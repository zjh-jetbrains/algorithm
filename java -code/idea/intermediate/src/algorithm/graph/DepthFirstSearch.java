package algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
// 深度优先搜索
public class DepthFirstSearch {
    private boolean [] marked;
    private int [] edgeTo;
    private int start;// 起点
    public DepthFirstSearch(UndirectedGraph graph){
        this(graph,0);
    }
    public DepthFirstSearch(UndirectedGraph graph,int start){
        if (start<0) throw new RuntimeException("起点不能为负数！");
        this.start=start;
        this.marked=new boolean[graph.getV()];
        this.edgeTo=new int[graph.getV()];
        this.dfs(graph, start);
    }
    private void dfs(UndirectedGraph graph,int start){
        this.marked[start]=true;
        for (int w: graph.getAdjacentV(start)){
            if (!this.marked[w]){
                this.edgeTo[w]=start;
                this.dfs(graph,w);
            }
        }
    }
    public boolean hasPathTo(int end){
        return this.marked[end];
    }
    public Iterable<Integer> pathTo(int end){
        if (!this.hasPathTo(end)) return null;
        Stack<Integer> stack = new Stack<>();
        for (int x=end;x!=this.start;x=this.edgeTo[x]){
            stack.push(x);
        }
        stack.push(this.start);
        return stack;
    }
    public String toString(int end){
        String temp=this.start+"->"+end+":";
        String str="";
        for (int x:this.pathTo(end)){
            str+=x+"-";
        }
        StringBuffer stringBuffer = new StringBuffer(str.substring(0,str.length()-1));
        return temp+stringBuffer.reverse().toString();
    }

    public static void main(String[] args) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        UndirectedGraph graph = new UndirectedGraph(bufferedReader);
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph);
        System.out.println(depthFirstSearch.toString(5));
    }
}
