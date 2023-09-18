package Heap;

public class HeapModule {

    public static void main(String[] args) {

        int[] arr = {1,3,4,2,11,10,20};
        MaxHeap maxHeap = new MaxHeap(100);

        /*maxHeap.buildMaxHeap(arr, arr.length);*/

        for(int x: arr)
            maxHeap.insert(x);

        arr = maxHeap.heap;
        int heapSize = maxHeap.heapSize;


        for(int i=0;i<heapSize;i++)
            System.out.print(arr[i]+ " ");





    }
}
