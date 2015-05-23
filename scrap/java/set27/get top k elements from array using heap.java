// get top k elements from array using heap
// f2f 1, q1

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1,2,3,6,7,10,2,4,11,21};
        MinHeap mh = new MinHeap(3);
        for (Integer value : array)
        {
            mh.insert(value);
            System.out.println("insert " + value + " min " + mh.getMin());
        }
    }
}

class MinHeap
{
    private List<Integer> list;
    final private Integer size;

    public MinHeap(Integer size)
    {
        this.list = new ArrayList<>();
        this.size = size;
    }

    public void insert(Integer value)
    {
        if (list.size() == size && getMin() > value)
        {
            return;
        }
        else if (list.size() == size && getMin() < value)
        {
            Collections.swap(list, 0, list.size()-1);
            list.remove((int)(list.size()-1));
            bubbleDown(0);
        }

        list.add(value);
        if (list.size() > 1)
        {
            bubbleUp(list.size() - 1);
        }
    }

    private void bubbleDown(Integer index)
    {
        Integer leftChildIndex = getLeftChild(index);
        Integer rightChildIndex = getRightChild(index);

        while (isValidIndex(leftChildIndex) || isValidIndex(rightChildIndex))
        {
            if (isValidIndex(leftChildIndex) && isValidIndex(rightChildIndex))
            {
                if (list.get(leftChildIndex) < list.get(rightChildIndex)
                        && list.get(leftChildIndex) < list.get(index))
                {
                    Collections.swap(list, leftChildIndex, index);
                    index = leftChildIndex;
                }
                else if  (list.get(leftChildIndex) > list.get(rightChildIndex)
                        && list.get(rightChildIndex) < list.get(index))
                {
                    Collections.swap(list, rightChildIndex, index);
                    index = rightChildIndex;
                }
                else
                {
                    break;
                }
            }
            else if (isValidIndex(leftChildIndex)
                    && list.get(leftChildIndex) < list.get(index))
            {
                Collections.swap(list, leftChildIndex, index);
                index = leftChildIndex;
            }
            else if (isValidIndex(rightChildIndex)
                    && list.get(rightChildIndex) < list.get(index))
            {
                Collections.swap(list, rightChildIndex, index);
                index = rightChildIndex;
            }
            else
            {
                break;
            }

            leftChildIndex = getLeftChild(index);
            rightChildIndex = getRightChild(index);
        }
    }

    private Integer getRightChild(Integer index)
    {
        return (index*2) + 2;
    }

    private Integer getLeftChild(Integer index)
    {
        return (index*2) + 1;
    }

    public Integer getMin()
    {
        return list.size() == 0 ? null : list.get(0);
    }

    private void bubbleUp(Integer index)
    {
        Integer parentIndex = getParentIndex(index);

        while(isValidIndex(parentIndex) && list.get(parentIndex) > list.get(index))
        {
            Collections.swap(list, parentIndex, index);
            parentIndex = getParentIndex(index);
        }
    }

    private Integer getParentIndex(Integer index)
    {
        return index/2;
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < list.size());
    }
}