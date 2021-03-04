package algorithm.graph;

import algorithm.search.ListNode;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Queue;

// 加权无向图
public class EdgeWeightedGraph {
    private int V; // 顶点的总数
    private int E; // 边的总数
    private ListNode<Edge> [] listNode; // 邻接表表视图
    public EdgeWeightedGraph(int V){
        if (this.V<0) throw new RuntimeException("顶点数不能为负数！");
        this.V=V;
        this.E=0;
        this.listNode = (ListNode<Edge>[]) new ListNode[this.V];
        for (int x=0;x<this.V;x++){
            this.listNode[x]=new ListNode<>();
        }
    }
    public EdgeWeightedGraph(BufferedReader reader) throws Exception{
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
                this.addEdge(new Edge(v,w,weight));
            }else {
                throw new RuntimeException("顶点输入越界！");
            }
        }
    }
    public void addEdge(Edge e){
        this.listNode[e.either()].add(e);
        this.listNode[e.other(e.either())].add(e);
        this.E++;
    }
    public int V(){return this.V;}
    public int E(){return this.E;}
    public Iterable<Edge> getAdjacent(int v){
        if (v<0||v>=this.V) return null;
        return this.listNode[v];
    }
    public Iterable<Edge> edges(){
        Queue<Edge> queue = new LinkedList<>();
        for (int x=0;x<this.listNode.length;x++){
            for (Edge m:this.listNode[x]){
                if (m.other(x)>x) queue.add(m);
            }
        }
        return queue;
    }
    public String toString(){
        String str = "顶点数:"+this.V()+"\t"+"边数:"+this.E()+"\n";
        for (Edge x:this.edges()){
            str+= x.toString()+"\n";
        }
        return str;
    }
}
