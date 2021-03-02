package algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// prime算法的延时实现-最小生成树
public class LazyPrimeMST {
    private boolean [] marked; // 最小生成树的顶点
    private Queue<Edge> edges; // 最小生成树的边
    private PriorityQueue<Edge> priorityQueue; // 横切边
    public LazyPrimeMST(EdgeWeightedGraph graph){
        this.marked = new boolean[graph.V()];
        edges=new LinkedList<>();
        priorityQueue = new PriorityQueue<>();
        this.visit(graph,0); // 此算法的图应是连通图
        while (!priorityQueue.isEmpty()){
            Edge temp = priorityQueue.remove(); // 获得权重最小的边并在优先队列中删除
            int v = temp.either();
            int w = temp.other(v);
            if (this.marked[v]&&this.marked[w]) continue;
            this.edges.offer(temp);
            if (!this.marked[v]) this.visit(graph,v);
            if (!this.marked[w]) this.visit(graph,w);
        }
    }
    private void visit(EdgeWeightedGraph graph,int v){
        this.marked[v]=true;
        for (Edge e: graph.getAdjacent(v)){
            if (!this.marked[e.other(v)]) this.priorityQueue.add(e);
        }
    }
    public Iterable<Edge> edges(){return this.edges;}
    public String toString(){
        String str = "最小生成树为:"+"\n";
        for (Edge edge:this.edges()){
            str+=edge.toString()+"\n";
        }
        str+="权重之和为:"+this.weight();
        return str;
    }
    public double weight(){
        double weight = 0.0;
        for (Edge e:this.edges){
            weight+=e.weight();
        }
        return weight;
    }

    public static void main(String[] args) throws Exception{
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(new BufferedReader(new InputStreamReader(System.in)));
        LazyPrimeMST mst = new LazyPrimeMST(edgeWeightedGraph);
        System.out.println(mst.toString());
    }
}
