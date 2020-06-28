package _.nowcoder;

import java.util.*;

/**
 * 数组中值出现了一次的数字
 * https://www.nowcoder.com/questionTerminal/200d8d789f9f431999fac795bb094356?f=discussion
 *
 * 【写的不大对】
 */
public class OnlyShowOnce {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.nextLine();
        String[] sr = s.split(" ");
        int[] arr = new int[sr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(sr[i]);
        }
        List list = new ArrayList();
        list.add(0);
        for (int i = 0; i < arr.length; i++) {
            if(list.indexOf(arr[i])<0)
                list.add(arr[i]);
            else
                list.remove(list.indexOf(arr[i]));
        }
        list.remove(0);
        Collections.sort(list);
        System.out.println(list);
    }
}
