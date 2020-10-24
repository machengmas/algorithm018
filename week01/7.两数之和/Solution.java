public class Solution{
    public int[] twoSum(int[] nums, int target) {
        int[] index=new int[2];
        List numList=Arrays.stream(nums).boxed().collect(Collectors.toList());
        for(int i=0;i<nums.length;i++){
            int indexof=numList.indexOf(target-nums[i]);
            if(indexof!=-1&&indexof!=i){
                index[0]=i;
                index[1]=indexof;
                break;
            }
        }
        return index;
    }

    public int[] twoSum1(int[] nums, int target) {
        Map<Integer,Integer> numMap=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            int num=target-nums[i];
            if(numMap.containsKey(num)){
                return new int[]{numMap.get(num),i};
            }
            numMap.put(nums[i],i);
        } 
        return new int[]{0,0};
     }
}
