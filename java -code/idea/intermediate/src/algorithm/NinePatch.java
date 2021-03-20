package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// 九宫格（暴力）
public class NinePatch {
    public static void fill(){
        List<List<Integer>> list = new ArrayList<>();
        for (int z=369;z<=987;z++){
            for (int y=246;y<=658;y++) {
                for (int x=123;x<=329;x++) {
                    if (y==2*x&&z==3*x){
                        List<Integer> list1 = Arrays.asList(x,y,z);
                        if (!isRepeat(list1)&&!isContainsZero(list1)){
                            list.add(list1);
                        }
                    }
                }
            }
        }
        System.out.println(list);
    }
    private static boolean isRepeat(List<Integer> lists){
        boolean flag[] = new boolean[10];
        for (int x=1;x<flag.length;x++){
            flag[x]=true;
        }
        for (int x=0;x<lists.size();x++){
            for (int y=1;y<10;y++){
                for (int z=0;z<String.valueOf(lists.get(x)).length();z++){
                    if (y==Integer.parseInt(String.valueOf(lists.get(x)).substring(z,z+1))){
                        if (flag[y]==true){
                            flag[y]=false;
                        }else {
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }
    private static boolean isContainsZero(List<Integer> list){
        for (int x=0;x<list.size();x++){
            if (String.valueOf(list.get(x)).contains("0")){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        fill();
    }
}
