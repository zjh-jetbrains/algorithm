package algorithm.graph;

import algorithm.search.ListNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
// 广度优先搜索
public class BreadthFirstSearch {
    private boolean [] marked;
    private int [] edgeTo;
    private int start;
    public BreadthFirstSearch(UndirectedGraph graph){
        this(graph,0);
    }
    public BreadthFirstSearch(UndirectedGraph graph,int start){
        if (start<0) throw new RuntimeException("起点不能为负数！");
        this.marked=new boolean[graph.getV()];
        this.edgeTo=new int[graph.getV()];
        this.start=start;
        this.bfs(graph,start);
    }
    private void bfs(UndirectedGraph graph,int start){
        Queue<Integer> queue = new LinkedList<>();
        this.marked[start]=true;
        queue.offer(start);
        while (!queue.isEmpty()){
            int v=queue.poll();
            for (int m:graph.getAdjacentV(v)){
                if (!this.marked[m]){
                    this.marked[m]=true;
                    this.edgeTo[m]=v;
                    queue.offer(m);
                }
            }
        }
    }
    public boolean hasPathTo(int end){
        return this.marked[end];
    }
    public Iterable<Integer> pathTo(int end){
        if (!this.hasPathTo(end)) return null;
        ListNode<Integer> stack = new ListNode<>();
        for (int x=end;x!=this.start;x=this.edgeTo[x]){
            stack.add(x);
        }
        stack.add(this.start);
        return stack;
    }
    public String toString(int end){
        String temp=this.start+"->"+end+":";
        String str="";
        for (int x:this.pathTo(end)){
            str+=x+"-";
        }
        str=str.substring(0,str.length()-1);
        return temp+str;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        UndirectedGraph graph = new UndirectedGraph(reader);
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(graph);
        System.out.println(breadthFirstSearch.toString(4));
    }
}
