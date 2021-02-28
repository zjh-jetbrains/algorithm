package algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 深度优先搜索-有向图的可达性问题
public class DirectedDFS {
    private boolean [] marked;
    public DirectedDFS(DirectedGraph graph,int start){
        if (start<0) throw new RuntimeException("起点不能为负数！");
        this.marked=new boolean[graph.getV()];
        this.dfs(graph, start);
    }
    public DirectedDFS(DirectedGraph graph,Iterable<Integer> sources){
        this.marked=new boolean[graph.getV()];
        for (int s:sources){
            if (!this.marked[s]&&(s<graph.getV()&&s>=0)) this.dfs(graph,s);
        }
    }
    private void dfs(DirectedGraph graph,int start){
        this.marked[start]=true;
        for (int m: graph.getAdjacentV(start)){
            if (!this.marked[m]) this.dfs(graph,m);
        }
    }
    public boolean isMarked(int v){
        return this.marked[v];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DirectedGraph graph = new DirectedGraph(reader);
        DirectedDFS directedDFS = new DirectedDFS(graph,0);
        for (int x=1;x< graph.getV();x++){
            System.out.println("0->"+x+":"+directedDFS.isMarked(x));
        }
    }
}
