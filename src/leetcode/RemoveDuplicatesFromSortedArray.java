package leetcode;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        int[] nums ={0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));

    }
    public static int removeDuplicates(int[] nums) {
        int k = 1;
        int j = 0;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[j]) {
                j++;
                k++;
                nums[j] = nums[i];

            }
        }
        return k;
    }
}
