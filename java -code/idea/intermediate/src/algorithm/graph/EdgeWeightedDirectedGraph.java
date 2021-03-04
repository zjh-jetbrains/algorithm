package algorithm.graph;

import algorithm.search.ListNode;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Queue;


// 加权有向图
public class EdgeWeightedDirectedGraph {
    private int V; // 顶点数
    private int E; // 边数
    private ListNode<DirectedEdge> [] tables; // 邻接表
    public EdgeWeightedDirectedGraph(int V){
        if (this.V<0) throw new RuntimeException("顶点数不能为负数！");
        this.V=V;
        this.E=0;
        this.tables=(ListNode<DirectedEdge>[]) new ListNode[this.V];
        for (int x=0;x<this.V;x++){
            this.tables[x]=new ListNode<>();
        }
    }
    public EdgeWeightedDirectedGraph(BufferedReader reader) throws Exception{
        this(Integer.parseInt(reader.readLine())); // 读取顶点数
        int temp = Integer.parseInt(reader.readLine()); // 读取边数
        if (temp<0){throw new RuntimeException("边的数量不能为负数！");}
        for (int x=0;x<temp;x++){
            String s = reader.readLine();
            String [] strs = s.split(" ");
            int v = Integer.parseInt(strs[0]); // 第一个顶点
            int w = Integer.parseInt(strs[1]); // 第二个顶点
            double weight = Double.parseDouble(strs[2]); // 权重
            if ((v>=0&&v<this.V)&&(w>=0&&w<this.V)){
                this.addEdge(new DirectedEdge(v,w,weight));
            }else {
                throw new RuntimeException("顶点输入越界！");
            }
        }
    }
    public void addEdge(DirectedEdge edge){
        int v = edge.from();
        this.tables[v].add(edge);
        this.E++;
    }
    public int V(){return this.V;}
    public int E(){return this.E;}
    public Iterable<DirectedEdge> getAdjacent(int v){
        return this.tables[v];
    }
    public Iterable<DirectedEdge> edges(){
        Queue<DirectedEdge> queue = new LinkedList<>();
        for (int x=0;x<this.tables.length;x++){
            for (DirectedEdge edge:this.tables[x]){
                queue.offer(edge);
            }
        }
        return queue;
    }
    public String toString(){
        String str = "顶点数:"+this.V()+"\t"+"边数:"+this.E()+"\n";
        for (DirectedEdge x:this.edges()){
            str+= x.toString()+"\n";
        }
        return str;
    }
}
