import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate{
	// 217.存在重复元素
    public boolean containsDuplicate(int[] nums) {
        // 方法一
        return Arrays.stream(nums).distinct().count() != nums.length;

        // 方法二：set可去重
//        Set<Integer> res = new HashSet<>();
//        for(int i : nums)
//            res.add(i);
//        return res.size()<nums.length ? true : false;
    }
}