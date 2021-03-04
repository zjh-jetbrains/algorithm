package algorithm.graph;

// 带权重的有向边
public class DirectedEdge {
    private int v; // 起点
    private int w; // 终点
    private double weight; // 权重
    public DirectedEdge(int v,int w,double weight){
        this.v=v;
        this.w=w;
        this.weight=weight;
    }
    public int from(){
        return this.v;
    }
    public int to(){
        return this.w;
    }
    public double weight(){return this.weight;}
    public String toString(){
        return String.format("%d->%d %.2f",this.v,this.w,this.weight);
    }
}
