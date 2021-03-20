package algorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiskSort {
    private BufferedReader reader ;
    private byte [] index=new byte[1000000000];
    private List<Integer> list;
    public DiskSort(String fileName){
        try{
            list = new ArrayList<>();
            this.reader = new BufferedReader(new FileReader(new File(fileName)));
            String str = reader.readLine();
            while (str!=null){
                str = str.trim();
                String [] s = str.split(" ");
                for (int x=0;x<s.length;x++){
                    this.index[Integer.parseInt(s[x])]=1;
                }
                str=reader.readLine();
            }
            for (int x=0;x<index.length;x++){
                if (this.index[x]!=0){
                    list.add(x);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printResult(){
        System.out.println(list);
    }

    public static void main(String[] args) {
        DiskSort sort = new DiskSort("C:"+File.separator+"Users"+File.separator+"zjh"+File.separator+"Desktop"+File.separator+"java"+File.separator+"data.txt");
        sort.printResult();
    }
}
