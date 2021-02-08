package algorithm.sort;

import java.util.Arrays;
// 基数排序
public class RadixSort {
    public static void sort(){}
    /**
     *输出数组
     */
    public static void toArray(Comparable a[]){
        System.out.println(Arrays.toString(a));
    }
    /**
     * 判断数组是否已排序
     */
    public static boolean isSorted(Comparable a[]){
        for (int x=1;x< a.length;x++){
            if (less(a[x],a[x-1])) return false;
        }
        return true;
    }
    /**
     *对两个元素进行比较
     */
    public static boolean less(Comparable a,Comparable b){
        return a.compareTo(b)<0;
    }

    public static void main(String[] args) {

    }
}
