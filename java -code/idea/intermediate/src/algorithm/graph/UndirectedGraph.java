package algorithm.graph;

import algorithm.search.ListNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
// 无向图
public class UndirectedGraph {
    private int V; // 顶点的数量
    private int E; // 边的数量
    private ListNode<Integer> queue[]; // 邻接表数组
    public UndirectedGraph(int V){
        if (V<0) throw new RuntimeException("顶点数目不能为负数！");
        this.V=V;
        this.E=0;
        this.queue=(ListNode<Integer>[]) new ListNode[this.V];
        for (int x=0;x<this.V;x++){
            this.queue[x]=new ListNode<>();
        }
    }
    public UndirectedGraph(BufferedReader bufferedReader) throws Exception{
        this(Integer.parseInt(bufferedReader.readLine()));
        int temp=Integer.parseInt(bufferedReader.readLine());
        if (temp<0){throw new RuntimeException("边的数量不能为负数！");}
        for (int x=0;x<temp;x++){
            String str = bufferedReader.readLine();
            String[] s = str.split(" ");
            int v=Integer.parseInt(s[0]);
            int w=Integer.parseInt(s[1]);
            if ((v>=0&&v<this.V)&&(w>=0&&w<this.V)) this.addEdge(v,w);
            else {
                throw new RuntimeException("顶点输入越界！");
            }
        }
    }
    public void addEdge(int v,int w){
        this.queue[v].add(w);
        this.queue[w].add(v);
        this.E++;
    }
    public int getV(){return this.V;}
    public int getE(){return this.E;}
    public Iterable<Integer> getAdjacentV(int v){
        if (v<this.V&&v>=0) return this.queue[v];
        else return null;
    }
    public String toString(){
        String content = "顶点数:"+this.getV()+"\t"+"边数:"+this.getE()+"\n";
        for (int v=0;v<this.getV();v++){
            content+=v+":";
            for (int w:this.getAdjacentV(v)) {
                content+=w+" ";
            }
            content+="\n";
        }
        return content;
    }
    public static int degree(UndirectedGraph graph,int v){
        int degree=0;
        for (int m: graph.getAdjacentV(v)) {
            degree++;
        }
        return degree;
    }
    public static int maxDegree(UndirectedGraph graph){
        int max=0;
        for (int x=0;x<graph.getV();x++){
            if (degree(graph,x)>max) max=degree(graph,x);
        }
        return max;
    }
    public static double avgDegree(UndirectedGraph graph){
        return 2.0*graph.getE()/graph.getV();
    }
    public static int numberOfSelfLoops(UndirectedGraph graph){
        int selfLoops=0;
        for (int x=0;x<graph.getV();x++){
            for (int m: graph.getAdjacentV(x)){
                if (m==x) selfLoops++;
            }
        }
        return selfLoops/2;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        UndirectedGraph graph = new UndirectedGraph(bufferedReader);
        System.out.println(graph.toString());
    }
}
