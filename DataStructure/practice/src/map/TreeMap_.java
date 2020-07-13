package map;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class TreeMap_<K,V> implements Map_<K,V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private int size;
    private Node<K,V> root;
    private Comparator<K> comparator;

    public TreeMap_() {
        this(null);
    }

    public TreeMap_(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 添加
     */
    public V put(K key, V value) {
        KeyNotNullCheck(key);

        // 添加第一个节点
        if (root == null) {
            root = new Node<>(key,value,null);
            root.colour = BLACK;
            size++;
            return null;
        }

        // 添加的不是第一个节点
        // 找到父节点
        Node<K,V> parent = root;
        Node<K,V> node = root;
        int cmp = 0;
        do {
            cmp = compare(key, node.key);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { // 相等
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

    public V get(K key) {
        Node<K,V> node = node(key);
        return node != null ? node.value : null;
    }

    /**
     * 删除
     */
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
            // 删除后继节点
            node = s;
        }
        // 删除node节点（node的度必然是1或者0）
        Node<K,V> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null)    // node是度为1的节点并且是根节点
                root = replacement;
            else if (node == node.parent.left)
                node.parent.left = replacement;
            else    // node == node.parent.right
                node.parent.right = replacement;
            // 删除节点之后的处理
            afterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;
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

    public boolean containsKey(K key) {
        return node(key) != null;
    }

    public boolean containsValue(V value) {
        if(root == null)
            return false;
        Queue<Node<K,V>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<K, V> node = queue.poll();
            if(valEquals(value,node.value))
                return true;
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
        return false;
    }

    /**
     * 遍历
     * 使用中序遍历：使得遍历出来的元素从小到大排列
     */
    public void traversal(Visitor<K, V> visitor) {
        if(visitor == null)
            return;
        traversal(root,visitor);
    }
    private void traversal(Node<K,V> node, Visitor<K, V> visitor) {
        if(node == null || visitor.stop)
            return;
        traversal(node.left,visitor);
        if(visitor.stop)
            return;
        visitor.visit(node.key,node.value);
        traversal(node.right,visitor);
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

    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child){
        //1.更新grand、parent、child的parent属性
        //让parent称为子树根节点
        parent.parent = grand.parent;
        if(grand.isLeftChild()){
            grand.parent.left= parent;
        }else if(grand.isRightChild()){
            grand.parent.right = parent;
        }else {//grand是root节点
            root = parent;
        }
        //更新child的parent
        if(child != null)
            child.parent = grand;
        //更新grand的parent
        grand.parent = parent;
    }

    /**
     * 根据key找到对应节点
     */
    private Node<K,V> node(K key){
        Node<K,V> node = root;
        while (node != null){
            int cmp = compare(key,node.key);
                if(cmp == 0)
                    return node;
                if(cmp > 0)
                    node = node.right;
                else
                    node = node.left;
            }
        return null;
    }

    /**
     * 判断value是否相等
     */
    private boolean valEquals(V v1,V v2){
        return v1==null ? v2==null : v1.equals(v2);
    }

    private static class Node<K,V> {
        K key;
        V value;
        boolean colour = RED;
        Node<K,V> left;
        Node<K,V> right;
        Node<K,V> parent;
        public Node(K key, V value, Node<K,V> parent) {
            this.key = key;
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
    }

    private void KeyNotNullCheck(K key) {
        if (key == null)
            throw new IllegalArgumentException("key must not be null");
    }

    /**
     * @return 返回值等于0，代表e1和e2相等；返回值大于0，代表e1大于e2；返回值小于于0，代表e1小于e2
     */
    private int compare(K k1, K k2) {
        if (comparator != null)
            return comparator.compare(k1, k2);
        return ((Comparable<K>)k1).compareTo(k2);
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
