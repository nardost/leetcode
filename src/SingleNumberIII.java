import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SingleNumberIII {

    public static void main(String[] args) {
        final int[] nums = new int[] { 2, 1, 3, 2, 5, 3, 5, 7 };
        final int[] result = new SingleNumberIII().singleNumber(nums);
        display(result);
    }
    public int[] singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(!set.add(nums[i])) {
                set.remove(nums[i]);
            }
        }
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void display(final int[] nums) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < nums.length; i++) {
            sb.append(nums[i] + ", ");
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        sb.append("]");
        System.out.println(sb.toString());
    }
}
