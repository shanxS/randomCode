// min max heap practice

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {2,1,5,4,7,9};
        Heap minHeap = new Heap(Heap.MIN);
        for (Integer i : ar)
        {
            minHeap.insert(i);
            System.out.print(minHeap.getTop() + " ");
        }

        System.out.println();
        while (minHeap.size() > 0)
        {
            System.out.print(minHeap.removeTop() + " ");
        }

    }
}

class Heap
{
    public static final Boolean MIN = false;
    public static final Boolean MAX = true;
    private final Boolean type;

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
        if (list.size() == 0)
        {
            return null;
        }

        return list.get(0);
    }

    public Integer removeTop()
    {
        if (list.size() == 0)
        {
            return null;
        }

        Integer prevTop = getTop();
        Collections.swap(list, 0, list.size() - 1);
        list.remove((int)(list.size()-1));

        bubbleDown();

        return prevTop;
    }

    private void bubbleDown()
    {
        Integer parentIndex = 0;
        Integer leftChildIndex = getLeftChildIndex(parentIndex);
        Integer rightChildIndex = getRightChildIndex(parentIndex);

        while (isValidIndex(leftChildIndex) || isValidIndex(rightChildIndex))
        {
            if (isValidIndex(leftChildIndex) && isValidIndex(rightChildIndex))
            {
                if((list.get(leftChildIndex) > list.get(rightChildIndex) == type)
                        && list.get(leftChildIndex) > list.get(parentIndex) == type)
                {
                    Collections.swap(list, leftChildIndex, parentIndex);
                    parentIndex = leftChildIndex;
                }
                else if ((list.get(rightChildIndex) > list.get(leftChildIndex) == type)
                        && list.get(rightChildIndex) > list.get(parentIndex) == type)
                {
                    Collections.swap(list, rightChildIndex, parentIndex);
                    parentIndex = rightChildIndex;
                }
                else
                {
                    break;
                }
            }
            else if (isValidIndex(leftChildIndex) &&
                    list.get(leftChildIndex) > list.get(parentIndex) == type)
            {
                Collections.swap(list, leftChildIndex, parentIndex);
                parentIndex = leftChildIndex;
            }
            else if (isValidIndex(rightChildIndex) &&
                    list.get(rightChildIndex) > list.get(parentIndex) == type)
            {
                Collections.swap(list, rightChildIndex, parentIndex);
                parentIndex = rightChildIndex;
            }
            else
            {
                break;
            }

            leftChildIndex = getLeftChildIndex(parentIndex);
            rightChildIndex = getRightChildIndex(parentIndex);
        }
    }

    private Integer getRightChildIndex(Integer parentIndex)
    {
        return 1 + getLeftChildIndex(parentIndex);
    }

    private Integer getLeftChildIndex(Integer parentIndex)
    {
        return (parentIndex*2) + 1;
    }

    public void insert(Integer value)
    {
        list.add(value);
        bubbleUp();
    }

    private void bubbleUp()
    {
        Integer childIndex = list.size()-1;
        Integer parentIndex = getParentIndex(childIndex);

        while ( (parentIndex != childIndex) && isValidIndex(parentIndex) && (list.get(childIndex) > list.get(parentIndex) == type))
        {
            Collections.swap(list, parentIndex, childIndex);
            childIndex = parentIndex;
            parentIndex = getParentIndex(childIndex);
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
}