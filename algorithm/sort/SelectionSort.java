package algorithm.sort;

import java.util.Arrays;
// 选择排序
public class SelectionSort {
    /**
     * 只要实现了Comparable接口的类型均可使用此方法进行排序
     * @param a:一个实现了Comparable接口的类型的数组
     */
    public static void sort(Comparable a[]){
        for (int x=0;x<a.length;x++){
            int min = x;
            for (int y=x+1;y< a.length;y++){
                if (less(a[y],a[min])){
                    min = y;
                }
            }
            swap(a,x,min);
        }
    }

    /**
     * 方法重载，基本数据类型也可使用此方法进行排序（int数组）
     * @param a:int数组
     */
    public static void sort(int a[]){
        for (int x=0;x<a.length;x++){
            int min = x;
            for (int y=x+1;y< a.length;y++){
                if (a[y]<a[min]){
                    min = y;
                }
            }
            swap(a,x,min);
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

    /**
     * 方法重载，同上
     */
    private static void swap(int a[], int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
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
    public static void show(Comparable a[]){
        System.out.println(Arrays.toString(a));
    }

    /**
     *方法重载，同上
     */
    public static void show(int a[]){
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
        Integer [] test = new Integer[]{1,5,7,0,85,2,3,7,1,4};
        sort(test);
        show(test);
        System.out.println(isSorted(test));
        int [] test1 = {4,8,4,7,9,1,0,4,3};
        sort(test1);
        show(test1);
    }
}
