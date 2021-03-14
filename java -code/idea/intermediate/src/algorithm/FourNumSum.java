package algorithm;

import java.util.ArrayList;
import java.util.List;

public class FourNumSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (target<-109||target>109) return null;
        if (nums.length<0||nums.length>200) return null;
        for (int x:nums){
            if (x>109||x<-109) return null;
        }
        List<List<Integer>> list = new ArrayList<>();
        for (int x=0;x<nums.length-3;x++){
            for (int y=x+1;y<nums.length-2;y++){
                for (int z=y+1;z<nums.length-1;z++){
                    if (nums[x]+nums[y]+nums[z]+nums[z+1]==target){
                        List<Integer> subList=List.of(nums[x], nums[y], nums[z], nums[z + 1]);
                        list.add(subList);
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        FourNumSum fns = new FourNumSum();
        List<List<Integer>> lists = fns.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        System.out.println(lists);
    }
}
