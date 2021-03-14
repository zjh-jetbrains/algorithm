package algorithm.String;

import java.util.Arrays;

// 三向字符串快速排序
public class ThreeQuickSortString {
    private static int chatAt(String str,int d){
        if (d<str.length()) return str.charAt(d);
        else return -1;
    }
    public static void sort(String [] s){
        sort(s,0,s.length-1,0);
    }
    private static void sort(String [] s,int left,int right, int d){
        if (left>=right) return;
        int v = chatAt(s[left],d); // 以第一个字符串的首字符进行切分
        int lt = left;
        int i = left+1;
        int gt = right;
        while (i<=gt){
            int w = chatAt(s[i], d);
            if (v>w) swap(s,lt++,i++);
            else if (v<w) swap(s,i,gt--);
            else i++;
        }
        sort(s,left,lt-1,d);
        if (v>=0) sort(s,lt,gt,d+1);
        sort(s,gt+1,right,d);
    }
    private static void swap(String s[],int i,int j){
        String temp = s[i];
        s[i]=s[j];
        s[j]=temp;
    }

    public static void main(String[] args) {
        String [] arrays = new String[]{"sdaas4","cvb456","sfgh546","sghj46","zfgh0","serty","afgh54","thj45"};
        ThreeQuickSortString.sort(arrays);
        System.out.println(Arrays.toString(arrays));
    }
}
