package algorithm.search;

import java.util.*;

// 基于线性探测法的散列表
public class LinearProbingHashDictionary <Key,Value>{
    private int M;
    private int N;
    private Key keys[];
    private Value values[];
    public LinearProbingHashDictionary(){
        this(16);
    }
    public LinearProbingHashDictionary(int capacity){
        this.M=capacity;
        this.keys=(Key[]) new Object[this.M];
        this.values=(Value []) new Object[this.M];
    }
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }
    private void resize(int capacity){
        LinearProbingHashDictionary<Key,Value> temp = new LinearProbingHashDictionary<>(capacity);
        for (int x=0;x<this.M;x++){
            if (this.keys[x]!=null) temp.put(keys[x],values[x]);
        }
        this.keys=temp.keys;
        this.values=temp.values;
        this.M=temp.M;
    }
    public void put(Key key,Value value){
        if (this.N>=this.M/2) this.resize(2*this.M);
        int i;
        for (i=this.hash(key);this.keys[i]!=null;i=(i+1)%this.M){
            if (key.equals(this.keys[i])) {
                this.values[i]=value;
                return;
            }
        }
        this.keys[i]=key;
        this.values[i]=value;
        this.N++;
    }
    public Value get(Key key){
        for (int i=this.hash(key);this.keys[i]!=null;i=(i+1)%this.M){
            if (key.equals(this.keys[i])) return this.values[i];
        }
        return null;
    }
    public void remove(Key key){
        if (!this.contains(key)) return;
        int i = this.hash(key);
        while (!key.equals(this.keys[i])){
            i = (i+1)%this.M;
        }
        keys[i]=null;
        values[i]=null;
        i=(i+1)%this.M;
        while (this.keys[i]!=null){
            Key tempKey = this.keys[i];
            Value tempValue = this.values[i];
            this.keys=null;
            this.values=null;
            this.N--;
            this.put(tempKey,tempValue);
            i=(i+1)%this.M;
        }
        this.N--;
        if (this.N>0&&this.N==this.M/2) this.resize(this.M/2);
    }
    public Iterator<Key> keys(){
        Queue<Key> queue = new LinkedList<>();
        for (int x=0;x<this.keys.length;x++){
            if (this.keys[x]!=null) queue.add(this.keys[x]);
        }
        return queue.iterator();
    }
    public boolean contains(Key key){
        for (int x=0;x<this.M;x++){
            if (key.equals(this.keys[x])) return true;
        }
        return false;
    }
    public int size(){return this.N;}
    public boolean isEmpty(){return this.size()==0;}
    public static void main(String[] args) {
        LinearProbingHashDictionary<String,Integer> linearProbingHashDictionary = new LinearProbingHashDictionary<>();
        linearProbingHashDictionary.put("zx",1);
        linearProbingHashDictionary.put("zjh",2);
        linearProbingHashDictionary.put("zr",3);
        linearProbingHashDictionary.put("rxy",4);
        linearProbingHashDictionary.put("dsm",5);
        linearProbingHashDictionary.put("lsq",6);
        linearProbingHashDictionary.put("valid",7);
        Iterator<String> keys = linearProbingHashDictionary.keys();
        while (keys.hasNext()){
            System.out.print(keys.next()+" ");
        }
    }
}
