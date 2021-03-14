package algorithm.sort;

import java.util.Arrays;

// 插入排序
public class InsertionSort {
    /**
     * 只要实现了Comparable接口的类型均可使用此方法进行排序
     * @param a:一个实现了Comparable接口的类型的数组
     */
    public static void sort(Comparable a[]){
        for (int x=1;x<a.length;x++){
            for (int y=x;y>0&&less(a[y],a[y-1]);y--){
                swap(a,y-1,y);
            }
        }
    }
    public static void sort(String s[],int left,int right,int d){
        for (int x=left;x<=right;x++){
            for (int y=x;y>left&&less(s[y],s[y-1],d);y--){
                swap(s,y,y-1);
            }
        }
    }
    /**
     * 交换数组中的两个元素
     * @param a:一个实现了Comparable接口的类型的数组
     * @param i:第一个下标
     * @param j:第二个下标
     */
    private static void swap(Comparable a[], int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    private static void swap(String s[],int i,int j){
        String str = s[i];
        s[i]=s[j];
        s[j]=str;
    }
    /**
     *对两个元素进行比较
     */
    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b)<0;
    }
    /**
     *输出数组
     */
    private static boolean less(String s1,String s2,int d){
        return s1.substring(d).compareTo(s2.substring(d))<0;
    }
    public static void show(Comparable a[]){
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

    public static void main(String[] args) {
        Integer [] test = new Integer[]{1,5,7,0,85,2,3,7,1,4,0,6};
        sort(test);
        show(test);
        System.out.println(isSorted(test));
    }
}
