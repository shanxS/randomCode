// median of stream of numbers

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4};
        MedianFinder mf = new MedianFinder();
        for (Integer value : array)
        {
            System.out.println(mf.find(value));
        }

    }
}

class MedianFinder
{
    private Heap minHeap, maxHeap;

    public MedianFinder()
    {
        this.minHeap = new Heap(Heap.MINHEAP);
        this.maxHeap = new Heap(Heap.MAXHEAP);
    }

    public Integer find(Integer value)
    {
        insertIntoHeap(value);
        return calculateMedian();
    }

    private Integer calculateMedian()
    {
        if (minHeap.size() == maxHeap.size())
        {
            return (minHeap.getTop() + maxHeap.getTop())/2;
        }

        return (minHeap.size() > maxHeap.size()) ? minHeap.getTop() : maxHeap.getTop();
    }

    private void insertIntoHeap(Integer value)
    {
        if (minHeap.size() == 0)
        {
            minHeap.insert(value);
        }
        else
        {
            if (minHeap.getTop() > value)
            {
                if (minHeap.size() < maxHeap.size())
                {
                    if (maxHeap.getTop() < value)
                    {
                        minHeap.insert(value);
                    }
                    else
                    {
                        minHeap.insert(maxHeap.removeTop());
                        maxHeap.insert(value);
                    }
                }
                else
                {
                    maxHeap.insert(value);
                }
            }
            else if (minHeap.getTop() < value)
            {
                if (minHeap.size() > maxHeap.size())
                {
                    maxHeap.insert(minHeap.removeTop());
                }

                minHeap.insert(value);
            }
        }
    }
}

class Heap
{
    final static public Boolean MINHEAP = false;
    final static public Boolean MAXHEAP = true;
    final private Boolean type;
    private List<Integer> list;

    public Heap(Boolean type)
    {
        this.type = type;
        this.list = new ArrayList<>();
    }

    public Integer size()
    {
        return list.size();
    }

    public Integer getTop()
    {
        return (list.size() > 0) ? list.get(0) : null;
    }

    public void insert(Integer value)
    {
        list.add(value);
        bubbleUp();
    }

    private void bubbleUp()
    {
        Integer index = list.size() - 1;
        Integer parentIndex = getParentIndex(index);

        while (!(parentIndex == 0 && index == 0)
                && isValidIndex(parentIndex)
                && (list.get(parentIndex) < list.get(index)) == type)
        {
            Collections.swap(list, parentIndex, index);
            index = parentIndex;
            parentIndex = getParentIndex(index);
        }
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < list.size());
    }

    private Integer getParentIndex(Integer index)
    {
        return (index-1)/2;
    }

    public Integer removeTop()
    {
        if (list.size() == 0)
        {
            return null;
        }

        Integer top = list.get(0);
        Collections.swap(list, 0, list.size()-1);
        list.remove((int) (list.size() - 1));
        bubbleDown();

        return top;
    }

    private void bubbleDown()
    {
        Integer index = 0;
        Integer leftChildIndex = getLeftChildIndex(index);
        Integer rightChildIndex = getRightChildIndex(index);

        while (isValidIndex(leftChildIndex) || isValidIndex(rightChildIndex))
        {
            if (isValidIndex(leftChildIndex) && isValidIndex(rightChildIndex))
            {
                if ((list.get(leftChildIndex) < list.get(rightChildIndex)) == type
                        && (list.get(index) < list.get(rightChildIndex)) == type)
                {
                    Collections.swap(list, index, rightChildIndex);
                    index = rightChildIndex;
                }
                else if ((list.get(rightChildIndex) < list.get(leftChildIndex)) == type
                        && (list.get(index) < list.get(leftChildIndex)) == type)
                {
                    Collections.swap(list, index, leftChildIndex);
                    index = leftChildIndex;
                }
                else
                {
                    break;
                }
            }
            else  if (isValidIndex(leftChildIndex)
                    && (list.get(index) < list.get(leftChildIndex)) == type)
            {
                Collections.swap(list, index, leftChildIndex);
                index = leftChildIndex;
            }
            else if (isValidIndex(rightChildIndex)
                    && (list.get(index) < list.get(rightChildIndex)) == type)
            {
                Collections.swap(list, index, rightChildIndex);
                index = rightChildIndex;
            }
            else
            {
                break;
            }

            leftChildIndex = getLeftChildIndex(index);
            rightChildIndex = getRightChildIndex(index);
        }
    }

    private Integer getRightChildIndex(Integer index)
    {
        return getLeftChildIndex(index) + 1;
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (2*index) + 1;
    }
}