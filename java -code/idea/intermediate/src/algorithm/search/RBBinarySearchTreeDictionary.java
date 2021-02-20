package algorithm.search;

// 红黑树（2-3树）
public class RBBinarySearchTreeDictionary <Key extends Comparable<Key>,Value>{
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    public void put(Key key,Value value){
        this.root=this.put(this.root,key,value);
        this.root.color=BLACK;
    }
    public Node put(Node node,Key key,Value value){
        if (node==null) return new Node(key,value,1,RED);
        int result = key.compareTo(node.key);
        if (result<0) node.left=this.put(node.left,key,value);
        else if (result>0) node.right=this.put(node.right,key,value);
        else node.value = value;
        if (!this.isRed(node.left)&&this.isRed(node.right)) node=this.rotateLeft(node);
        if (this.isRed(node.left)&&this.isRed(node.left.left)) node=this.rotateRight(node);
        if (this.isRed(node.left)&&this.isRed(node.right)) this.flipColors(node);
        node.N=this.size(node.right)+this.size(node.left)+1;
        return node;
    }
    public Value get(Key key){
        Node x = this.get(this.root,key);
        if (x==null) return null;
        return x.value;
    }
    private Node get(Node node,Key key){
        if (node==null) return null;
        int result = key.compareTo(node.key);
        if (result<0) return this.get(node.left,key);
        else if (result>0) return this.get(node.right,key);
        else return node;
    }
    private boolean isRed(Node node){
        if (node==null) return false;
        return node.color==RED;
    }
    private Node rotateLeft(Node node){
        Node temp = node.right;
        node.right=temp.left;
        temp.left=node;
        temp.color=node.color;
        node.color=RED;
        temp.N=node.N;
        node.N=this.size(node.left)+this.size(node.right)+1;
        return temp;
    }
    private Node rotateRight(Node node){
        Node temp = node.left;
        node.left=temp.right;
        temp.right=node;
        temp.color=node.color;
        node.color=RED;
        temp.N=node.N;
        node.N=1+this.size(node.left)+this.size(node.right);
        return temp;
    }
    // 颜色转换
    private void flipColors(Node node){
        node.color=RED;
        node.left.color=BLACK;
        node.right.color=BLACK;
    }
    public int size(){
        return this.size(root);
    }
    private int size(Node x){
        if (x==null) return 0;
        else return x.N;
    }
    private class Node{
        private Node left;
        private Node right;
        private Key key;
        private Value value;
        private int N;
        private boolean color;
        public Node(Key key,Value value,int N,boolean color) {
            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }
    }

    public static void main(String[] args) {
        RBBinarySearchTreeDictionary<Character,Integer> rbTree = new RBBinarySearchTreeDictionary<>();
        rbTree.put('G',121);
        rbTree.put('I',420);
        rbTree.put('Q',750);
        System.out.println(rbTree.get('I'));
    }
}
