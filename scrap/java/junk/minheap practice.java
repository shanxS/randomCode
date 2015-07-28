// minheap practice

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {9,8,7,3,5,6};
        MinHeap mh = new MinHeap();
        for (Integer i : ar)
        {
            mh.insert(i);
            System.out.print(mh.getMin() + " ");
        }

        System.out.println();
        while (mh.size() > 0)
        {
            System.out.print(mh.removeMin() + " ");
        }
    }
}

class MinHeap
{
    private List<Integer> list;

    public MinHeap()
    {
        list = new ArrayList<>();
    }

    public Integer size()
    {
        return list.size();
    }

    public void insert(Integer n)
    {
        list.add(n);
        bubbleUp();
    }

    private void bubbleUp()
    {
        if (list.size() == 1)
        {
            return;
        }

        Integer childIndex = list.size()-1;
        Integer parentIndex = getParentIndex(childIndex);

        while (isValidIndex(parentIndex) && list.get(parentIndex) > list.get(childIndex))
        {
            Collections.swap(list, parentIndex, childIndex);
            childIndex = parentIndex;
            parentIndex = getParentIndex(childIndex);
        }
    }

    private boolean isValidIndex(Integer index)
    {
        return index>=0 && index<list.size();
    }

    private Integer getParentIndex(Integer childIndex)
    {
        return (childIndex-1)/2;
    }

    public Integer getMin()
    {
        if (list.size() == 0)
        {
            return null;
        }

        return list.get(0);
    }

    public Integer removeMin()
    {
        if (list.size() ==  0)
        {
            return null;
        }

        Integer returnValue = list.get(0);
        Collections.swap(list, 0, list.size() - 1);
        list.remove((int)(list.size()-1));
        bubbleDown();

        return returnValue;
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
                if (list.get(leftChildIndex) < list.get(rightChildIndex)
                        && list.get(leftChildIndex) < list.get(parentIndex))
                {
                    Collections.swap(list, leftChildIndex, parentIndex);
                    parentIndex = leftChildIndex;
                }
                else if (list.get(leftChildIndex) > list.get(rightChildIndex)
                    && list.get(rightChildIndex) < list.get(parentIndex))
                {
                    Collections.swap(list, rightChildIndex, parentIndex);
                    parentIndex = rightChildIndex;
                }
                else
                {
                    break;
                }
            }
            else if (isValidIndex(leftChildIndex)
                    && list.get(leftChildIndex) < list.get(parentIndex))
            {
                Collections.swap(list, leftChildIndex, parentIndex);
                parentIndex = leftChildIndex;
            }
            else if (isValidIndex(rightChildIndex)
                    && list.get(rightChildIndex) < list.get(parentIndex))
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

    private Integer getRightChildIndex(Integer index)
    {
        return getLeftChildIndex(index) + 1;
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (index*2) + 1;
    }
}