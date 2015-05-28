// convert row sorted matrix to sorted array in mnlogm
// r2, q4, set30

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
        final Integer ROW = matrix.length;
        final Integer COL = matrix[0].length;

        MinHeap minHeap = new MinHeap();
        List<Integer> colStatus = new ArrayList<>();
        for (Integer i=0; i<ROW; ++i)
        {
            colStatus.add(0);
            minHeap.insert(matrix[i][0]);
        }

        while(minHeap.getMin() != Integer.MAX_VALUE)
        {
            Integer min = minHeap.getMin();
            System.out.print(min + " ");
            minHeap.removeTop();

            for (Integer r=0; r<ROW; ++r)
            {
                if (colStatus.get(r) < COL
                        && matrix[r][colStatus.get(r)] == min)
                {
                    colStatus.set(r, colStatus.get(r) + 1);

                    if (colStatus.get(r) < COL)
                    {
                        minHeap.insert(matrix[r][colStatus.get(r)]);
                    }
                    else
                    {
                        minHeap.insert(Integer.MAX_VALUE);
                    }

                    break;
                }
            }
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

    public Integer size()
    {
        return list.size();
    }

    public void insert(Integer value)
    {
        list.add(value);

        if (list.size() > 1)
        {
            bubbleUp();
        }
    }

    public void removeTop()
    {
        if (list.size() == 0)
        {
            return;
        }

        Collections.swap(list, 0, list.size()-1);
        list.remove((int) list.size() - 1);

        bubbleDown();
    }

    private void bubbleDown()
    {
        Integer index = 0;
        Integer leftChildIndex = getLeftChildIndex(index);
        Integer rightChlidIndex = getRightChildIndex(index);

        while(isValidIndex(rightChlidIndex) || isValidIndex(leftChildIndex))
        {
            if (isValidIndex(rightChlidIndex) && isValidIndex(leftChildIndex))
            {
                if (list.get(leftChildIndex) < list.get(rightChlidIndex)
                        && list.get(leftChildIndex) < list.get(index))
                {
                    Collections.swap(list, index, leftChildIndex);
                    index = leftChildIndex;
                }
                else if (list.get(rightChlidIndex) < list.get(leftChildIndex)
                        && list.get(rightChlidIndex) < list.get(index))
                {
                    Collections.swap(list, index, rightChlidIndex);
                    index = rightChlidIndex;
                }
                else
                {
                    break;
                }
            }
            else if (isValidIndex(rightChlidIndex)
                        && list.get(rightChlidIndex) < list.get(index))
            {
                Collections.swap(list, index, rightChlidIndex);
                index = rightChlidIndex;
            }
            else if (isValidIndex(leftChildIndex)
                        && list.get(leftChildIndex) < list.get(index))
            {
                Collections.swap(list, index, leftChildIndex);
                index = leftChildIndex;
            }
            else
            {
                break;
            }

            leftChildIndex = getLeftChildIndex(index);
            rightChlidIndex = getRightChildIndex(index);
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

    public Integer getMin()
    {
        if (list.size() == 0)
        {
            return null;
        }

        return list.get(0);
    }

    private void bubbleUp()
    {
        Integer index = list.size()-1;
        Integer parentIndex = getParentIndex(index);

        while(isValidIndex(parentIndex)
                && list.get(index) < list.get(parentIndex))
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