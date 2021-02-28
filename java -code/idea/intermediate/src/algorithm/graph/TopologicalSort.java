package algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 拓扑排序
public class TopologicalSort {
    private Iterable<Integer> order;
    public TopologicalSort(DirectedGraph graph){
        DirectedCycle cycle = new DirectedCycle(graph);
        if (!cycle.isHasCycle()){ // 判断是否存在有向环
            DepthFirstSearchOrder searchOrder = new DepthFirstSearchOrder(graph);
            this.order = searchOrder.reversePost(); // 深度优先搜索的顶点的逆后序排列为拓扑排序
        }
    }
    public Iterable<Integer> order(){return this.order;}
    public boolean isDAG(){return this.order!=null;}
    public String toString(){
        String str= "";
        String temp="";
        if (this.order!=null){
            str+="拓扑排序结果为:";
            for (int x:this.order()){
                temp+=x+"-";
            }
            temp = temp.substring(0,temp.length()-1);
            return str+temp;
        }
        return "无法进行拓扑排序，存在有向环！"+str;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DirectedGraph graph = new DirectedGraph(reader);
        TopologicalSort sort = new TopologicalSort(graph);
        System.out.println(sort.toString());
    }
}
