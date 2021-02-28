package algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 深度优先搜索-强连通分量(有向图) Kosaraju算法
public class StrongConnectedComponent {
    private boolean [] marked; // 标记访问的顶点
    private int [] id; // 顶点所在的强连通分量的标识符
    private int count; // 强连通分量的个数
    public StrongConnectedComponent(DirectedGraph graph){
        this.marked=new boolean[graph.getV()];
        this.id=new int[graph.getV()];
        DepthFirstSearchOrder dfs = new DepthFirstSearchOrder(graph.reverse());
        for (int h:dfs.reversePost()){
            if (!this.marked[h]){
                this.dfs(graph,h);
                this.count++;
            }
        }
    }
    private void dfs(DirectedGraph graph,int start){
        this.marked[start]=true;
        this.id[start]=this.count;
        for (int m:graph.getAdjacentV(start)){
            if (!this.marked[m]){
                this.dfs(graph,m);
            }
        }
    }
    public boolean isStrongConnected(int v,int w){
        if ((v>=0&&v<this.marked.length)&&(w>=0&&w<this.marked.length)) return this.id[v]==this.id[w];
        return false;
    }
    public int count(){return this.count;}
    public int id(int v){
        if (v>=0&&v<this.marked.length) return this.id[v];
        return -1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DirectedGraph graph = new DirectedGraph(reader);
        StrongConnectedComponent scc = new StrongConnectedComponent(graph);
        System.out.println(scc.count);
        System.out.println(scc.id(5));
        System.out.println(scc.isStrongConnected(0,4));
    }
}
