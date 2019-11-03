public class CreateMaximumNumber {
    /**
     * @param nums1 an integer array of length m with digits 0-9
     * @param nums2 an integer array of length n with digits 0-9
     * @param k an integer and k <= m + n
     * @return an integer array
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        // if k = 0 or no sufficient numbers to reach k, return empty
        if (k == 0 || nums1.length + nums2.length < k) {
            return new int[0];
        }

        int[] ans = new int[k];
        
        // if only k elements, return the merge result of nums1 and nums2
        if (nums1.length + nums2.length == k) {
            return getMergedMaxNumber(nums1, nums2);
        }

        // take i elements from nums1 and k-1 elements from nums2
        for (int i = 0; i <= k; i++) {
            if ( i > nums1.length || k-i > nums2.length) {
                continue;
            }
            int[] currentMax = getMergedMaxNumber(getMaxNumber(nums1, i), getMaxNumber(nums2, k-i));
            if (isGreater(currentMax, 0, ans, 0))
                ans = currentMax;
        }

        return ans;
    }

    private int[] getMergedMaxNumber(int[] nums1, int[] nums2) {
        int[] results = new int[nums1.length + nums2.length];

        int start1 = 0, start2 = 0;
        for (int i = 0; i < nums1.length+nums2.length; i++) {
            results[i] = isGreater(nums1, start1, nums2, start2) ? nums1[start1++] : nums2[start2++];
        }
        return results;
    }

    private boolean isGreater(int[] nums1, int start1, int[] nums2, int start2) {
        for (; start1 < nums1.length && start2 < nums2.length; start1++, start2++) {
            if (nums1[start1] > nums2[start2]) {
                return true;
            }
            if (nums1[start1] < nums2[start2]) {
                return false;
            }
        }
        return start1 != nums1.length;
    }

    private int[] getMaxNumber(int[] nums, int size) {
        if (size == 0) {
            return new int[0];
        }

        int[] result = new int[size];
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            while (nums.length - j + i > size && i > 0 && result[i-1] < nums[j])
                i--;
            if (i < size)
                result[i++] = nums[j];
        }
        
        return result;
    }

}