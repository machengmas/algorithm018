public class Solution{
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(m+n>nums1.length){
            return;
        }
        for(int i=0;i<n;i++){
            nums1[m++]=nums2[i];
        }
        Arrays.sort(nums1);
    }
    
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1Copy = new int[m];
        System.arraycopy(nums1, 0, nums1Copy, 0, m);
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < m && j < n) {
            nums1[k++] = nums1Copy[i] < nums2[j] ? nums1Copy[i++] : nums2[j++];
        }

        if (i < m) {
            System.arraycopy(nums1Copy, i, nums1, i + j, m - i);
        }

        if (j < n) {
            System.arraycopy(nums2, j, nums1, i + j, n - j);
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1;
        int j=n-1;
        int k=m+n-1;
        while (i>=0&&j>=0){
            nums1[k--]=nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
        
        System.arraycopy(nums2, 0, nums1, 0, j+1); 
    }
}
