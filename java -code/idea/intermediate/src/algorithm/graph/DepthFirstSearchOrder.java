package algorithm.graph;

import algorithm.search.ListNode;

import java.util.LinkedList;
import java.util.Queue;

// 深度优先搜索-顶点排序
public class DepthFirstSearchOrder {
    private boolean [] marked;
    private Queue<Integer> pre; // 前序排列
    private Queue<Integer> post; // 后序排列
    private ListNode<Integer> reversePost; // 逆后序排列(使用自定义的数据结构代替stack,因为原生stack的迭代是从栈底开始)
    public DepthFirstSearchOrder(DirectedGraph graph){
        this.pre = new LinkedList<>();
        this.post = new LinkedList<>();
        this.reversePost = new ListNode<>();
        this.marked = new boolean[graph.getV()];
        for (int x=0;x< graph.getV();x++){
            if (!this.marked[x]) this.dfs(graph,x);
        }
    }
    private void dfs(DirectedGraph graph,int start){
        this.marked[start]=true;
        this.pre.offer(start);
        for (int m: graph.getAdjacentV(start)){
            if (!this.marked[m]) this.dfs(graph,m);
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
}
