package algorithm;

import java.util.Arrays;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int results[] = new int[2];
        if (nums.length<2||nums.length>103){
            results[0] = -1;
            results[1] = -1;
            return results;
        }
        if (target>109||target<-109){
            results[0] = -1;
            results[1] = -1;
            return results;
        }
        for (int num: nums) {
            if (num>109||num<-109){
                results[0] = -1;
                results[1] = -1;
                return results;
            }
        }
        for (int x=0;x< nums.length;x++){
            for (int y=x+1;y< nums.length;y++){
                if (nums[x]+nums[y]==target){
                    results[0] = x;
                    results[1] = y;
                    return results;
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        int [] results = new TwoSum().twoSum(new int[]{1,2,7,3,100,2,100,7,-4,4},4);
        System.out.println(Arrays.toString(results));
    }
}
