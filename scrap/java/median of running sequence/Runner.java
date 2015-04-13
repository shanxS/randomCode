import Heap.MaxHeap;
import Heap.MinHeap;

// Created by shanxS on 4/11/2015.
public class Runner {


    public Runner()
    {
    }

    public void run()
    {
        MaxHeap maxHeap = new MaxHeap();
        MinHeap minHeap = new MinHeap();

        Integer[] numbers = {3,7,5,1,10,12,2};

        for (int i=0; i<numbers.length; ++i)
        {
            if (i == 0)
            {
                System.out.println("midian till i: " + i + " is " + numbers[i]);
                continue;
            }
            else if (i==1)
            {
                if (numbers[i] < numbers[i-1])
                {
                    maxHeap.insertElement(numbers[i]);
                    minHeap.insertElement(numbers[i-1]);
                }
                else
                {
                    maxHeap.insertElement(numbers[i-1]);
                    minHeap.insertElement(numbers[i]);
                }

                System.out.println("midian till i: " + i + " is " + (numbers[i] + numbers[i-1])/2);
                continue;
            }
            else
            {
                int currentNumber = numbers[i];
                if (maxHeap.getMaximum() > currentNumber)
                {
                    maxHeap.insertElement(currentNumber);
                }
                else
                {
                    minHeap.insertElement(currentNumber);
                }
            }

            if (Math.abs(minHeap.elementCount() - maxHeap.elementCount()) > 1)
            {
                if (minHeap.elementCount() - maxHeap.elementCount() > 0)
                {
                    Integer min = minHeap.removeRootElement();
                    maxHeap.insertElement(min);
                }
                else
                {
                    Integer max = maxHeap.removeRootElement();
                    minHeap.insertElement(max);
                }
            }

            Integer median;
            if (minHeap.elementCount() > maxHeap.elementCount())
            {
                median = minHeap.getMinimum();
            }
            else if (minHeap.elementCount() < maxHeap.elementCount())
            {
                median = maxHeap.getMaximum();
            }
            else
            {
                median = (maxHeap.getMaximum() + minHeap.getMinimum())/2;
            }

            System.out.println("midian till i: " + i + " is " + median);
        }
    }
}

