package algorithm.graph;

import algorithm.UnionFind;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
// kruskal算法-最小生成树
public class KruskalMST {
    private Queue<Edge> edges; // 最小生成树的边
    private boolean [] marked; // 标记图的顶点
    public KruskalMST(EdgeWeightedGraph graph){
        this.edges = new LinkedList<>();
        Queue<Edge> queue = new PriorityQueue<>(); // 将图的所有边添加到优先队列中
        for (Edge e: graph.edges()){
            queue.offer(e);
        }
        UnionFind uf = new UnionFind(graph.V()); // 用来识别会形成环的边
        while (!queue.isEmpty()&&this.edges.size()< graph.V()-1){
            Edge temp = queue.remove();
            int v = temp.either();
            int w = temp.other(v);
            if (uf.connected(v,w)) continue;
            uf.union(v,w); // 合并分量
            this.edges.add(temp);
        }
    }
    public Iterable<Edge> edges(){
        return this.edges;
    }
    public double weight(){
        double x=0.0;
        for (Edge edge:this.edges()){
            x+=edge.weight();
        }
        return x;
    }
    public String toString(){
        String str = "最小生成树为:"+"\n";
        for (Edge edge:this.edges()){
            str+=edge.toString()+"\n";
        }
        str+="权重之和为:"+this.weight();
        return str;
    }
    public static void main(String[] args) throws Exception{
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(new BufferedReader(new InputStreamReader(System.in)));
        KruskalMST mst = new KruskalMST(edgeWeightedGraph);
        System.out.println(mst.toString());
    }

}
