package algorithm.search;

import java.util.*;

public class DisorderedDictionary<Key,Value>{
    private Node first;
    private int count=0;
    public DisorderedDictionary(){}
    private class Node{
        private Key key;
        private Value value;
        private Node next;
        Node(Key key,Value value,Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    public void put(Key key,Value value){
        Node temp = this.first;
        for (Node x=first;x!=null;x=x.next){
            if (x.key.equals(key)){
                x.value=value;
                return;
            }
        }
        this.first = new Node(key,value,temp);
        this.count++;
    }
    public Value get(Key key){
        for (Node x=first;x!=null;x=x.next){
            if (x.key.equals(key)){
                return x.value;
            }
        }
        return null;
    }
    public int size(){return this.count;}
    public boolean isEmpty(){return this.size()==0;}
    public Value remove(Key key){
        Node currentNode = this.first;
        Node temp = this.first.next;
        if (this.size()==1&&currentNode.key.equals(key)){
            this.first=null;
            this.count--;
            return this.first.value;
        }else if (this.size()>1){
            if (currentNode.key.equals(key)){
                this.first = this.first.next;
                this.count--;
                return this.first.value;
            }else {
                while (temp!=null){
                    if (temp.key.equals(key)){
                        if (temp.next==null){
                            currentNode.next=null;
                        }else{
                            currentNode.next=temp.next;
                        }
                        this.count--;
                        return temp.value;
                    }else {
                        currentNode=currentNode.next;
                        temp=temp.next;
                    }
                }
            }
        }
        return null;
    }
    public void toDict(){
        String str = "";
        for (Node x=this.first;x!=null;x=x.next){
            str+=x.key+":"+x.value+",";
        }
        if (str.equals("")){
            System.out.println("{"+"}");
        }else System.out.println("{"+str.substring(0,str.length()-1)+"}");
    }
    public void toListKey(){
        String s = "";
        for (Node x=first;x!=null;x=x.next){
            s+=x.key+",";
        }
        if (s.equals("")) System.out.println("["+"]");
        else System.out.println("["+s.substring(0,s.length()-1)+"]");
    }
    public void toListValue(){
        String s = "";
        for (Node x=first;x!=null;x=x.next){
            s+=x.value+",";
        }
        if (s.equals("")) System.out.println("["+"]");
        else System.out.println("["+s.substring(0,s.length()-1)+"]");
    }
    public boolean contains(Key key){
        for (Node x=first;x!=null;x=x.next){
            if (x.key.equals(key)) return true;
        }
        return false;
    }
    public Iterable<Key> keys(){
        Iterable<Key> iterable = new Iterable<Key>() {
            @Override
            public Iterator<Key> iterator() {
                return new DictionaryIterator();
            }
            class DictionaryIterator implements Iterator<Key>{
                private int i = count;
                @Override
                public boolean hasNext() {
                    return this.i>0;
                }
                private Key iter(){
                    Node temp = first;
                    Key key = temp.key;
                    if (temp.next!=null) temp=temp.next;
                    return key;
                }
                @Override
                public Key next() {
                    Key key = iter();
                    this.i--;
                    return key;
                }
            }
        };
        return iterable;
    }
    public static void main(String[] args) {
        DisorderedDictionary<Integer,String> dictionary = new DisorderedDictionary<>();
        dictionary.put(1,"zr");
        dictionary.put(2,"zjh");
        dictionary.put(3,"zx");
        dictionary.put(4,"gfc");
        dictionary.put(5,"lwx");
        dictionary.put(6,"lt");
        dictionary.toDict();
        System.out.println(dictionary.remove(4));
        dictionary.toDict();
        dictionary.toListKey();
        dictionary.put(7,"lwy");
        dictionary.toDict();
        dictionary.toListKey();
    }
}
