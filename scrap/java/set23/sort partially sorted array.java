// sort partially sorted array
// r1, q1

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] wer)
    {
        Integer[] array = new Integer[] {2, 6, 3, 12, 56, 8};
        Integer k = 3;

        MinHeap mh = new MinHeap();
        for (Integer  i=0; i<k; ++i)
        {
            mh.insert(array[i]);
        }

        Integer i=k;
        for (; i<array.length; ++i)
        {
            array[i-k] = mh.getMin();
            mh.removeMin();
            mh.insert(array[i]);
        }

        for (Integer j=i-k; j<array.length; ++j)
        {
            array[j] = mh.getMin();
            mh.removeMin();
        }

        for (Integer value : array)
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

    public void insert(Integer value)
    {
        list.add(value);
        if (list.size() > 1)
        {
            bubbleUp();
        }
    }

    public void removeMin()
    {
        if (getMin() == null)
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
        Integer leftChild = getLeftChild(index);
        Integer rightChild = getRightChild(index);

        while(isValidIndex(leftChild) || isValidIndex(rightChild))
        {
            if ((isValidIndex(leftChild) && isValidIndex(rightChild)))
            {
                if (list.get(leftChild) < list.get(rightChild)
                        && list.get(index) > list.get(leftChild))
                {
                    Collections.swap(list, index, leftChild);
                    index = leftChild;
                }
                else if (list.get(leftChild) > list.get(rightChild)
                        && list.get(index) > list.get(rightChild))
                {
                    Collections.swap(list, index, rightChild);
                    index = rightChild;
                }
                else
                {
                    break;
                }
            }
            else if (isValidIndex(rightChild)
                    && list.get(index) > list.get(rightChild))
            {
                Collections.swap(list, index, rightChild);
                index = rightChild;
            }
            else if (isValidIndex(leftChild)
                    && list.get(index) > list.get(leftChild))
            {
                Collections.swap(list, index, leftChild);
                index = leftChild;
            }
            else
            {
                break;
            }
        }
    }

    private Integer getLeftChild(Integer index)
    {
        return (index * 2) + 1;
    }

    private Integer getRightChild(Integer index)
    {
        return (index * 2) + 2;
    }

    public Integer getMin()
    {
        return (list.size() > 0) ? list.get(0) : null;
    }

    private void bubbleUp()
    {
        Integer index = list.size()-1;
        Integer parentIndex = getParentIndex(index);

        while(isValidIndex(parentIndex) && list.get(parentIndex) > list.get(index))
        {
            Collections.swap(list, index, parentIndex);
            index = parentIndex;
            parentIndex = getParentIndex(index);
        }
    }

    private boolean isValidIndex(Integer index)
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
}