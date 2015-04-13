import Heap.MinHeap;

// Created by shanxS on 4/11/2015.
public class Runner {


    public Runner()
    {
    }

    public void run()
    {
        /*MaxHeap maxHeap = new MaxHeap();

        maxHeap.insertElement(1);
        System.out.println(maxHeap.getMaximum());
        maxHeap.printHeap();

        maxHeap.insertElement(7);
        System.out.println(maxHeap.getMaximum());
        maxHeap.printHeap();

        maxHeap.insertElement(5);
        System.out.println(maxHeap.getMaximum());
        maxHeap.printHeap();

        maxHeap.insertElement(10);
        System.out.println(maxHeap.getMaximum());
        maxHeap.printHeap();

        while(maxHeap.elementCount() > 1)
        {
            maxHeap.removeRootElement();
            System.out.println(maxHeap.getMaximum());
            maxHeap.printHeap();
        }*/


        MinHeap minHeap = new MinHeap();

        minHeap.insertElement(9);
        System.out.println(minHeap.getMinimum());
        minHeap.printHeap();

        minHeap.insertElement(8);
        System.out.println(minHeap.getMinimum());
        minHeap.printHeap();

        minHeap.insertElement(10);
        System.out.println(minHeap.getMinimum());
        minHeap.printHeap();

        minHeap.insertElement(1);
        System.out.println(minHeap.getMinimum());
        minHeap.printHeap();

        while(minHeap.elementCount() > 1) {
            minHeap.removeRootElement();
            System.out.println(minHeap.getMinimum());
            minHeap.printHeap();
        }

    }
}

