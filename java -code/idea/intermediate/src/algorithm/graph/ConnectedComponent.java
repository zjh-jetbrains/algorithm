package algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 深度优先搜索-连通分量(无向图)
public class ConnectedComponent {
    private boolean [] marked;
    private int [] id;
    private int count;
    public ConnectedComponent(UndirectedGraph graph){
        this.marked=new boolean[graph.getV()];
        this.id=new int[graph.getV()];
        for (int x=0;x< graph.getV();x++){
            if (!this.marked[x]){
                this.dfs(graph,x);
                this.count++;
            }
        }
    }
    private void dfs(UndirectedGraph graph,int start){
        this.marked[start]=true;
        this.id[start]=this.count;
        for (int m: graph.getAdjacentV(start)){
            if (!this.marked[m]){
                this.dfs(graph,m);
            }
        }
    }
    public boolean isConnected(int v,int w){
        return this.id[v]==this.id[w];
    }
    public int getId(int v){return this.id[v];}
    public int count(){return this.count;}
    private Iterable<Integer>[] iterable(){
        Queue<Integer> queue[] = (Queue<Integer>[]) new LinkedList[this.count];
        for (int x=0;x< queue.length;x++){
            queue[x]=new LinkedList<>();
        }
        for (int x=0;x<this.id.length;x++){
            queue[this.getId(x)].offer(x);
        }
        return queue;

    }
    public String toString(){
        Iterable<Integer>[] iterable = this.iterable();
        String str=this.count+"个连通分量:"+"\n";
        for (int x=0;x< iterable.length;x++){
            for (int m:iterable[x]){
                str+=m+" ";
            }
            str+="\n";
        }
        return str;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        UndirectedGraph graph = new UndirectedGraph(reader);
        ConnectedComponent cc = new ConnectedComponent(graph);
        System.out.println(cc.toString());
    }
}
