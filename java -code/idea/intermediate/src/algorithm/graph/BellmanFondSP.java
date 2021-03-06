package algorithm.graph;

import algorithm.graph.acyclic.sp.DirectedCycle;
import algorithm.search.ListNode;

import java.util.LinkedList;
import java.util.Queue;

public class BellmanFondSP {
    private DirectedEdge [] edges; // 各个顶点距离起点的最短路径的最后一条边
    private double [] distTo; // 各个顶点距离起点的最短距离
    private boolean [] isOnQueue; // 各个顶点是否在队列中
    private Queue<Integer> queue; // 各个顶点依次添加到队列中
    private int count; // 顶点放松的次数
    private Iterable<DirectedEdge> cycle; // edges[]中是否存在负权重的有向环
    public BellmanFondSP(EdgeWeightedDirectedGraph graph){
        this(graph,0);
    }
    public BellmanFondSP(EdgeWeightedDirectedGraph graph,int start){
        if (start<0||start>= graph.V()) throw new RuntimeException("起点越界！");
        this.edges=new DirectedEdge[graph.V()];
        this.distTo=new double[graph.V()];
        this.isOnQueue=new boolean[graph.V()];
        this.queue = new LinkedList<>();
        for (int x=0;x<this.distTo.length;x++){
            this.distTo[x]=Double.POSITIVE_INFINITY;
        }
        this.distTo[start]=0.0;
        this.queue.offer(start);
        this.isOnQueue[start]=true;
        while (!this.queue.isEmpty()&&!this.isHasNegativeCycle()){
            int remove = this.queue.remove();
            this.isOnQueue[remove]=false;
            this.relax(graph,remove);
        }
    }
    private void relax(EdgeWeightedDirectedGraph graph,int v){
        for (DirectedEdge edge: graph.getAdjacent(v)){
            int to = edge.to();
            if (this.distTo[to]>this.distTo[v]+edge.weight()){
                this.distTo[to]=this.distTo[v]+edge.weight();
                this.edges[to]=edge;
                if (!this.isOnQueue[to]){
                    this.queue.offer(to);
                    this.isOnQueue[to]=true;
                }
                if (this.count++%graph.V()==0){
                    this.findNegativeCycle();
                }
            }
        }
    }
    public double distTo(int v){return this.distTo[v];}
    public boolean hasPathTo(int v){return this.distTo(v)<Double.POSITIVE_INFINITY;}
    public Iterable<DirectedEdge> pathTo(int v){
        if (!this.hasPathTo(v)) return null;
        ListNode<DirectedEdge> stack = new ListNode<>();
        for (DirectedEdge edge = this.edges[v];edge!=null;edge=this.edges[edge.from()]){
            stack.add(edge);
        }
        return stack;
    }
    public String toString(int v){
        String str = "从起点到"+v+"的最短路径为:"+"\n";
        for (DirectedEdge edge:this.pathTo(v)){
            str+=edge.toString()+"\n";
        }
        str+="权重之和为:"+this.distTo(v);
        return str;
    }
    private void findNegativeCycle(){
        int V = this.edges.length;
        EdgeWeightedDirectedGraph graph = new EdgeWeightedDirectedGraph(V);
        for (int x=0;x<V;x++){
            if (this.edges[x]!=null){
                graph.addEdge(edges[x]);
            }
        }
        DirectedCycle directedCycle = new DirectedCycle(graph);
        this.cycle= directedCycle.cycle();
    }
    public boolean isHasNegativeCycle(){
        return this.cycle!=null;
    }
    public Iterable<DirectedEdge> negativeCycle(){
        if (this.isHasNegativeCycle()){
            return this.cycle;
        }
        return null;
    }
}
