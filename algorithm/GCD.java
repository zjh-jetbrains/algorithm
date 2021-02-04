package algorithm;

public class GCD {
    public static int calGCD(int num1,int num2){
        if (num2==0)return num1;
        int temp = num1 % num2;
        return calGCD(num2,temp);
    }

    public static void main(String[] args) {
        System.out.println(GCD.calGCD(28,56));
    }
}
