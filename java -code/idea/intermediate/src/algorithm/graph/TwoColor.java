package algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 双色问题(二分图)
public class TwoColor {
    private boolean [] marked;
    private boolean [] colored;
    private boolean isTwoColorable = true;
    public TwoColor(UndirectedGraph graph){
        this.marked=new boolean[graph.getV()];
        this.colored=new boolean[graph.getV()];
        for (int x=0;x< graph.getV();x++){
            if (!this.marked[x]) this.dfs(graph,x);
        }
    }
    private void dfs(UndirectedGraph graph,int start){
        this.marked[start]=true;
        for (int w: graph.getAdjacentV(start)){
            if (!this.marked[w]){
                this.colored[w]=!this.colored[start];
                this.dfs(graph,w);
            }else if (this.colored[w]==this.colored[start]) this.isTwoColorable=false;
        }
    }
    public boolean isTwoColorable(){
        return this.isTwoColorable;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        UndirectedGraph graph = new UndirectedGraph(reader);
        TwoColor twoColor = new TwoColor(graph);
        System.out.println(twoColor.isTwoColorable());
    }
}
