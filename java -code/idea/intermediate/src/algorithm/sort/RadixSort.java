package algorithm.sort;

import java.util.*;

// 基数排序
public class RadixSort {
    public static void sort(int a[]){
        int maxValue = max(a);
        int exp;
        for (exp=1;maxValue/exp>0;exp*=10){// 从个位开始
            int temp[] = new int[a.length];
            String s[] = new String[]{"","","","","","","","","",""};
            for (int number:a){
                for (int x=0;x<10;x++){
                    if ((number/exp)%10==x) s[x]+=number+"、";
                }
            }
            List<Integer> list = new ArrayList<>();
            for (int y=0;y<s.length;y++){
                if (s[y]!="") {
                    String str[] = s[y].split("、");
                    for (int z=0;z<str.length;z++){
                        list.add(Integer.parseInt(str[z]));
                    }
                }
            }
            for (int x=0;x< temp.length;x++){
                temp[x]= list.get(x);
            }
            System.arraycopy(temp,0,a,0,temp.length);
        }

    }
    private static int max(int a[]){
        int max = a[0];
        for (int x=1;x<a.length;x++){
            if (a[x]>max) max = a[x];
        }
        return max;
    }
    /**
     *输出数组
     */
    public static void toArray(int a[]){
        System.out.println(Arrays.toString(a));
    }
    /**
     * 判断数组是否已排序
     */
    public static boolean isSorted(int a[]){
        for (int x=1;x< a.length;x++){
            if (a[x]<a[x-1]) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        int a[] = new int[]{4,85,956,74,581,5162,4152,71150,456,0,854};
        sort(a);
        toArray(a);
    }
}
