// Given array A of size n - Given integer k < n - Construct an array B - B[i] = min{A[i], A[i+1], A[i+2], A[i+3], ……., A[i+k]}
// online q2, set22

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] wer)
    {
        Integer[] array = new Integer[] {9,5,6,7,2,4,1};
        LimitedMinHeap limitedMinHeap = new LimitedMinHeap();

        Integer[] B = new Integer[array.length];
        Integer k = 3;
        for (Integer cursor  = array.length-1; cursor >= 0; --cursor)
        {
            if (cursor + k < array.length)
            {
                limitedMinHeap.remove(array[cursor + k]);
            }

            limitedMinHeap.insert(array[cursor]);
            B[cursor] = limitedMinHeap.getMin();
        }

        for (Integer cursor = 0; cursor < B.length; ++cursor)
        {
            System.out.print(B[cursor] + " ");
        }

//        for (Integer value : array)
//        {
//            limitedMinHeap.insert(value);
//            System.out.print(limitedMinHeap.getMin() + " ");
//        }
//
//        System.out.println();
//        for (Integer i=0; i<array.length; ++i)
//        {
//            System.out.print(limitedMinHeap.removeMin() + " ");
//        }
    }
}

class LimitedMinHeap
{
    private List<Integer> list;

    public LimitedMinHeap()
    {
        list = new ArrayList<>();
    }

    public void insert(Integer value)
    {
        list.add(value);
        bubbleUp();
    }

    public void remove(Integer target)
    {
        Integer targetIndex = 0;
        while (targetIndex < list.size())
        {
            if (list.get(targetIndex) == target)
            {
                break;
            }
            ++targetIndex;
        }

        Collections.swap(list, targetIndex, list.size() - 1);
        list.remove((int)list.size()-1);
        bubbleDown(targetIndex);
    }

    public Integer removeMin()
    {
        Integer min = getMin();

        Collections.swap(list, 0, list.size() - 1);
        list.remove((int)list.size()-1);
        bubbleDown(0);

        return min;
    }

    private void bubbleDown(Integer targetIndex)
    {
        if (list.size() <= 1)
        {
            return;
        }

        Integer childIndex = getLeastChild(targetIndex);
        while (childIndex != null && list.get(childIndex) < list.get(targetIndex))
        {
            Collections.swap(list, targetIndex, childIndex);
            targetIndex = childIndex;
            childIndex = getLeastChild(targetIndex);
        }
    }

    private Integer getLeastChild(Integer targetIndex)
    {
        Integer rightChildIndex = (2*targetIndex) + 2;
        Integer leftChildIndex = (2*targetIndex) + 1;

        if (validIndex(rightChildIndex) && validIndex(leftChildIndex))
        {
            return (list.get(rightChildIndex) < list.get(leftChildIndex)) ? rightChildIndex : leftChildIndex;
        }
        else if (validIndex(leftChildIndex))
        {
            return leftChildIndex;
        }

        return null;
    }

    private void bubbleUp()
    {
        if (list.size() <= 1)
        {
            return;
        }

        Integer targetIndex = list.size() - 1;
        Integer parentIndex = getParentIndex(targetIndex);

        while(parentIndex != null && list.get(targetIndex) < list.get(parentIndex))
        {
            Collections.swap(list, parentIndex, targetIndex);
            targetIndex = parentIndex;
            parentIndex = getParentIndex(targetIndex);
        }
    }

    public Integer getMin()
    {
        return list.get(0);
    }

    private boolean validIndex(Integer index)
    {
        return  (index >= 0 && index < list.size());
    }

    private Integer getParentIndex(Integer targetIndex)
    {
        Integer index = (targetIndex - 1)/2;
        return validIndex(index) ? index : null;
    }
}
