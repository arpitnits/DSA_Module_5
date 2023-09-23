package Heap;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {

    PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder()); //maxHeap
    PriorityQueue<Integer> right = new PriorityQueue<>(); //minHeap

    public MedianFinder() {

    }

    public void addNum(int num) {
        if(left.isEmpty() || num < left.peek()) {
            left.add(num);
        } else {
            right.add(num);
        }

        if(left.size() - right.size() > 1) {
            right.add(left.remove());
        } else if(right.size() - left.size() > 1) {
            left.add(right.remove());
        }
    }

    public double findMedian() {
        if(left.isEmpty())
            return -1;

        if(left.size() == right.size()) {
            return (double)(left.peek() + right.peek())/2;
        } else if(left.size() > right.size()) {
            return left.peek();
        } else
            return right.peek();
    }
}
