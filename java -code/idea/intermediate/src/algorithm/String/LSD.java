package algorithm.String;

import java.util.Arrays;

// 低位优先的字符串排序
public class LSD {
    /**
     *
     * @param array:此数组为待排序的数组，并且该数组中的每个元素的长度应该相同，否则会报错
     * @param amount:通过前amount个字符进行排序
     * @param R:字符集的基数(例如扩展ASCII字符集的基数为256)
     */
    public static void sort(String [] array,int amount,int R){
        if (amount<=0||amount>array.length) throw new RuntimeException("parameter error!");
        String [] temp = new String[array.length]; // 用于之后暂存数据
        for (int d=amount-1;d>=0;d--){
            int [] count = new int[R+1]; // 计算出现的频率
            for (int i=0;i<array.length;i++){
                count[array[i].charAt(d)+1]++;
            }
            for (int r=0;r<R;r++){ // 将频率转化为索引
                count[r+1]+=count[r];
            }
            for (int x=0;x<array.length;x++){ // 将元素分类
                temp[count[array[x].charAt(d)]++]=array[x];
            }
            for (int y=0;y<array.length;y++){ // 回写
                array[y]=temp[y];
            }
        }
    }

    public static void main(String[] args) {
        String [] arrays = new String[]{"adds1s","asdcx3","45asdf","sdsaf1","drfsqw","45ds1r","asdcdf"};
        LSD.sort(arrays,6,256);
        System.out.println(Arrays.toString(arrays));
    }
}
