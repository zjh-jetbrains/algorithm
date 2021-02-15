package algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 堆排序
public class HeapSort<key extends Comparable<key>> {
    private key[] pq;// 二叉堆数组(完全二叉树)
    private int N=0;
    public HeapSort(int length){
        this.pq = (key[]) new Comparable[length+1];
    }
    public HeapSort(){
        this(1);
    }
    public HeapSort(key [] temp){
        this(temp.length);
        for (int x=0;x<temp.length;x++){
            this.insert(temp[x]);
        }
    }
    /**
     * 交换数组中的两个元素
     * @param i:第一个下标
     * @param j:第二个下标
     */
    private void swap(int i, int j){
        key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    private static void swapForSort(Comparable a[],int i,int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    /**
     *对两个元素进行比较
     */
    private boolean less(int a, int b){
        return pq[a].compareTo(pq[b])<0;
    }
    private static boolean less(Comparable a[],int i,int j){
        return a[i].compareTo(a[j])<0;
    }
    /**
     *输出数组
     */
    public void show(){
        List<key> list = new ArrayList<>();
        while (!this.isNull()){
            list.add(this.delMaxValue());
        }
        System.out.println(Arrays.toString(list.toArray()));
    }
    /**
     * 判断数组是否已排序
     */
    public boolean isSorted(){
        for (int x=2;x< this.pq.length;x++){
            if (less(x,x-1)) return false;
        }
        return true;
    }
    public static boolean isSorted(Comparable a[]){
        for (int x=2;x<a.length;x++){
            if (!less(a,x-1,x)) return false;
        }
        return true;
    }
    private void swim(int k){// 上浮
        while (k>1&&less(k/2,k)){
            swap(k/2,k);
            k = k/2;
        }
    }
    private void resize(int max){// 数组动态扩充
        key [] temp = (key []) new Comparable[max+1];
        for (int x=0;x<=this.N;x++){
            temp[x] = this.pq[x];
        }
        this.pq = temp;
    }
    private void sink(int k){// 下沉
        while (2*k<=this.N){
            int j = 2*k;
            if (j<N&&less(j,j+1)) j++;
            if (!less(k,j)) break;
            swap(k,j);
            k = j;
        }
    }

    /**
     * 下沉，用于构造二叉堆和进行排序（完全二叉树）
     * @param a:待构造和排序的数组（实现了Comparable接口）
     * @param k:待下沉的索引
     * @param j:二叉堆的长度或数组的长度减去一
     */
    private static void sink(Comparable a[],int k,int j){
        while (2*k<=j){// 如果存在子结点则进入循环（即未到堆底）
            int temp = 2*k;// 左边结点
            if (temp<j&&less(a,temp,temp+1)) temp++;// 如果存在右结点，并且右结点的值大于左结点，则将左结点的索引加一（即接下来比较右结点和父结点的大小）
            if (!less(a,k,temp)) break;// 如果父结点大于子结点则结束循环，停止下沉过程
            swapForSort(a,k,temp);// 交换父结点和两个子结点之中的最大结点
            k = temp;// 将最大子结点的索引赋值给代表父结点的索引
        }
    }
    public void insert(key value){// 向二叉堆中添加元素
        if (this.N==this.pq.length-1) this.resize(2*this.pq.length);
        this.pq[++this.N] = value;
        this.swim(this.N);
    }
    public key delMaxValue(){// 删除最大值并返回这个值
        key temp = this.pq[1];
        this.swap(1,this.N--);
        this.pq[N+1] = null;// 防止对象游离
        if (this.N>0&&this.N==this.pq.length/4) this.resize(this.pq.length/2);
        this.sink(1);
        return temp;
    }
    public int size(){return this.N;}
    public boolean isNull() {return this.N==0;}
    public static void sort(Comparable a[]){
        int n = a.length-1;
        for (int k=n/2;k>=1;k--){
            sink(a,k,n);
        }
        while (n>1){
            swapForSort(a,1,n--);
            sink(a,1,n);
        }
    }
    public static void asList(Comparable a[]){
        String str="[";
        String split="";
        for (int x=1;x< a.length;x++){
            if (x== a.length-1) split+=a[x];
            else split+=a[x]+"、";
        }
        System.out.println(str+split+"]");
    }
    public static void main(String[] args) {
//        Character character[] = new Character[]{'D','R','G','V','A'};
//        HeapSort<Character> heapSort = new HeapSort<>(character);
//        heapSort.insert('N');
//        heapSort.insert('M');
//        heapSort.insert('K');
//        heapSort.insert('Z');
//        heapSort.insert('B');
//        heapSort.insert('D');
//        heapSort.insert('O');
//        heapSort.insert('T');
//        heapSort.insert('K');
//        System.out.println(heapSort.size());
//        System.out.println(heapSort.delMaxValue());
//        heapSort.show();
        Character character[] = new Character[]{null,'R','G','V','A','P','T','B'};// 从数组的第一个元素开始排序，所以第零个元素置为空
        HeapSort.sort(character);
        System.out.println(HeapSort.isSorted(character));
        HeapSort.asList(character);
    }
}
