package Heap;
import java.util.List;

public class MaxPriorityQueueUsingHeap {

    public static int pop(List<Integer> heap) {

        int heapSize = heap.size();
        if(heapSize==0)
            return -1;

        int top = heap.get(0);

        heap.set(0, heap.get(heapSize-1));
        heap.remove(heapSize-1);

        topDownHeapify(heap, 0);
        return top;
    }

    private static void swap(List<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    private static void topDownHeapify(List<Integer> arr, int i) {
        int left = 2*i+1, right=2*i+2, largest = i;
        int n = arr.size();
        if(left < n && arr.get(left) > arr.get(largest)) {
            largest = left;
        }
        if(right < n && arr.get(right) > arr.get(largest)) {
            largest = right;
        }

        if(largest!=i) {
            swap(arr, i, largest);
            topDownHeapify(arr, largest);
        }
    }
}
