package algorithm.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


// 快速排序
public class QuickSort {
    /**
     * 只要实现了Comparable接口的类型均可使用此方法进行排序
     * @param a:一个实现了Comparable接口的类型的数组
     */
    public static void sort(Comparable a[]){
        //upset(a);// 打乱数组（效率太低）
        Collections.shuffle(Arrays.asList(a));// 使用Java原生提供的打乱数组的方法
        sort(a,0, a.length-1);
    }

    /**
     * 方法重载
     * @param a:一个实现了Comparable接口的类型的数组
     * @param left:最左边的元素索引（第一个）
     * @param right:最右边的元素索引（最后一个）
     */
    public static void sort(Comparable a[], int left,int right){
        if (left>=right) return;
        int j = partition(a, left, right);// 切分
        sort(a,left,j-1);// 将左边排序
        sort(a,j+1,right);// 将右边排序
    }

    /**
     * 切分
     * @param a:一个实现了Comparable接口的类型的数组
     * @param left:最左边的元素索引（第一个）
     * @param right:最右边的元素索引（最后一个）
     * @return:返回切分后的中间值的索引
     */
    private static int partition(Comparable a[],int left,int right){
        Comparable variable = a[left];// 选择数组的一个元素作为中间值，即它的左边的元素不大于它，它的右边的元素不小于它
        int i=left;
        int j=right+1;
        while (true){
            while (less(a[++i],variable)) if (i==right) break;// 从左边开始找到一个大于中间值的元素下标
            while (less(variable,a[--j])) if (j==left) break;// 从右边开始找到一个小于中间值的元素下标
            if (i>=j) break;// 如果两边的索引冲突，则停止循环
            swap(a,i,j);// 交换左右的元素
        }
        swap(a,left,j);// 将定义的中间值放入正确的位置
        return j;// 返回当前中间值的索引
    }
    @Deprecated
    public static void upset(Comparable a[]){
        Random random = new Random();
        for (int r= a.length-1;r< a.length;r--){
            if (r>0){
                int temp = random.nextInt(r);
                swap(a,temp,r);
            }
        }
    }
    // 三向切分的快速排序
    public static void threeWaySort(Comparable a[]){
        Collections.shuffle(Arrays.asList(a));
        threeWaySort(a,0,a.length-1);
    }
    // 三向切分的快速排序（重载）
    public static void threeWaySort(Comparable a[],int left,int right){
        if (left>=right) return;
        int j = left;
        int i = left+1;
        int k = right;
        Comparable v = a[left];
        while (i<=k){
            int result = a[i].compareTo(v);
            if (result<0) swap(a,i++,j++);
            else if (result>0) swap(a,i,k--);
            else i++;
        }
        threeWaySort(a,left,j-1);// 再比较小于中间值部分的数据
        threeWaySort(a,k+1,right);// 再比较大于中间值部分的数据
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
        threeWaySort(test);
        show(test);
        System.out.println(isSorted(test));
    }
}
