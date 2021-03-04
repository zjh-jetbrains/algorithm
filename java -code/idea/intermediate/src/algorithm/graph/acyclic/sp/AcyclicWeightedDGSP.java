package algorithm.graph.acyclic.sp;

import algorithm.graph.DijkstraSP;
import algorithm.graph.DirectedEdge;
import algorithm.graph.Edge;
import algorithm.graph.EdgeWeightedDirectedGraph;
import algorithm.search.ListNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 无环加权有向图的最短路径算法
public class AcyclicWeightedDGSP {
    private DirectedEdge [] edges;
    private double [] distTo;
    public AcyclicWeightedDGSP(EdgeWeightedDirectedGraph graph){
        this(graph,0);
    }
    public AcyclicWeightedDGSP(EdgeWeightedDirectedGraph graph,int start){
        this.edges=new DirectedEdge[graph.V()];
        this.distTo=new double[graph.V()];
        for (int x=0;x<distTo.length;x++){
            this.distTo[x]=Double.POSITIVE_INFINITY;
        }
        this.distTo[start]=0.0;
        TopologicalSort ts = new TopologicalSort(graph);
        if (ts.order()!=null){
            for (int v: ts.order()){
                this.relax(graph,v);
            }
        }
    }
    private void relax(EdgeWeightedDirectedGraph graph,int v){
        for (DirectedEdge edge:graph.getAdjacent(v)){
            int to = edge.to();
            if (this.distTo[to]>this.distTo[v]+edge.weight()){
                this.distTo[to]=this.distTo[v]+edge.weight();
                this.edges[to]=edge;
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
    public static void main(String[] args) throws Exception{
        EdgeWeightedDirectedGraph edgeWeightedGraph = new EdgeWeightedDirectedGraph(new BufferedReader(new InputStreamReader(System.in)));
        AcyclicWeightedDGSP sp = new AcyclicWeightedDGSP(edgeWeightedGraph);
        for (int x=0;x<4;x++){
            System.out.println(sp.toString(x));
        }
    }
}
