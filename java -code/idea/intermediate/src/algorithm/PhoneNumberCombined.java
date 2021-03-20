package algorithm;
import java.util.*;
public class PhoneNumberCombined {
    public List<String> letterCombinations(String digits) {
        if(digits.length()>4||digits==null) return null;
        StringBuffer sb = new StringBuffer(digits);
        for(int x=0;x<sb.length();x++){
            if(sb.charAt(x)<'2'||sb.charAt(x)>'9') return null;
        }
        Map<Integer,String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        List<String> list = new ArrayList<>();
        if(digits.equals("")){
            return list;
        }
        if(sb.length()==1){
            String temp=map.get(Integer.parseInt(sb.substring(0)));
            char [] chars = temp.toCharArray();
            for(int x=0;x<chars.length;x++){
                list.add(String.valueOf(chars[x]));
            }
        }else{
            String s = map.get(Integer.parseInt(String.valueOf(sb.charAt(0))));
            for (int x=0;x<s.length();x++){
                list.add(s.substring(x,x+1));
            }
            for (int x=1;x<sb.length();x++){
                String s1 = map.get(Integer.parseInt(String.valueOf(sb.charAt(x))));
                int len = list.size();
                for (int y=0;y<len;y++){
                    String remove = list.remove(0);
                    for (int z=0;z<s1.length();z++){
                        list.add(remove+s1.substring(z,z+1));
                    }
                }
            }
        }
        return list;
    }
}
