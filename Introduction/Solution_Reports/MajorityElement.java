import java.util.List;

public class MajorityElement {
    public int majorityNumber(List<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return -1;
        }

        int majority = nums.get(0), count = 1;

        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            count += num == majority ? 1 : -1;
            if (count == 0) {
                majority = num;
                count = 1;
            }
        }
        
        return majority;
    }
}