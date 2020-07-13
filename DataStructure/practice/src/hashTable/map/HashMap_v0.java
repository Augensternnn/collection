package hashTable.map;

import Tree.util.printer.BinaryTreeInfo;
import Tree.util.printer.BinaryTrees;
import map.Map_;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class HashMap_v0<K,V> implements Map_<K,V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private int size;//数组中红黑树节点总数量
    private Node<K,V>[] table;
    private static final int DEFAULT_CAPACITY = 1<<4;// 1<<4 == 2^4

    public HashMap_v0() {
        table = new Node[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        if(size == 0)
            return;
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    public V put(K key, V value) {
        int index = index(key);
        //取出index位置的红黑树根节点
        Node<K, V> root = table[index];
        if(root == null) {
            root = new Node<>(key, value, null);
            table[index] = root;
            size++;
            afterPut(root);
            return null;
        }

        //添加新节点到红黑树上面
        Node<K,V> parent = root;
        Node<K,V> node = root;
        int cmp = 0;
        K k1 = key;
        int h1 = k1 == null ? 0 : k1.hashCode();
        Node<K,V> result = null;
        boolean searched = false;// 是否已经搜索过这个key
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            if(h1 > h2)
                cmp = 1;
            else if(h1 < h2)
                cmp = -1;
            else if(Objects.equals(k1,k2))
                cmp = 0;
            else if(k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0){
            } else if(searched) {//已经扫描了
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }else {//searched == false；还没有扫描，先扫描，然后根据内存地址大小决定左右
                if((node.left != null && (result = node(node.left,k1)) != null)
                        || (node.right != null && (result = node(node.right,k1)) != null)){
                    //已经存在这个key
                    node = result;
                    cmp = 0;
                }else {//不存在这个key
                    searched = true;
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { // 相等
                //这里不用覆盖哈希值，因为equals为true的2个key哈希值一样
                node.key = key;
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        } while (node != null);

        // 看看插入到父节点的哪个位置
        Node<K,V> newNode = new Node<>(key,value,parent);
        if (cmp > 0)
            parent.right = newNode;
        else
            parent.left = newNode;
        size++;
        //新添加节点之后的处理
        afterPut(newNode);
        return null;
    }

    public V get(K key) {
        Node<K,V> node = node(key);
        return node != null ? node.value : null;
    }

    //以红黑树形式打印
    public void print(){
        if(size == 0)
            return;
        for (int i = 0; i < table.length; i++) {
            final Node<K,V> root = table[i];
            System.out.println("【index = " + i + "】");
            BinaryTrees.println(new BinaryTreeInfo() {
                @Override
                public Object root() {
                    return root;
                }

                @Override
                public Object left(Object node) {
                    return ((Node<K,V>)node).left;
                }

                @Override
                public Object right(Object node) {
                    return ((Node<K,V>)node).right;
                }

                @Override
                public Object string(Object node) {
                    return node;
                }
            });
            System.out.println("------------------------------------------------------");
        }
    }

    public V remove(K key) {
        return remove(node(key));
    }
    private V remove(Node<K,V> node){
        if (node == null)
            return null;
        size--;
        V olaValue = node.value;
        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<K,V> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.key = s.key;
            node.value = s.value;
            node.hash = s.hash;
            // 删除后继节点
            node = s;
        }
        // 删除node节点（node的度必然是1或者0）
        Node<K,V> replacement = node.left != null ? node.left : node.right;
        int index = index(node);
        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null)    // node是度为1的节点并且是根节点
                table[index] = replacement;
            else if (node == node.parent.left)
                node.parent.left = replacement;
            else    // node == node.parent.right
                node.parent.right = replacement;
            // 删除节点之后的处理
            afterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            table[index] = null;
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left)
                node.parent.left = null;
            else    // node == node.parent.right
                node.parent.right = null;
            // 删除节点之后的处理	afterRemove(node,null);
            afterRemove(node);
        }
        return olaValue;
    }

    public boolean containsKey(K key) {
        return node(key) != null;
    }

    public boolean containsValue(V value) {
        if(size == 0)
            return false;
        Queue<Node<K,V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if(table[i] == null)
                continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()){
                Node<K,V> node = queue.poll();
                if(Objects.equals(value,node.value))
                    return true;
                if(node.left != null)
                    queue.offer(node.left);
                if(node.right != null)
                    queue.offer(node.right);
            }
        }
        return false;
    }

    public void traversal(Visitor<K, V> visitor) {
        if(size == 0 || visitor == null)
            return;
        Queue<Node<K,V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if(table[i] == null)
                continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()){
                Node<K,V> node = queue.poll();
                if(visitor.visit(node.key,node.value))
                    return;
                if(node.left != null)
                    queue.offer(node.left);
                if(node.right != null)
                    queue.offer(node.right);
            }
        }
    }

    private static class Node<K,V> {
        int hash;
        K key;
        V value;
        boolean colour = RED;
        Node<K,V> left;
        Node<K,V> right;
        Node<K,V> parent;
        public Node(K key, V value, Node<K,V> parent) {
            this.key = key;
            this.hash = key == null ? 0 : key.hashCode();
            this.value = value;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild(){
            return parent!=null && this==parent.left;
        }

        public boolean isRightChild(){
            return parent!=null && this==parent.right;
        }

        //返回兄弟节点
        public Node<K,V> sibling(){
            if(isLeftChild()){
                return parent.right;
            }
            if(isRightChild()){
                return parent.left;
            }
            return null;
        }

        @Override
        public String toString() {
            return "Node_" + key + "_" + value;
        }
    }

    /**
     * 根据key生成对应的索引（在桶数组中的位置）
     */
    private int index(K key){
        if(key == null)
            return 0;
        int hash = key.hashCode();
        // hash ^ (hash >>> 16) //让高低16位再次混合运算
        return (hash ^ (hash >>> 16)) & (table.length - 1);
    }
    private int index(Node<K,V> node){
        return (node.hash ^ (node.hash >>> 16)) & (table.length - 1);
    }

    /**
     * 比较key大小
     */
//    private int compare(K k1, K k2, int h1, int h2) {
//        //比较哈希值
//        int result = h1 - h2;
//        if(result != 0)
//            return result;
//        //哈希值相同 -- 比较equals
//        if(Objects.equals(k1,k2))//哈希值相同，equals为true
//            return 0;
//
//        //哈希值相同，equals为false -- 比较类名
//        if(k1 != null && k2 != null){
//            String k1Cls = k1.getClass().getName();
//            String k2Cls = k2.getClass().getName();
//            result = k1Cls.compareTo(k2Cls);
//            if(result != 0)
//                return result;
//
//            //同一种类型并且具备可比较性
//            if(k1 instanceof Comparable)
//                return ((Comparable) k1).compareTo(k2);
//        }
//        //同一种类型、哈希值相等，但不具备可比较性 或 k1、k2有一个为null -- 比较内存地址
//        // System.identityHashCode()：利用内存地址算出一个hashCode
//        return System.identityHashCode(k1) - System.identityHashCode(k2);
//    }

    private Node<K,V> node(K key){
        Node<K,V> root = table[index(key)];
        return root == null ? null : node(root,key);
    }
    /**
     * 去node节点里找k1对应的节点
     */
    private Node<K,V> node(Node<K,V> node, K k1){
        int h1 = k1 == null ? 0 : k1.hashCode();
        Node<K,V> result = null;    //存储查找结果
        int cmp = 0;
        while (node != null){
            K k2 = node.key;
            int h2 = node.hash;
            if(h1 > h2)
                node = node.right;
            else if(h1 < h2)
                node = node.left;
            else if(Objects.equals(k1,k2))
                return node;
            else if(k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0){
                node = cmp > 0 ? node.right : node.left;
            } else if(node.right != null && (result = node(node.right,k1)) != null)
                return result;
            else    //右边扫描没找到，只能往左边找
                node = node.left;
        }
        return null;
    }

    /**
     * 添加之后的修复
     */
    private void afterPut(Node<K,V> node){
        Node<K,V> parent = node.parent;

        //添加的是根节点 或者 上溢到达了根节点
        if(parent == null){
            black(node);
            return;
        }

        // 父节点是黑色：直接返回
        if(isBlack(parent))
            return;

        Node<K,V> uncle = parent.sibling();// 叔父节点
        Node<K,V> grand = red(parent.parent);//祖父节点
        //叔父节点是红色【B树节点上溢】
        if(isRed(uncle)){
            black(parent);
            black(uncle);
            // 将祖父节点当做是新添加的节点
            afterPut(grand);
            return;
        }
        //叔父节点不是红色
        if(parent.isLeftChild()){//L
            if(node.isLeftChild()){//LL
                black(parent);
            }else {//LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        }else {//R
            if(node.isRightChild()){//RR
                black(parent);
            }else {//RL
                black(node);
                rotateRight(parent);
            }
            rotateLeft(grand);
        }
    }

    /**
     * 删除之后的修复
     */
    private void afterRemove(Node<K,V> node){
        // 删除的节点是红色 或 用以取代删除节点的子节点是红色
        if(isRed(node)){
            black(node);
            return;
        }

        Node<K,V> parent = node.parent;
        if(parent == null)  //删除的是根节点 或下溢到达了根节点
            return;

        // 删除黑色叶子节点【下溢】
        boolean left = parent.left==null || node.isLeftChild();// 判断被删除的node是左还是右
        Node<K,V> sibling = left ? parent.right : parent.left;
        if(left){// 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }
            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                colour(sibling, colourOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }

        }else {// 被删除的节点在右边，兄弟节点在左边
            if(isRed(sibling)){// 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if(isBlack(sibling.left) && isBlack(sibling.right)){// 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack)
                    afterRemove(parent);
            }else {// 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if(isBlack(sibling.left)){
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                colour(sibling,colourOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    /**
     *左旋转
     */
    private void rotateLeft(Node<K,V> grand){
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand,parent,child);
    }

    /**
     *右旋转
     */
    private void rotateRight(Node<K, V> grand){
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand,parent,child);
    }

    /**
     * 旋转之后的修复
     */
    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child){
        //1.更新grand、parent、child的parent属性
        //让parent称为子树根节点
        parent.parent = grand.parent;
        if(grand.isLeftChild()){
            grand.parent.left= parent;
        }else if(grand.isRightChild()){
            grand.parent.right = parent;
        }else {//grand是root节点（root节点：grand、parent、child所在红黑树的根节点）
            table[index(grand)] = parent;
        }
        //更新child的parent
        if(child != null)
            child.parent = grand;
        //更新grand的parent
        grand.parent = parent;
    }

    /**
     * 后继节点
     */
    protected Node<K,V> successor(Node<K,V> node) {
        if (node == null) return null;
        // 前驱节点在左子树当中（right.left.left.left....）
        Node<K,V> p = node.right;
        if (p != null) {
            while (p.left != null)
                p = p.left;
            return p;
        }
        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 给节点染色
     */
    private Node<K,V> colour(Node<K,V> node, boolean colour){
        if(node == null)
            return node;
        node.colour = colour;
        return node;
    }

    /**
     * 将节点染成红色
     */
    private Node<K,V> red(Node<K,V> node){
        return colour(node,RED);
    }

    /**
     * 将节点染成黑色
     */
    private Node<K,V> black(Node<K,V> node){
        return colour(node,BLACK);
    }

    /**
     * 判断节点是什么颜色
     */
    private boolean colourOf(Node<K,V> node){
        return node==null ? BLACK : node.colour;
    }

    /**
     * 节点是否为黑色
     */
    private boolean isBlack(Node<K,V> node){
        return colourOf(node) == BLACK;
    }

    /**
     * 节点是否为红色
     */
    private boolean isRed(Node<K,V> node){
        return colourOf(node) == RED;
    }
}
