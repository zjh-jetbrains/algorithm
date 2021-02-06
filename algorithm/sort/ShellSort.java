package algorithm.sort;

import java.util.Arrays;

// 希尔排序
public class ShellSort {
    /**
     * 只要实现了Comparable接口的类型均可使用此方法进行排序
     * @param a:一个实现了Comparable接口的类型的数组
     * @param split:希尔排序要将整个大数组分为多个小数组，此参数的意义为划分的数组的个数或者划分的
     * 数组中每两个数据之间的间隔。因为要在其他类中进行效率测试，所以才使用可变参数
     */
    public static void sort(Comparable a[],int ... split){
        int h=1;
        while (h<a.length/split[0]){
            h=h*split[0]+1;  //1,4,13,40...
        }
        while (h>=1){
            for (int x=h;x<a.length;x++){
                for (int y=x;y>=h&&less(a[y],a[y-h]);y-=h){
                    swap(a,y-h,y);
                }
            }
            h/=split[0];
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
        sort(test,3);
        show(test);
        System.out.println(isSorted(test));
    }
}
