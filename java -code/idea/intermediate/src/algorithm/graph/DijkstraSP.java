package algorithm.graph;

import algorithm.search.ListNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class DijkstraSP {
    private DirectedEdge [] edges;
    private double [] distTo;
    private Map<Integer,Double> pq;
    public DijkstraSP(EdgeWeightedDirectedGraph graph){
        this(graph,0);
    }
    public DijkstraSP(EdgeWeightedDirectedGraph graph,int start){
        if (start<0||start>= graph.V()) throw new RuntimeException("顶点不在范围内");
        this.edges=new DirectedEdge[graph.V()];
        this.distTo=new double[graph.V()];
        this.pq=new HashMap<>(graph.V());
        for (int x=0;x< graph.V();x++){
            this.distTo[x]=Double.POSITIVE_INFINITY;
        }
        this.distTo[start]=0.0;
        this.pq.put(start,0.0);
        while (!this.pq.isEmpty()){
            this.relax(graph,(int)this.getMinValueOfKey());
        }
    }
    private void relax(EdgeWeightedDirectedGraph graph,int v){
        for (DirectedEdge edge:graph.getAdjacent(v)){
            int to = edge.to();
            if (this.distTo[to]>this.distTo[v]+ edge.weight()){
                this.distTo[to]=this.distTo[v]+edge.weight();
                this.edges[to]=edge;
                this.pq.put(to,this.distTo[to]);
            }
        }
    }

    /**
     * 删除map（pq）中value最小的项并且返回该项的key
     * @return:返回key
     */
    private Object getMinValueOfKey() {
        if (this.pq == null) return null;
        TreeMap<Integer,Double> tree = new TreeMap<>(this.pq); // 将Map转化为TreeMap
        List<Map.Entry<Integer,Double>> list = new ArrayList<Map.Entry<Integer,Double >>(tree.entrySet());
        // 转化为List<Map.Entry<Integer,Double>>后，根据value进行升序排序
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue)); // 等价于(o1, o2) -> o1.getValue().compareTo(o2.getValue())
        Object obj = list.get(0).getKey(); // 获取第一个Key（value最小）
        Map.Entry<Integer, Double> remove = list.remove(0); // 删除第一个Key并获取它
        this.pq.remove(remove.getKey()); // 根据获得的Key删除横切边集合中权重最小的横切边
        return obj;
    }
    public double distTo(int v){return this.distTo[v];}
    public boolean hasPathTo(int v){return this.distTo(v)<Double.POSITIVE_INFINITY;}
    public Iterable<DirectedEdge> pathTo(int v){
        ListNode<DirectedEdge> stack = new ListNode<>();
        for (DirectedEdge edge = this.edges[v];edge!=null;edge=this.edges[edge.from()]){
            stack.add(edge);
        }
        return stack;
    }
    public String toString(int v){
        String str = "从起点到"+v+"的最短路径为:"+"\n";
        for (DirectedEdge edge:this.pathTo(v)){
            str+=edge.toString()+"\n";
        }
        str+="权重之和为:"+this.distTo(v);
        return str;
    }

    public static void main(String[] args) throws Exception{
        EdgeWeightedDirectedGraph edgeWeightedGraph = new EdgeWeightedDirectedGraph(new BufferedReader(new InputStreamReader(System.in)));
        DijkstraSP sp = new DijkstraSP(edgeWeightedGraph);
        for (int x=0;x<8;x++){
            System.out.println(sp.toString(x));
        }
    }
}
