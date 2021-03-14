package algorithm.String;

import algorithm.sort.InsertionSort;

import java.util.Arrays;

// 高位优先的字符串排序
public class MSD {
    private static final int M = 15; // 切换到小数组的阈值
    private static String temp[]; // 辅助数组
    private static int R=256; // 基数
    public static void sort(String s[]){
        temp = new String[s.length];
        sort(s,0,s.length-1,0);
    }
    private static int charAt(String str,int index){
        if (index<str.length()){return str.charAt(index);}
        else return -1;
    }
    private static void sort(String s[],int left,int right,int d){
        if (right<=left+M){ // 切换到插入排序
            InsertionSort.sort(s, left, right, d);return;
        }
        int [] count = new int[R+2];
        for (int x=left;x<=right;x++){
            count[charAt(s[x],d)+2]++;
        }
        for (int x=0;x<R+1;x++){
            count[x+1]+=count[x];
        }
        for (int x=left;x<=right;x++){
            temp[count[charAt(s[x],d)+1]++]=s[x];
        }
        for (int x=left;x<=right;x++){
            s[x]=temp[x-left];
        }
        for (int x=0;x<R;x++) {
            sort(s, left + count[x], left + count[x + 1] - 1, d + 1);
        }
    }

    public static void main(String[] args) {
        String s[] = new String[]{"4sd5af6","fds46a5","4sd56a","asd465","46asd5","zxc789","asd564"};
        MSD.sort(s);
        System.out.println(Arrays.toString(s));
    }
}
