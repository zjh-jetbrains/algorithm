package algorithm.sort;

import java.util.Arrays;
// 归并排序
public class MergeSort {
    private static Comparable temp[];// 作为临时存放数据的地方
    /**
     *  原地归并排序
     * 只要实现了Comparable接口的类型均可使用此方法进行排序
     * @param a:一个实现了Comparable接口的类型的数组
     * @param left:第一个元素的下标
     * @param middle:将数组一分为二的中间下标
     * @param right:最后一个元素的下标
     */
    private static void situSort(Comparable a[],int left, int middle, int right){
        int x = left;// 左半边的第一个元素
        int y = middle+1;// 右半边的第一个元素
        for (int z=left;z<=right;z++){  // 拷贝
            temp[z] = a[z];
        }
        for (int z=left;z<=right;z++){
            if (x>middle) a[z] = temp[y++];// 左半边用尽，取右边的元素
            else if (y>right) a[z] = temp[x++];// 右半边用尽，取左边的元素
            else if (less(temp[y],temp[x])) a[z] = temp[y++];// 右半边的元素小于左半边的元素，取右边的元素
            else a[z] = temp[x++];// 右半边的元素大于或等于左半边的元素，取左半边的元素
        }
    }

    /**
     * 自顶向下的归并排序（重载）
     * @param a:一个实现了Comparable接口的类型的数组
     */
    public static void topDownSort(Comparable a[]){
        temp = new Comparable[a.length];// 为临时数组分配空间
        topDownSort(a,0,a.length-1);
    }

    /**
     * 自顶向下的归并排序
     * @param a:一个实现了Comparable接口的类型的数组
     * @param left:第一个元素的下标
     * @param right:最后一个元素的下标
     */
    public static void topDownSort(Comparable a[],int left,int right){
        if (left>=right) return;
        int middle = left + (right-left)/2;
        topDownSort(a, left, middle);// 左边排序
        topDownSort(a, middle+1, right);// 右边排序
        if (less(a[middle+1],a[middle])) situSort(a,left,middle,right);// 如果已经有序，就不归并了，否则归并
    }

    /**
     * 自底向上的归并排序，子数组长度全为1、2、4、8、16...，分别进行两两归并、四四归并、八八归并...
     * @param a
     */
    public static void bottomUpSort(Comparable a[]){
        int n = a.length;;
        temp = new Comparable[n];
        for (int x=1;x<n;x+=x){// 子数组的长度
            for (int y=0;y<n-x;y+=x+x){// 子数组的索引
                situSort(a,y,y+x-1,Math.min(y+x+x-1,n-1));
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
        Integer [] test = new Integer[]{4,3,2,8,9,4,0,1,5,7,9,3};
        //topDownSort(test);
        bottomUpSort(test);
        show(test);
        System.out.println(isSorted(test));
    }
}
