package algorithm;

import java.util.*;

public class CharacterReplacement {
    public int characterReplacement(String s, int k) {
        Map<Character,Integer> map = new HashMap<>();
        if (s.matches("[A-Z]+")){
            char [] temp = s.toCharArray();
            Random random = new Random();
            for (int x=0;x<k;x++){
                int i = random.nextInt(temp.length);
                int j = random.nextInt(temp.length);
                temp[i] = s.charAt(j);
            }
            for (int x=0;x<temp.length;x++){
                if(map.containsKey(temp[x])){
                    map.put(temp[x],map.get(temp[x])+1);
                }else {
                    map.put(temp[x],1);
                }
            }
            Collection<Integer> c = map.values();
            Object[] obj = c.toArray();
            Arrays.sort(obj);
            return (int)obj[obj.length-1];
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new CharacterReplacement().characterReplacement("AAAAABBBBBCC",10));
    }
}
