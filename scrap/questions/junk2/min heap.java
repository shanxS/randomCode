// min heap

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] ar = { {10, 20, 30, 40},
                            {15, 25, 35, 45},
                            {27, 29, 37, 48},
                            {32, 33, 39, 50},
                    };

        int[] cursor = new int[ar.length];
        MinHeap m = new MinHeap();
        for (Integer r=0; r<ar.length; ++r)
        {
            m.insert(ar[r][cursor[r]]);
        }

        while(m.size() > 0)
        {
            System.out.print(m.remove() + " ");
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

    public void insert(Integer i)
    {
        list.add(i);
        bubleUp();
    }

    public Integer getTop()
    {
        return list.get(0);
    }

    private void bubleUp()
    {
        Integer childIndex = list.size()-1;
        Integer parentIndex = getPrentIndex(childIndex);

        while (isValidIndex(parentIndex) && list.get(parentIndex) > list.get(childIndex))
        {
            Collections.swap(list, parentIndex, childIndex);
            childIndex = parentIndex;
            parentIndex = getPrentIndex(childIndex);
        }
    }

    private boolean isValidIndex(Integer index)
    {
        return index>=0 && index<list.size();
    }

    private Integer getPrentIndex(Integer childIndex)
    {
        return (childIndex-1)/2;
    }

    public Integer size()
    {
        return list.size();
    }

    public Integer remove()
    {
        if (list.size() == 0)
        {
            return null;
        }

        Integer v = list.remove((int)0);
        if (list.size() > 0)
        {
            Collections.swap(list, 0, list.size() - 1);
            bubbleDown();
        }
        return v;
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
                    Collections.swap(list,leftChildIndex, parentIndex);
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
            else  if (isValidIndex(rightChildIndex)
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

    private Integer getLeftChildIndex(Integer parentIndex)
    {
        return (parentIndex*2) +1;
    }

    private Integer getRightChildIndex(Integer parentIndex)
    {
        return getLeftChildIndex(parentIndex) + 1;
    }
}