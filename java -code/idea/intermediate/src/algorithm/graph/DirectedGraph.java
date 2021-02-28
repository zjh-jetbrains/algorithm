package algorithm.graph;

import algorithm.search.ListNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
// 有向图
public class DirectedGraph {
    private int V; // 顶点数
    private int E; // 边数
    private ListNode<Integer> listNode[]; // 邻接表数组
    public DirectedGraph(int V){
        if (V<0) throw new RuntimeException("顶点数不能为负数！");
        this.V=V;
        this.E=0;
        this.listNode=(ListNode<Integer>[]) new ListNode[this.V];
        for (int x=0;x<this.V;x++){
            this.listNode[x]=new ListNode<>();
        }
    }
    public DirectedGraph(BufferedReader reader) throws Exception{
        this(Integer.parseInt(reader.readLine())); // 输入顶点数
        int temp = Integer.parseInt(reader.readLine()); //输入边数
        for (int x=0;x<temp;x++){
            String str = reader.readLine();
            String[] s = str.split(" ");
            int v=Integer.parseInt(s[0]);
            int w=Integer.parseInt(s[1]);
            if ((v>=0&&v<this.V)&&(w>=0&&w<this.V)) this.addEdge(v,w);
        }
    }
    public void addEdge(int v,int w){
        this.listNode[v].add(w);
        this.E++;
    }
    public int getV(){return this.V;}
    public int getE(){return this.E;}
    public DirectedGraph reverse(){
        DirectedGraph graph = new DirectedGraph(this.V);
        for (int x=0;x<this.V;x++){
            for (int m:this.getAdjacentV(x)){
                graph.addEdge(m,x);
            }
        }
        return graph;
    }
    public Iterable<Integer> getAdjacentV(int v){
        if (v<this.V&&v>=0) return this.listNode[v];
        else return null;
    }
    public String toString(){
        String content = "顶点数:"+this.getV()+"\t"+"边数:"+this.getE()+"\n";
        for (int v=0;v<this.getV();v++){
            content+=v+":";
            for (int w:this.getAdjacentV(v)) {
                content+=w+" ";
            }
            content+="\n";
        }
        return content;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DirectedGraph graph = new DirectedGraph(reader);
        System.out.println(graph.toString());
        System.out.println(graph.reverse().toString());
    }
}
