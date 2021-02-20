package algorithm.search;


import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

// 有序数组
public class OrderedDictionary <Key extends Comparable<Key>,Value>{
    private Key keys[];
    private Value values[];
    private int N;
    public OrderedDictionary(int capacity){
        this.keys=(Key[])new Comparable[capacity];
        this.values=(Value[])new Object[capacity];
    }
    public int getArrayLen(){return this.keys.length;}
    public int size(){return this.N;}
    public int rank(Key key){
        int left=0;
        int right=this.N-1;
        while (left<=right){
            int middle = left+(right-left)/2;
            int result = key.compareTo(this.keys[middle]);
            if (result<0) right=middle-1;
            else if (result>0) left=middle+1;
            else return middle;
        }
        return left;
    }
    public void put(Key key,Value value){
        if (this.N==this.keys.length){
            this.resize(2*this.keys.length);
        }
        int rank = this.rank(key);
        if (rank<this.N&&key.compareTo(this.keys[rank])==0) {
            this.values[rank]=value;
            return;
        }
        for (int j=N;j>rank;j--){
            this.keys[j]=this.keys[j-1];
            this.values[j]=this.values[j-1];
        }
        this.keys[rank]=key;
        this.values[rank]=value;
        this.N++;
    }
    public Value get(Key key){
        if (isEmpty()) return null;
        int rank = this.rank(key);
        if (rank<this.N&&key.compareTo(this.keys[rank])==0) return this.values[rank];
        else return null;
    }
    public boolean isEmpty(){return this.size()==0;}
    public void resize(int max){
        Key[] tempKey=(Key[]) new Comparable[max];
        Value[] tempValue=(Value[]) new Object[max];
        for (int x=0;x<this.N;x++){
            tempKey[x]=this.keys[x];
            tempValue[x]=this.values[x];
        }
        this.keys=tempKey;
        this.values=tempValue;
    }
    public void delete(Key key){
        int rank = this.rank(key);
        if (rank<this.N&&key.compareTo(this.keys[rank])==0){
            if (rank==this.N-1) {
                this.keys[this.N-1] = null;
                this.values[this.N-1] = null;
            }
            else {
                for (int m=rank;m<this.N-1;m++){
                    this.keys[m]=this.keys[m+1];
                    this.values[m]=this.values[m+1];
                }
                this.keys[this.N-1] = null;
                this.values[this.N-1] = null;
            }
            this.N--;
        }else return;
        if (this.N>0&&this.N==this.keys.length/4) this.resize(this.keys.length/2);
    }
    // 删除并返回所删除的键
    public Key deleteExplicit(Key key){
        Key result = null;
        int rank = this.rank(key);
        if (rank<this.N&&key.compareTo(this.keys[rank])==0){
            if (rank==this.N-1) {
                result = this.keys[this.N-1];
                this.keys[this.N-1] = null;
                this.values[this.N-1] = null;
            }
            else {
                result = this.keys[rank];
                for (int m=rank;m<this.N-1;m++){
                    this.keys[m]=this.keys[m+1];
                    this.values[m]=this.values[m+1];
                }
                this.keys[this.N-1] = null;
                this.values[this.N-1] = null;
            }
            this.N--;
        }else return null;
        if (this.N>0&&this.N==this.keys.length/4) this.resize(this.keys.length/2);
        return result;
    }
    public Key min(){return this.keys[0];}
    public Key max(){return this.keys[this.N-1];}
    public Key select(int k){
        if (k>this.N-1) return null;
        return this.keys[k];
    }
    public Key ceiling(Key key){
        int rank = this.rank(key);
        return this.keys[rank];
    }
    public Key floor(Key key){
        int rank = this.rank(key);
        int index=this.keys[rank]==key?rank:rank-1;
        return this.keys[index];
    }
    public void asKeyList(){
        String content="";
        Iterator<Key> keys = this.keys();
        while (keys.hasNext()){
            content+=keys.next()+",";
        }
        System.out.println("["+content.substring(0,content.length()-1)+"]");
    }
    public Iterator<Key> keys(Key left,Key right){
        Queue<Key> queue = new PriorityQueue<>();
        for (int i=this.rank(left);i<this.rank(right);i++){
            queue.add(this.keys[i]);
        }
        if (this.contains(right)) queue.add(this.keys[this.rank(right)]);
        return queue.iterator();
    }
    public Iterator<Key> keys(){
        return this.keys(this.min(),this.max());
    }
    public boolean contains(Key key){
        for (int x=0;x<this.N;x++){
            if (this.keys[x]==key) return true;
        }
        return false;
    }
    public static void main(String[] args) {
        OrderedDictionary<Character,Integer> map = new OrderedDictionary<>(2);
        map.put('P',42);
        map.put('I',22);
        map.put('H',30);
        map.put('O',43);
        map.put('D',49);
        map.put('W',72);
        map.put('E',78);
        map.put('A',40);
        map.asKeyList();
        map.delete('H');
        map.asKeyList();
    }
}
