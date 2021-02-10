package algorithm.sort;

public class SortCompare {
    /**
     * 计算排序算法的时间
     * @param sortName:排序算法的名字
     * @param a:任意一个实现了Comparable接口的类型的数组
     * @return:返回使用某个排序算法的时间
     */
    public static double calTime(String sortName, Comparable a[]){
        double start = System.currentTimeMillis();
        if (sortName.equals("Bubble")) BubbleSort.sort(a);
        if (sortName.equals("Insertion")) InsertionSort.sort(a);
        if (sortName.equals("Selection")) SelectionSort.sort(a);
        if (sortName.equals("Shell")) ShellSort.sort(a,3);
        if (sortName.equals("MergeTD")) MergeSort.topDownSort(a);
        if (sortName.equals("MergeBU")) MergeSort.bottomUpSort(a);
        if (sortName.equals("Quick")) QuickSort.sort(a);
        if (sortName.equals("QuickThreeWay")) QuickSort.threeWaySort(a);
        double end = System.currentTimeMillis();
        return (end-start)/1000;  // 以秒为单位
    }

    /**
     * 计算多次排序的总时间
     * @param sortName:排序算法的名字
     * @param n:数组的长度
     * @param t:数组的数量
     * @return:
     */
    public static double timeRandomInput(String sortName, int n, int t){
        double total = 0.0;
        Comparable a [] = new Double[n];
        for (int x=0;x<t;x++){
            for (int y=0;y<n;y++){
                a[y] = Math.random();
            }
            double singleTime = calTime(sortName, a);
            total+=singleTime;
        }
        return total;
    }
    public static void main(String[] args) {
        System.out.println("选择排序："+timeRandomInput("Selection",1000,10));
        System.out.println("插入排序："+timeRandomInput("Insertion",1000,10));
        System.out.println("冒泡排序："+timeRandomInput("Bubble",1000,10));
        System.out.println("希尔排序："+timeRandomInput("Shell",1000,10));
        System.out.println("归并排序(自顶向下)："+timeRandomInput("MergeTD",1000,10));
        System.out.println("归并排序(自底向上)："+timeRandomInput("MergeBU",1000,10));
        System.out.println("快速排序："+timeRandomInput("Quick",1000,10));
        System.out.println("快速排序(三向切分)："+timeRandomInput("QuickThreeWay",1000,10));
    }
}
