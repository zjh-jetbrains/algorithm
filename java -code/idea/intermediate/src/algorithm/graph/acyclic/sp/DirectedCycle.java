package algorithm.graph.acyclic.sp;

import algorithm.graph.DirectedEdge;
import algorithm.graph.EdgeWeightedDirectedGraph;
import algorithm.search.ListNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 基于DirectedEdge类和EdgeWeightedDirectedGraph类实现
public class DirectedCycle {
    private boolean [] marked;
    private DirectedEdge [] edgeTo;
    private ListNode<DirectedEdge> cycle;
    private boolean [] stack;
    public DirectedCycle(EdgeWeightedDirectedGraph graph){
        this.marked=new boolean[graph.V()];
        this.edgeTo=new DirectedEdge[graph.V()];
        this.stack=new boolean[graph.V()];
        for (int x=0;x< graph.V();x++){
            if (!this.marked[x]) this.dfs(graph,x);
        }
    }
    private void dfs(EdgeWeightedDirectedGraph graph,int start){
        this.marked[start]=true;
        this.stack[start]=true;
        for (DirectedEdge m:graph.getAdjacent(start)){
            int t = m.to();
            if (this.isHasCycle()) return;
            else if (!this.marked[t]){
                this.edgeTo[t]=m;
                this.dfs(graph, t);
            }else if (this.stack[t]){
                this.cycle=new ListNode<>();
                for (DirectedEdge x=m;x!=null;x=this.edgeTo[x.from()]){
                    this.cycle.add(x);
                }
            }
        }
        this.stack[start]=false;
    }
    public boolean isHasCycle(){return this.cycle!=null;}
    public Iterable<DirectedEdge> cycle(){
        return this.cycle;
    }
    public String toString(){
        String temp = "";
        String str="有向环为:"+"\n";
        if (this.cycle!=null){
            for (DirectedEdge n:this.cycle()){
                temp+=n.toString()+"\n";
            }
            temp = temp.substring(0,temp.length()-1);
            return str+temp;
        }
        return temp+"无有向环！";
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        EdgeWeightedDirectedGraph graph = new EdgeWeightedDirectedGraph(reader);
        DirectedCycle cycle = new DirectedCycle(graph);
        System.out.println(cycle.toString());
    }
}
