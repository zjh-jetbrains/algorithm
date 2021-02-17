package algorithm;

public class Fibonacci {
    private int a=1;
    private int b=1;
    private int c=0;
    private int sum=0;
    public void printFibonacci(int end){
        System.out.print(a+"、"+b);
        while(true){
            this.c=this.a+this.b;
            this.a=this.b;
            this.b=this.c;
            if (this.c<end)System.out.print("、"+c);
            else break;
        }
    }
    public int addFibonacci(int end){
        this.sum = this.a+this.b;
        while (true){
            this.c=this.a+this.b;
            this.a=this.b;
            this.b=this.c;
            if (this.c<end)this.sum+=c;
            else break;
        }
        return this.sum;
    }

    public static void main(String[] args) {
        //new Fibonacci().printFibonacci(400);
        System.out.println(new Fibonacci().addFibonacci(300));
    }
}
