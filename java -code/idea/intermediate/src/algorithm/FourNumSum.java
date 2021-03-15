package algorithm;

import java.util.*;
// 暴力四重循环解决
public class FourNumSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (target<-109||target>109) return null;
//        if (nums.length<0||nums.length>200) return null;
//        for (int x:nums){
//            if (x>109||x<-109) return null;
//        }
        List<List<Integer>> list = new ArrayList<>();
        for (int x=0;x<nums.length-3;x++){
            for (int y=x+1;y<nums.length-2;y++){
                for (int z=y+1;z<nums.length-1;z++){
                    for (int t=z+1;t<nums.length;t++){
                        if (nums[x]+nums[y]+nums[z]+nums[t]==target){
                            List<Integer> subList=new ArrayList<>();
                            subList.add(nums[x]);
                            subList.add(nums[y]);
                            subList.add(nums[z]);
                            subList.add(nums[t]);
                            Collections.sort(subList);
                            list.add(subList);
                        }
                    }
                }
            }
        }
        Set<List<Integer>> set = new HashSet<List<Integer>>(list);
        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        FourNumSum fns = new FourNumSum();
        List<List<Integer>> lists = fns.fourSum(new int[]{-5,5,4,-3,0,0,4,-2}, 4);
        System.out.println(lists);
    }
}
