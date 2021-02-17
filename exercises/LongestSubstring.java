package algorithm;

import java.util.ArrayList;
import java.util.List;

public class LongestSubstring {
    private int length = 0;
    public int lengthOfLongestSubstring(String s) {
        List<Integer> listLength = new ArrayList<>();
        StringBuffer temp = new StringBuffer(s);
        while (temp.length()!=0){
            StringBuffer stringBuffer = new StringBuffer();
            for (int x=0;x<temp.length();x++){
                if (!stringBuffer.toString().contains(String.valueOf(temp.charAt(x)))){
                    stringBuffer.append(temp.charAt(x));
                }else {
                    listLength.add(stringBuffer.length());
                    stringBuffer = new StringBuffer();
                    stringBuffer.append(temp.charAt(x));
                }
            }
            listLength.add(stringBuffer.length());
            temp.deleteCharAt(0);
        }
        return this.getMax(listLength);
    }
    public int getMax(List<Integer> nums){
        this.length = nums.get(0);
        for (int x=1;x< nums.size();x++){
            if (this.length<nums.get(x)){
                this.length = nums.get(x);
            }
        }
        return this.length;
    }
    public static void main(String[] args) {
        LongestSubstring longestSubstring = new LongestSubstring();
        System.out.println(longestSubstring.lengthOfLongestSubstring("abcabcddefgh45as1fs2as4df45+-#  "));
    }
}
