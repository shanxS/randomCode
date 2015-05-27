// practice generic heap

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{10,9,11,12,8,7};
        GenericHeap minHeap = new GenericHeap(GenericHeap.MIN);
        GenericHeap maxHeap = new GenericHeap(GenericHeap.MAX);
        for (Integer value : array)
        {
            minHeap.insert(value);
            maxHeap.insert(value);
            System.out.println("value: " + value + " min " + minHeap.getTop() + " max " + maxHeap.getTop());
        }

        while(minHeap.size() > 0)
        {
            System.out.println("min " + minHeap.removeTop() + " max " + maxHeap.removeTop());
        }
    }
}

class GenericHeap
{
    final public static Boolean MIN = false;
    final public static Boolean MAX = true;
    final private Boolean type;

    private List<Integer> list;

    public GenericHeap(Boolean type)
    {
        this.type = type;
        this.list = new ArrayList<>();
    }

    public Integer getTop()
    {
        return (list.size() == 0) ? null : list.get(0);
    }

    public Integer removeTop()
    {
        if (getTop() == null)
        {
            return null;
        }

        Integer top = getTop();
        Collections.swap(list, 0, list.size()-1);
        list.remove((int) (list.size() - 1));
        bubbleDown();

        return top;
    }

    private void bubbleDown()
    {
        Integer index = 0;
        Integer rightChildIndex = getRightChildIndex(index);
        Integer leftChildIndex = getLeftChildIndex(index);

        while(isValidIndex(rightChildIndex) || isValidIndex(leftChildIndex))
        {
            if (isValidIndex(rightChildIndex) && isValidIndex(leftChildIndex))
            {
                if ((list.get(leftChildIndex) > list.get(rightChildIndex)) == type
                        && (list.get(leftChildIndex) > list.get(index)) == type)
                {
                    Collections.swap(list, leftChildIndex, index);
                    index = leftChildIndex;
                }
                else if ((list.get(rightChildIndex) > list.get(leftChildIndex)) == type
                    && (list.get(rightChildIndex) > list.get(index)) == type)
                {
                    Collections.swap(list, rightChildIndex, index);
                    index = rightChildIndex;
                }
                else
                {
                    break;
                }
            }
            else if (isValidIndex(rightChildIndex)
                    && (list.get(rightChildIndex) > list.get(index)) == type)
            {
                Collections.swap(list, rightChildIndex, index);
                index = rightChildIndex;
            }
            else if (isValidIndex(leftChildIndex)
                    && (list.get(leftChildIndex) > list.get(index)) == type)
            {
                Collections.swap(list, leftChildIndex, index);
                index = leftChildIndex;
            }
            else
            {
                break;
            }

            rightChildIndex = getRightChildIndex(index);
            leftChildIndex = getLeftChildIndex(index);
        }
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (index*2) + 1;
    }

    private Integer getRightChildIndex(Integer index)
    {
        return getLeftChildIndex(index) + 1;
    }

    public void insert(Integer value)
    {
        list.add(value);

        if (list.size() > 1)
        {
            bubbleUp();
        }
    }

    private void bubbleUp()
    {
        Integer childIndex = list.size()-1;
        Integer parentIndex = getParentIndex(childIndex);

        while(isValidIndex(parentIndex))
        {
            if (childIndex != 0 && ((list.get(childIndex) > list.get(parentIndex)) == type))
            {
                Collections.swap(list, parentIndex, childIndex);
                childIndex = parentIndex;
                parentIndex = getParentIndex(childIndex);
            }
            else
            {
                break;
            }
        }
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < list.size());
    }

    private Integer getParentIndex(Integer childIndex)
    {
        return (childIndex-1)/2;
    }

    public int size()
    {
        return list.size();
    }
}