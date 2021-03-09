package algorithm;

import java.util.Arrays;

public class BinarySearch {
    public static int binarySerachByCycle(int key,int [] array){
        int left = 0;
        int right = array.length-1;
        if (left>right) return -1;
        while (left<=right){
            int mid = (left + right)/2;
            if (key < array[mid]){
                right = mid - 1;
            }else if (key > array[mid]){
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }
    public static int binarySearchByRecursion(int key,int [] array){
        return binarySearchByRecursion(key, array, 0, array.length-1);
    }
    public static int binarySearchByRecursion(int key, int [] array, int left, int right){
        if (left>right) return -1;
        int mid = (left+right)/2;
        if (key < array[mid]){
            return binarySearchByRecursion(key, array, left, mid-1);
        }else if (key > array[mid]){
            return binarySearchByRecursion(key, array, mid+1, right);
        }else {
            return mid;
        }
    }
    public static void main(String[] args) {
        int a[] = {73,51,85,47,11,6,46};
        Arrays.sort(a);
        System.out.println(binarySearchByRecursion(47,a));
    }
}
