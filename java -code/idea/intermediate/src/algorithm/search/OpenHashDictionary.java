package algorithm.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 基于拉链法的散列表
public class OpenHashDictionary<Key,Value>{
    private int M; // 散列表的大小（链表的数量）
    private int N; // 键值对数量
    private DisorderedDictionary<Key,Value> [] dictionary;
    public OpenHashDictionary(){this(997);}
    public OpenHashDictionary(int M){
        this.M=M;
        this.dictionary=(DisorderedDictionary<Key, Value>[]) new DisorderedDictionary[M];
        for (int x=0;x<M;x++){
            this.dictionary[x]=new DisorderedDictionary();
        }
    }
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % this.M;
    }
    public void put(Key key,Value value){
        this.dictionary[this.hash(key)].put(key,value);
    }
    public Value get(Key key){
        return this.dictionary[this.hash(key)].get(key);
    }
    public Value remove(Key key){
        return this.dictionary[this.hash(key)].remove(key);
    }
    public Iterator<Key> keys(){
        Iterable<Key> keys = null;
        List<Key> list = new ArrayList<Key>();
        for (int x=0;x<this.M;x++){
            keys = this.dictionary[x].keys();
            Iterator<Key> iterator = keys.iterator();
            while (iterator.hasNext()){
                list.add(iterator.next());
            }
        }
        return list.iterator();
    }
    public static void main(String[] args) {
        OpenHashDictionary<String,Integer> hashDictionary = new OpenHashDictionary<>();
        hashDictionary.put("zr",1);
        hashDictionary.put("zjh",2);
        hashDictionary.put("zx",3);
        hashDictionary.put("y2j",4);
        hashDictionary.put("edge",5);
        hashDictionary.put("lwx",6);
        hashDictionary.put("jkl",7);
        Iterator<String> keys = hashDictionary.keys();
        while (keys.hasNext()){
            System.out.print(keys.next()+" ");
        }
    }
}
