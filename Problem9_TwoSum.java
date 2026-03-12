import java.util.*;

public class Problem9_TwoSum {

    public List<int[]> findTwoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                result.add(new int[]{map.get(complement), i});
            }
            map.put(nums[i], i);
        }
        return result;
    }

    public List<List<Integer>> findKSum(int[] nums, int k, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        kSumHelper(nums, 0, k, target, new ArrayList<>(), res);
        return res;
    }

    private void kSumHelper(int[] nums, int start, int k, int target, List<Integer> path, List<List<Integer>> res) {
        if (k == 2) {
            int left = start, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> temp = new ArrayList<>(path);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    res.add(temp);
                    left++;
                    right--;
                } else if (sum < target) left++;
                else right--;
            }
            return;
        }
        for (int i = start; i < nums.length - k + 1; i++) {
            path.add(nums[i]);
            kSumHelper(nums, i + 1, k - 1, target - nums[i], path, res);
            path.remove(path.size() - 1);
        }
    }
}