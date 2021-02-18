package algorithm.search;

import java.util.*;

public class BinarySearchTreeDictionary<Key extends Comparable<Key>,Value> {
    private Node root;
    public void put(Key key,Value value){
        this.root=this.put(this.root,key,value);
    }
    private Node put(Node x,Key key,Value value){
        if (x==null) return new Node(key,value,1);
        int result = key.compareTo(x.key);
        if (result<0) x.left = put(x.left,key,value);
        else if (result>0) x.right = put(x.right,key,value);
        else x.value=value;
        x.N= size(x.left)+size(x.right)+1;
        return x;
    }
    public Value get(Key key){
        return this.get(root,key);
    }
    private Value get(Node x,Key key){
        if (x==null) return null;
        int result = key.compareTo(x.key);
        if (result<0) return this.get(x.left,key);
        else if (result>0) return this.get(x.right,key);
        else return x.value;
    }
    // 获取最小的键
    public Key min(){
        return getMinKey();
    }
    private Key getMinKey(){
        if (this.root==null) return null;
        if (this.root.left==null) return this.root.key;
        Node temp = this.root.left;
        Key minKey = null;
        while (temp!=null){
            minKey = temp.key;
            temp = temp.left;
        }
        return minKey;
    }
    private Node min(Node node){
        if (node==null) return null;
        if (node.left==null) return node;
        Node temp = this.min(node.left);
        return temp;
    }
    // 获取最大的键
    public Key max(){
        return getMaxKey();
    }
    private Key getMaxKey(){
        if (this.root==null) return null;
        if (this.root.right==null) return this.root.key;
        Node temp = this.root.right;
        Key maxKey = null;
        while (temp!=null){
            maxKey = temp.key;
            temp=temp.right;
        }
        return maxKey;
    }
    public int size(){
        return this.size(root);
    }
    private int size(Node x){
        if (x==null) return 0;
        else return x.N;
    }
    // 获取小于等于给定键的最大值（向下取整）
    public Key floor(Key key){
        Node temp = this.floor(this.root,key);
        if (temp==null) return null;
        return temp.key;
    }
    private Node floor(Node node,Key key){
        if (node==null) return null;
        int result = key.compareTo(node.key);
        if (result==0) return node;
        else if (result<0) return this.floor(node.left,key);
        else{
            Node temp = this.floor(node.right,key);
            if (temp!=null) return temp;
            else return node;
        }
    }
    // 大于等于给定键的最小值（向上取整）
    public Key ceiling(Key key){
        Node x = this.ceiling(this.root,key);
        if (x==null) return null;
        return x.key;
    }
    private Node ceiling(Node node,Key key){
        if (node==null) return null;
        int result = key.compareTo(node.key);
        if (result==0) return node;
        else if (result>0) return this.ceiling(node.right,key);
        else {
            Node temp = this.ceiling(node.left,key);
            if (temp!=null) return temp;
            else return node;
        }
    }
    // 找到排名为k的键（从0开始）
    public Key select(int k){
        Node x = this.select(this.root,k);
        if (x==null) return null;
        return x.key;
    }
    private Node select(Node node,int k){
        if (node==null) return null;
        int temp = this.size(node.left);
        if (temp==k) return node;
        else if (temp>k) return this.select(node.left,k);
        else return this.select(node.right,k-temp-1);
    }
    // 计算键的排名（从0开始）
    public int rank(Key key){
        return this.rank(this.root,key);
    }
    // 删除最小的键
    public void deleteMin(){
        this.root=deleteMin(this.root);
    }
    private Node deleteMin(Node node){
        if(node==null) return null;
        if (node.left==null) return node.right;
        node.left = this.deleteMin(node.left);
        node.N = this.size(node.left)+this.size(node.right)+1;
        return node;
    }
    // 删除最大的键
    public void deleteMax(){
        this.root = this.deleteMax(this.root);
    }
    private Node deleteMax(Node node){
        if (node==null) return null;
        if (node.right==null) return node.left;
        node.right = this.deleteMax(node.right);
        node.N = this.size(node.left)+this.size(node.right)+1;
        return node;
    }
    // 删除指定的键
    public void delete(Key key){
        this.root = this.delete(this.root,key);
    }
    private Node delete(Node node,Key key){
        if (node==null) return null;
        int result = key.compareTo(node.key);
        if (result<0) node.left = this.delete(node.left,key);
        else if (result>0) node.right = this.delete(node.right,key);
        else {
            if (node.right==null) return node.left;
            if (node.left==null) return node.right;
            Node temp = node;
            node = this.min(temp.right);
            node.right = this.deleteMin(temp.right);
            node.left = temp.left;
        }
        node.N = this.size(node.left)+this.size(node.right)+1;
        return node;
    }
    // 中序遍历所有的键
    private void print(Node node){
        if (node==null) return;
        this.print(node.left);
        System.out.print(node.key+" ");
        this.print(node.right);
    }
    private int rank(Node node,Key key){
        if (node==null) return -1;
        int temp = key.compareTo(node.key);
        if (temp==0) return this.size(node.left);
        else if (temp<0) return rank(node.left,key);
        else return 1+this.size(node.left)+rank(node.right,key);
    }
    public Iterator<Key> keys(){
        return this.keys(min(),max());
    }
    public Iterator<Key> keys(Key left,Key right){
        Queue<Key> queue = new PriorityQueue<>();
        this.keys(this.root,queue,left,right);
        return queue.iterator();
    }
    // 输出给定范围内的键
    private void keys(Node node, Queue<Key> queue,Key left,Key right){
        if (node==null) return;
        int res1 = left.compareTo(node.key);
        int res2 = right.compareTo(node.key);
        if (res1<0) this.keys(node.left,queue,left,right);
        if (res1<=0&&res2>=0) queue.add(node.key);
        if (res2>0) this.keys(node.right,queue,left,right);
    }
    private class Node{
        private Node left; // 左结点
        private Node right; // 右结点
        private int N; // 以该结点为根节点的子树的结点数目
        private Key key; // 键
        private Value value; // 键对应的值
        public Node(Key key,Value value,int N){
            this.key=key;
            this.value=value;
            this.N=N;
        }
    }

    public static void main(String[] args) {
        BinarySearchTreeDictionary<Character,Integer> treeDictionary = new BinarySearchTreeDictionary<>();
        treeDictionary.put('K',21);
        treeDictionary.put('J',56);
        treeDictionary.put('Z',72);
        treeDictionary.put('B',63);
        treeDictionary.put('Q',82);
        treeDictionary.put('O',73);
        treeDictionary.put('D',23);
        Iterator<Character> keys = treeDictionary.keys();
        while (keys.hasNext()){
            System.out.print(keys.next()+" ");
        }
    }
}
