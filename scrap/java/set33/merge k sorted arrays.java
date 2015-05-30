// merge k sorted arrays
// code question 20
// r2, q1, set33

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] matrix = new Integer[][]{
                {1,4,7,10},
                {2,5,8,11},
                {3,6,9,12}
        };
        Integer rowCount = matrix.length;
        Integer colCount = matrix[0].length;

        MinHeap mh = new MinHeap();
        List<Integer> colStatus = new ArrayList<>();
        for (Integer i=0; i<rowCount; ++i)
        {
            colStatus.add(0);
            mh.insert(matrix[i][0]);
        }

        Integer[] result = new Integer[rowCount*colCount];
        Integer resultCounter = 0;
        while(mh.getMin() != null)
        {
            Integer minValue = mh.removeMin();
            result[resultCounter] = minValue;
            ++resultCounter;

            for (Integer r=0; r<rowCount; ++r)
            {
                if (colStatus.get(r) < colCount && matrix[r][colStatus.get(r)] == minValue)
                {
                    colStatus.set(r, colStatus.get(r)+1);

                    if(colStatus.get(r) < colCount)
                    {
                        mh.insert(matrix[r][colStatus.get(r)]);
                    }
                }
            }
        }

        for (Integer value : result)
        {
            System.out.print(value + " ");
        }
    }
}

class MinHeap
{
    private List<Integer> list;

    public MinHeap()
    {
        this.list = new ArrayList<>();
    }

    public Integer removeMin()
    {
        if (getMin() == null)
        {
            return null;
        }

        Integer previousMin = getMin();
        Collections.swap(list, 0, list.size()-1);
        list.remove((int)list.size()-1);
        bubbleDown();

        return previousMin;
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
                if (list.get(rightChildIndex) < list.get(leftChildIndex)
                        && list.get(rightChildIndex) < list.get(index))
                {
                    Collections.swap(list, rightChildIndex, index);
                    index = rightChildIndex;
                }
                else if (list.get(leftChildIndex) < list.get(rightChildIndex)
                        && list.get(leftChildIndex) < list.get(index))
                {
                    Collections.swap(list, leftChildIndex, index);
                    index = leftChildIndex;
                }
                else
                {
                    break;
                }
            }
            else if (isValidIndex(rightChildIndex)
                    && list.get(rightChildIndex) < list.get(index))
            {
                Collections.swap(list, rightChildIndex, index);
                index = rightChildIndex;
            }
            else if (isValidIndex(leftChildIndex)
                    && list.get(leftChildIndex) < list.get(index))
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

    private Integer getRightChildIndex(Integer index)
    {
        return getLeftChildIndex(index) + 1;
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (index * 2) + 1;
    }

    public Integer getMin()
    {
        if (list.size() == 0)
        {
            return null;
        }

        return list.get(0);
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
        Integer index = list.size()-1;
        Integer parentIndex = getParentIndex(index);

        while(isValidIndex(parentIndex)
                && list.get(parentIndex) > list.get(index))
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
}