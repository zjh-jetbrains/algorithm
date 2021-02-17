package algorithm;
//动态连通性问题

public class UnionFind {
    /**
     * component:连通分量的个数
     * id:每个点的值
     * sizes:最初每个结点都作为一个根节点构成一棵树，所以默认值为1
     */
    private int component;
    private int [] id;
    private int [] sizes;

    /**
     * 构造函数进行初始化
     * @param N:表示有N个结点，所以最初有N个连通分量
     */
    public UnionFind(int N){
        this.id = new int[N];
        this.sizes = new int[N];
        this.component = N;
        for (int x = 0;x< id.length;x++){
            this.id[x] = x;
        }
        for (int x=0;x< sizes.length;x++){
            this.sizes[x] = 1;
        }
    }

    /**
     * 将两个结点进行连接
     * @param p:代表第一个结点
     * @param q:代表第二个结点
     */
    public void union(int p, int q){
        int pValue = this.find(p);
        int qValue = this.find(q);
        if (this.connected(p,q)) return;
        if (this.sizes[pValue]<this.sizes[qValue]){
            this.id[pValue] = qValue;
            this.sizes[qValue] += this.sizes[pValue];
        }else {
            this.id[qValue] = pValue;
            this.sizes[pValue] += this.sizes[qValue];
        }
        this.component--;
    }

    /**
     * p所在分量的值
     * @param p:表示一个结点
     * @return:返回p对应的分量的值
     */
    public int find(int p){
        while (p!=this.id[p]) p=this.id[p];
        return p;
    }

    /**
     * 判断两个结点是否已经相连
     * @param p:表示一个结点
     * @param q:表示一个结点
     * @return:如果已经相连返回true，否则返回false
     */
    public boolean connected(int p, int q){
        return this.find(p)==this.find(q);
    }

    /**
     * 计算连通分量的个数
     * @return:返回连通分量的个数
     */
    public int count(){
        return this.component;
    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(20);  //0~N-1
        unionFind.union(0,1);
        unionFind.union(1,2);
        unionFind.union(5,6);
        unionFind.union(9,19);
        unionFind.union(0,10);
        unionFind.union(13,14);
        unionFind.union(3,13);
        System.out.println(unionFind.count());
    }
}
