package algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// prime算法的及时实现-最小生成树
public class HardPrimeMST {
    private boolean [] marked; // 最小生成树的顶点
    private Edge [] edges; // 每个顶点距离最小生成树最近的边（0除外，因为初始时把0先加入到树中）
    private double [] dists; // 每个顶点距离树最近的边的权重
    private Map<Integer,Double> queue; // 有效的横切边(表示形式为‘顶点-权重’，即每个顶点距离当前的树的最小距离)
    public HardPrimeMST(EdgeWeightedGraph graph){
        this.marked = new boolean[graph.V()];
        this.edges = new Edge[graph.V()];
        this.dists = new double[graph.V()];
        for (int x=0;x<this.dists.length;x++){
            this.dists[x]=Double.POSITIVE_INFINITY; // 将权重数组全部初始化为正无穷
        }
        this.dists[0]=0.0; // 将顶点0距离树的最小权重初始化为0
        queue = new HashMap<>(graph.V());
        queue.put(0,0.0); // 循环前将横切边集合初始化（将顶点0加入）
        while (!queue.isEmpty()){
            this.visit(graph,(int)this.getMinValueOfKey());
        }
    }
    private void visit(EdgeWeightedGraph graph,int v){
        this.marked[v]=true;
        for (Edge e:graph.getAdjacent(v)){
            int w = e.other(v);
            if (this.marked[w]) continue;
            if (e.weight()<this.dists[w]){
                this.edges[w]=e;
                this.dists[w]=e.weight();
                this.queue.put(w,this.dists[w]);
            }
        }
    }
    public  Object getMinValueOfKey() {
        if (this.queue == null) return null;
        TreeMap<Integer,Double> tree = new TreeMap<>(this.queue); // 讲Map转化为TreeMap
        List<Map.Entry<Integer,Double>> list = new ArrayList<Map.Entry<Integer,Double >>(tree.entrySet());
        // 转化为List<Map.Entry<Integer,Double>>后，根据value进行升序排序
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue)); // 等价于(o1, o2) -> o1.getValue().compareTo(o2.getValue())
        Object obj = list.get(0).getKey(); // 获取第一个Key（value最小）
        Map.Entry<Integer, Double> remove = list.remove(0); // 删除第一个Key并获取它
        this.queue.remove(remove.getKey()); // 根据获得的Key删除横切边集合中权重最小的横切边
        return obj;
    }
    public Iterable<Edge> edges(){
        Queue<Edge> queue = new LinkedList<>();
        for (int x=1;x<this.edges.length;x++){
            queue.add(this.edges[x]);
        }
        return queue;
    }
    public double weight(){
        double t = 0.0;
        for (Edge e:this.edges()){
            t+=e.weight();
        }
        return t;
    }
    public String toString(){
        String str = "最小生成树为:"+"\n";
        for (Edge edge:this.edges()){
            str+=edge.toString()+"\n";
        }
        str+="权重之和为:"+this.weight();
        return str;
    }
    public static void main(String[] args) throws Exception{
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(new BufferedReader(new InputStreamReader(System.in)));
        HardPrimeMST mst = new HardPrimeMST(edgeWeightedGraph);
        System.out.println(mst.toString());
    }
}
