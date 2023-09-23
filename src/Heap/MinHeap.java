package Heap;

public class MinHeap {

    int[] heap;
    int heapSize;

    MinHeap(int MAX_SIZE) {
        heap = new int[MAX_SIZE];
        heapSize = 0;
    }

    public void insert(int val) {
        heap[heapSize++] = val;
        bottomUpHeapify(heap, heapSize, heapSize-1);
    }

    public int extractMin() {
        if(heapSize==0)
            return -1;

        int topMin = heap[0];
        delete(0);
        return topMin;
    }

    public void delete(int ind) {
        if(heapSize==0 || ind>=heapSize)
            return;

        //replace that element with last heap element
        heap[ind] = heap[heapSize-1];
        heapSize--;
        topDownHeapify(heap, heapSize, ind);
    }

    private void bottomUpHeapify(int[] arr, int n, int i) {
        int parent = (i-1)/2;
        if(parent>=0) {
            //it's a minHeap - so parent should be smaller
            if(arr[parent] > arr[i]) {
                swap(arr, i, parent);
                bottomUpHeapify(arr, n, parent);
            }
        }
    }

    private void swap(int[] arr, int i,  int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private void topDownHeapify(int[] arr, int n, int i) {
        int left = 2 * i + 1, right = 2 * i + 2, smallest = i;
        if (left < n && arr[left] < arr[smallest]) {
            smallest = left;
        }
        if (right < n && arr[right] < arr[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            swap(arr, i, smallest);
            topDownHeapify(arr, n, smallest);
        }
    }

    public int[] buildMinHeap(int[] arr, int n) {
        for(int i=(n-1)/2;i>=0;i--) {
            topDownHeapify(arr, n, i);
        }
        return arr;
    }

}
