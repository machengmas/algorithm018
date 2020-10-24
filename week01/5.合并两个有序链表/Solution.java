public class Solution{
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    
    public void rotate1(int[] nums, int k) {
        for(int i=0;i<k;i++){
            int rotateNum=nums[nums.length-1];
            for(int j=0;j<nums.length;j++){
                int temp=nums[j];
                nums[j]=rotateNum;
                rotateNum=temp;
            }
        }
    }
}
