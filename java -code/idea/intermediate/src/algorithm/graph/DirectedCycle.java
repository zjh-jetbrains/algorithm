package algorithm.graph;

import algorithm.search.ListNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// 有向图是否存在有向环
public class DirectedCycle {
    private boolean [] marked;
    private int [] edgeTo;
    private ListNode<Integer> cycle;
    private boolean [] stack;
    public DirectedCycle(DirectedGraph graph){
        this.marked=new boolean[graph.getV()];
        this.edgeTo=new int[graph.getV()];
        this.stack=new boolean[graph.getV()];
        for (int x=0;x< graph.getV();x++){
            if (!this.marked[x]) this.dfs(graph,x);
        }
    }
    private void dfs(DirectedGraph graph,int start){
        this.marked[start]=true;
        this.stack[start]=true;
        for (int m:graph.getAdjacentV(start)){
            if (this.isHasCycle()) return;
            else if (!this.marked[m]){
                this.edgeTo[m]=start;
                this.dfs(graph, m);
            }else if (this.stack[m]){
                this.cycle=new ListNode<>();
                for (int x=start;x!=m;x=this.edgeTo[x]){
                    this.cycle.add(x);
                }
                this.cycle.add(m);
                this.cycle.add(start);
            }
        }
        this.stack[start]=false;
    }
    public boolean isHasCycle(){return this.cycle!=null;}
    public Iterable<Integer> cycle(){
        return this.cycle;
    }
    public String toString(){
        String temp = "";
        String str="有向环为:";
        if (this.cycle!=null){
            for (int n:this.cycle()){
                temp+=n+"-";
            }
            temp = temp.substring(0,temp.length()-1);
            return str+temp;
        }
        return temp+"无有向环！";
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DirectedGraph graph = new DirectedGraph(reader);
        DirectedCycle cycle = new DirectedCycle(graph);
        System.out.println(cycle.toString());
    }
}
