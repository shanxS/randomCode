// Given array A of size n - Given integer k lt n - Construct an array B - B[i] is min{A[i] A[i+1] A[i+2] A[i+3] to A[i+k]}
// online q2 set23

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] qwe)
    {
        Integer[] array = new Integer[]{4, 3, 2, 5, 1};
        MinHeap mh = new MinHeap();
        for (Integer value : array)
        {
            mh.insert(value);
            System.out.println(mh.getMin());
        }

        for (Integer i=0; i<array.length; ++i)
        {
            mh.remove(mh.getMin());
            System.out.println(mh.getMin());
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

    public void insert(Integer value)
    {
        list.add(value);
        if (list.size() > 1)
        {
            bubbleUp();
        }
    }

    public void remove(Integer value)
    {
        Integer counter = 0;
        while(counter < list.size() && list.get(counter) != value)
        {
            ++counter;
        }

        Collections.swap(list, counter, list.size()-1);
        list.remove((int) list.size() - 1);
        bubbleDown(counter);
    }

    private void bubbleDown(Integer index)
    {
        Integer leftChildIndex = (2*index) + 1;
        Integer rightChildIndex = (2*index) + 2;

        while(isValidIndex(leftChildIndex) || isValidIndex(rightChildIndex))
        {
            if (isValidIndex(leftChildIndex) && isValidIndex(rightChildIndex))
            {
                if (list.get(leftChildIndex) < list.get(rightChildIndex)
                        && list.get(index) > list.get(leftChildIndex))
                {
                    Collections.swap(list, leftChildIndex, index);
                    index = leftChildIndex;
                }
                else if (list.get(leftChildIndex) > list.get(rightChildIndex)
                        && list.get(index) > list.get(rightChildIndex))
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
                    && list.get(index) > list.get(leftChildIndex))
            {
                Collections.swap(list, leftChildIndex, index);
                index = leftChildIndex;
            }
            else if (isValidIndex(rightChildIndex)
                    && list.get(index) > list.get(rightChildIndex))
            {
                Collections.swap(list, rightChildIndex, index);
                index = rightChildIndex;
            }
            else
            {
                break;
            }

            leftChildIndex = (2*index) + 1;
            rightChildIndex = (2*index) + 2;
        }
    }

    private void bubbleUp()
    {
        Integer index = list.size() - 1;
        Integer parentIndex = getParentIndex(index);

        while (isValidIndex(parentIndex) && list.get(index) < list.get(parentIndex))
        {
            Collections.swap(list, parentIndex, index);
            index = parentIndex;
            parentIndex = getParentIndex(index);
        }
    }

    private Boolean isValidIndex(Integer index)
    {
        if (index < 0 || index >= list.size())
        {
            return false;
        }

        return true;
    }


    private Integer getParentIndex(Integer index)
    {
        return (index-1)/2;
    }

    public Integer getMin()
    {
        return (list.size() > 0) ? list.get(0) : null;
    }
}