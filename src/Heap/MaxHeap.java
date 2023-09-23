package Heap;

public class MaxHeap {

    int[] heap;
    int heapSize;

    MaxHeap(int MAX_SIZE) {
        heap = new int[MAX_SIZE];
        heapSize = 0;
    }

    public void insert(int val) {
        heap[heapSize++] = val;
        bottomUpHeapify(heap, heapSize, heapSize-1);
    }

    public int extractMax() {
        if(heapSize==0)
            return -1;

        int topMax = heap[0];
        delete(0);
        return topMax;
    }

    public void delete(int ind) {
        if(heapSize==0 || ind>=heapSize)
            return;

        heap[ind] = heap[heapSize-1];
        heapSize--;
        topDownHeapify(heap, heapSize, ind);
    }

    private void bottomUpHeapify(int[] arr, int n, int i) {
        int parent = (i-1)/2;
        if(parent>=0) {
            if(arr[parent] < arr[i]) {
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
        int left = 2*i+1, right=2*i+2, largest = i;
        if(left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if(right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if(largest!=i) {
            swap(arr, i, largest);
            topDownHeapify(arr, n, largest);
        }
    }

    public int[] buildMaxHeap(int[] arr, int n) {
        for(int i=(n-1)/2;i>=0;i--) {
            topDownHeapify(arr, n, i);
        }
        return arr;
    }

    public void heapSort(int arr[], int n) {
        buildMaxHeap(arr, n);

        while(heapSize>0) {
            swap(arr, 0, heapSize-1);
            heapSize--;
            topDownHeapify(arr, heapSize, 0);
        }
    }

}
