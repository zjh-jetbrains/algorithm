package algorithm.graph;

// 带权重的无向边
public class Edge implements Comparable<Edge>{
    private int v; // 边的一个顶点
    private int w; // 边的另一个顶点
    private double weight; // 边的权重
    public Edge(int v,int w,double weight){
        this.v=v;
        this.w=w;
        this.weight=weight;
    }
    public double weight(){return this.weight;}
    public int either(){return this.v;}
    public int other(int v){
        if (v==this.v) return this.w;
        else if (v==this.w) return this.v;
        else throw new RuntimeException("矛盾的边");
    }
    @Override
    public int compareTo(Edge o) {
        if (this.weight>o.weight) return 1;
        else if (this.weight<o.weight) return -1;
        else return 0;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f",this.v,this.w,this.weight);
    }
}
