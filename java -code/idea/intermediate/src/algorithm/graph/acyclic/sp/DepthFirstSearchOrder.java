package algorithm.graph.acyclic.sp;

import algorithm.graph.DirectedEdge;
import algorithm.graph.EdgeWeightedDirectedGraph;
import algorithm.search.ListNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 基于DirectedEdge类和EdgeWeightedDirectedGraph类实现
public class DepthFirstSearchOrder {
    private boolean [] marked;
    private Queue<Integer> pre; // 前序排列
    private Queue<Integer> post; // 后序排列
    private ListNode<Integer> reversePost; // 逆后序排列(使用自定义的数据结构代替stack,因为原生stack的迭代是从栈底开始)
    public DepthFirstSearchOrder(EdgeWeightedDirectedGraph graph){
        this.pre = new LinkedList<>();
        this.post = new LinkedList<>();
        this.reversePost = new ListNode<>();
        this.marked = new boolean[graph.V()];
        for (int x=0;x< graph.V();x++){
            if (!this.marked[x]) this.dfs(graph,x);
        }
    }
    private void dfs(EdgeWeightedDirectedGraph graph,int start){
        this.marked[start]=true;
        this.pre.offer(start);
        for (DirectedEdge m: graph.getAdjacent(start)){
            int t = m.to();
            if (!this.marked[t]) this.dfs(graph,t);
        }
        this.post.offer(start);
        this.reversePost.add(start);
    }
    public Iterable<Integer> pre(){return this.pre;}
    public Iterable<Integer> post(){return this.post;}
    public Iterable<Integer> reversePost(){return this.reversePost;}
    public String toStringForRP(){
        String str= "";
        String temp="";
        if (this.reversePost!=null){
            str+="逆后序为:";
            for (int x:this.reversePost()){
                temp+=x+"-";
            }
            temp = temp.substring(0,temp.length()-1);
            temp=new StringBuilder(temp).reverse().toString();
            return str+temp;
        }
        return str;
    }

    public static void main(String[] args) throws Exception{
        EdgeWeightedDirectedGraph graph = new EdgeWeightedDirectedGraph(new BufferedReader(new InputStreamReader(System.in)));
        DepthFirstSearchOrder order = new DepthFirstSearchOrder(graph);
        System.out.println(order.toStringForRP());
    }
}
