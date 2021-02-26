package algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 无环图问题
public class Cycle {
    private boolean [] marked;
    private boolean hasCycle;
    public Cycle(UndirectedGraph graph){
        this.marked=new boolean[graph.getV()];
        for (int x=0;x< graph.getV();x++){
            if (!this.marked[x]){
                this.dfs(graph,x,x);
            }
        }
    }
    private void dfs(UndirectedGraph graph,int v,int u){
        this.marked[v]=true;
        for (int m:graph.getAdjacentV(v)){
            if (!this.marked[m]){
                this.dfs(graph,m,v);
            }else if (m!=u) this.hasCycle=true;
        }
    }
    public boolean isHasCycle(){
        return this.hasCycle;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        UndirectedGraph graph = new UndirectedGraph(reader);
        Cycle cycle = new Cycle(graph);
        System.out.println(cycle.isHasCycle());

    }
}
