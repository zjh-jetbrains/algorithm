package algorithm.search;

import java.util.Iterator;
// 无序链表实现动态数组
public class ListNode<T> implements Iterable<T> {
    private Node head;
    private int count=0;
    public ListNode(){}
    public void add(T value){
        Node temp = this.head;
        this.head = new Node(value,temp);
        this.count++;
    }
    public boolean remove(T value){
        Node currentNode = this.head;
        Node temp = this.head.next;
        if (this.count==1&&currentNode.value.equals(value)){
            head = null;
            this.count--;
            return true;
        }else if (this.count>1){
            if (currentNode.value.equals(value)){
                head = head.next;
                this.count--;
                return true;
            }else {
                while (temp!=null){
                    if (temp.value.equals(value)){
                        if (temp.next==null){
                            currentNode.next = null;
                        }else {
                            currentNode.next=temp.next;
                        }
                        this.count--;
                        return true;
                    }else {
                        currentNode = currentNode.next;
                        temp = temp.next;
                    }
                }
            }
        }
        return false;
    }
    public T get(int index){
        T value = null;
        if (index>=this.size()||index<0) throw new RuntimeException("下标越界!");
        int temp=0;
        for (Node x=this.head;x!=null;x=x.next){
            if (temp==index){
                value = x.value;
                break;
            }
            temp++;
        }
        return value;
    }
    public void set(int index,T value){
        if (index>=this.size()||index<0) throw new RuntimeException("下标越界!");
        int temp=0;
        for (Node x=this.head;x!=null;x=x.next){
            if (temp==index){
                x.value = value;
                break;
            }
            temp++;
        }
    }
    public void toArray(){
        String str = "";
        for (Node x=this.head;x!=null;x=x.next){
            str+=x.value+",";
        }
        if (str.equals("")) System.out.println("[]");
        else System.out.println("["+str.substring(0,str.length()-1)+"]");
    }
    public boolean contains(T value){
        for (Node x=this.head;x!=null;x=x.next){
            if (x.value.equals(value)){
                return true;
            }
        }
        return false;
    }
    @Override
    public Iterator<T> iterator() {
        return new ListNodeIterator();
    }
    public int size(){return this.count;}
    public boolean isEmpty(){return this.size()==0;}
    private class ListNodeIterator implements Iterator<T>{
        private int i = count;
        private Node temp= head;
        @Override
        public boolean hasNext() {
            return this.i>0;
        }
        @Override
        public T next() {
            T val = temp.value;
            temp=temp.next;
            this.i--;
            return val;
        }
    }
    private class Node{
        private T value;
        private Node next;
        Node(T value,Node next){
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode<Integer> listNode = new ListNode<>();
        listNode.add(1);
        listNode.add(7);
        listNode.add(3);
        listNode.add(8);
        listNode.add(4);
        listNode.add(6);
        listNode.add(2);
        Iterator<Integer> iterator = listNode.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
