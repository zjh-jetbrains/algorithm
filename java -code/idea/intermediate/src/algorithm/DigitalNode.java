package algorithm;

import java.util.Arrays;

// 两数之和（基于链表）
public class DigitalNode {
    private ListNode first;
    private int count = 0;
    public DigitalNode(){}
    public DigitalNode addTwoNumbers(DigitalNode l1, DigitalNode l2) {
        DigitalNode result = new DigitalNode();
        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();
        while (!l1.isEmpty()){
            s1.append(l1.get());
        }
        while (!l2.isEmpty()){
            s2.append(l2.get());
        }
        int num1 = Integer.parseInt(s1.reverse().toString());
        int num2 = Integer.parseInt(s2.reverse().toString());
        int temp = num1+num2;
        StringBuffer sb = new StringBuffer(String.valueOf(temp));
        for (int x=0;x<String.valueOf(temp).length();x++){
            result.add(Integer.parseInt(String.valueOf(sb.charAt(0))));
            sb.deleteCharAt(0);
        }
        return result;
    }
    public void add(int num){
        ListNode oldNode = this.first;
        first = new ListNode(num, oldNode);
        this.count++;
    }
    public int get(){
        int num = this.first.val;
        this.first = this.first.next;
        this.count--;
        return num;
    }
    public int size(){
        return this.count;
    }
    public boolean isEmpty(){
        return size()==0;
    }
    public String toList(DigitalNode digitalNode){
        String str = "[";
        while (!digitalNode.isEmpty()){
            str+=digitalNode.get()+",";
        }
        return str.substring(0,str.length()-1)+"]";
    }
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        DigitalNode d1 = new DigitalNode();
        d1.add(2);
        d1.add(3);
        d1.add(4);
        DigitalNode d2 = new DigitalNode();
        d2.add(1);
        d2.add(2);
        d2.add(3);
        DigitalNode digitalNode = d1.addTwoNumbers(d1, d2);
        System.out.println(digitalNode.toList(digitalNode));
    }
}

