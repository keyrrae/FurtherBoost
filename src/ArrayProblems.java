/**
 * Created by xuanwang on 10/28/16.
 */
public class ArrayProblems {

    public static void mergeTwoSortedArrays(int A[], int m, int B[], int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (j >= 0 && i >= 0) {
            if (A[i] > B[j]) A[k--] = A[i--];
            else A[k--] = B[j--];
        }
        while (j >= 0) A[k--] = B[j--];
    }

    public static void moveZeroes(int[] nums) {
        int ind = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[ind++] = nums[i];
            }
        }
        while (ind < nums.length) {
            nums[ind++] = 0;
        }
    }

    public static void moveZerosOptimal(int[] nums) {
        for(int lastNonZeroFoundAt = 0, cur = 0; cur<nums.length;cur++) {
            if (nums[cur] != 0) {
                swap(nums, lastNonZeroFoundAt++, cur);
            }
        }
    }

    public void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1;
        int cur = 0;
        while(cur <= right) {
            if(nums[cur] == 0){
                swap(nums, left, cur);
                left++;
                cur++;
            }else if(nums[cur] == 2){
                swap(nums, right, cur);
                right--;
            }else{
                cur++;
            }
        }
    }

    private static void swap(int[]a , int l, int r){
        int temp = a[l];
        a[l] = a[r];
        a[r] = temp;
    }


}
