// get nth max number in array

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {12, 13, 1, 10, 34};
        MaxHeap mh = new MaxHeap(3);
        for (Integer value : array)
        {
            mh.insert(value);
        }

        System.out.print(mh.getMax());
    }
}

class MaxHeap
{
    private List<Integer> list;
    final private Integer size;

    public MaxHeap(Integer count)
    {
        this.list = new ArrayList<>();
        this.size = count;
    }

    public Integer getMax()
    {
        return (list.size() > 0) ? list.get(0) : null;
    }

    public void insert(Integer value)
    {
        if (list.size() == size)
        {
            if (list.get(0) > value)
            {
                removeTop();
            }
            else
            {
                return;
            }
        }

        list.add(value);
        bubbleUp();
    }

    private void bubbleUp()
    {
        Integer index = list.size()-1;
        Integer parentIndex = getParentIndex(index);

        while (isValidIndex(parentIndex) && list.get(parentIndex) < list.get(index))
        {
            Collections.swap(list, parentIndex, index);
            index = parentIndex;
            parentIndex = getParentIndex(index);
        }
    }

    private Integer getParentIndex(Integer index)
    {
        return (index-1)/2;
    }

    private void removeTop()
    {
        Collections.swap(list, 0, list.size() - 1);
        list.remove((int) list.size() - 1);

        Integer index = 0;
        Integer leftChildIndex = getLeftChildIndex(index);
        Integer rightChildIndex = getRightChildIndex(index);

        while (isValidIndex(leftChildIndex) || isValidIndex(rightChildIndex))
        {
            if (isValidIndex(leftChildIndex) && isValidIndex(rightChildIndex))
            {
                if (list.get(leftChildIndex) > list.get(rightChildIndex)
                        && list.get(leftChildIndex) > list.get(index))
                {
                    Collections.swap(list, leftChildIndex, index);
                    index = leftChildIndex;
                }
                else if (list.get(leftChildIndex) < list.get(rightChildIndex)
                        && list.get(rightChildIndex) > list.get(index))
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
                        && list.get(leftChildIndex) > list.get(index))
            {
                Collections.swap(list, leftChildIndex, index);
                index = leftChildIndex;
            }
            else if (isValidIndex(rightChildIndex)
                    && list.get(rightChildIndex) > list.get(index))
            {
                Collections.swap(list, rightChildIndex, index);
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

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < list.size());
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