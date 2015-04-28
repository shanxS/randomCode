// convert matrix with sorted rows to sorted array using heap
// r2, q2, set7 amazon

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Integer[][] array = new Integer[][]{
                {1,3,5,7},
                {11,13,15,17},
                {2,4,6,8}
        };

        MinHeap heap = new MinHeap();

        for (Integer r=0; r<array.length; ++r)
        {
            for (Integer c=0; c<array[r].length; ++c)
            {
                heap.insert(array[r][c]);
            }
        }

        Integer value = heap.getMin();
        while(value != null)
        {
            System.out.print(value + " ");
            heap.delete();
            value = heap.getMin();
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

    public Integer getMin()
    {
        return (list.size() > 0) ? list.get(0) : null;
    }

    public void insert(Integer value)
    {
        list.add(value);
        bubbleUp();

    }

    public void delete()
    {
        Integer childIndex = list.size() - 1;
        list.set(0, list.get(childIndex));

        list.remove(list.get(childIndex));

        bubbleDown();
    }

    private void bubbleDown() {
        Integer childIndex = 0;
        Integer leftChild = getLeftChild(childIndex);
        Integer rightChild = getRightChild(childIndex);

        while (leftChild != null || rightChild != null)
        {
            if (leftChild != null && rightChild != null)
            {
                if (leftChild < rightChild)
                {
                    if (list.get(childIndex) > leftChild) {
                        Collections.swap(list, childIndex, getLeftChildIndex(childIndex));
                        childIndex = getLeftChildIndex(childIndex);
                    }
                    else
                    {
                        break;
                    }
                }
                else if (rightChild < leftChild)
                {
                    if (list.get(childIndex) > rightChild) {
                        Collections.swap(list, childIndex, getRightChildIndex(childIndex));
                        childIndex = getRightChild(childIndex);
                    }
                    else
                    {
                        break;
                    }
                }
            }
            else if (leftChild != null)
            {
                if (list.get(childIndex) > leftChild) {
                    Collections.swap(list, childIndex, getLeftChildIndex(childIndex));
                    childIndex = getLeftChildIndex(childIndex);
                }
                else
                {
                    break;
                }
            }
            else if (rightChild != null)
            {
                if (rightChild < leftChild)
                {
                    if (list.get(childIndex) > rightChild) {
                        Collections.swap(list, childIndex, getRightChildIndex(childIndex));
                        childIndex = getRightChild(childIndex);
                    }
                    else
                    {
                        break;
                    }
                }
            }

            leftChild = getLeftChild(childIndex);
            rightChild = getRightChild(childIndex);
        }

    }

    private Integer getLeftChild(Integer parentIndex)
    {
        if (isValidIndex(getLeftChildIndex(parentIndex)))
        {
            return list.get(getLeftChildIndex(parentIndex));
        }
        else
        {
            return null;
        }
    }

    private Integer getRightChild(Integer parentIndex)
    {
        if (isValidIndex(getRightChildIndex(parentIndex)))
        {
            return list.get(getRightChildIndex(parentIndex));
        }
        else
        {
            return null;
        }
    }

    private void bubbleUp() {
        Integer childIndex = list.size()-1;
        Integer parentIndex = getParentIndex(childIndex);

        while (isValidIndex(parentIndex) && list.get(parentIndex) > list.get(childIndex))
        {
            Collections.swap(list, parentIndex, childIndex);
            childIndex = parentIndex;
            parentIndex = getParentIndex(childIndex);
        }
    }

    private Integer getRightChildIndex(Integer parentIndex)
    {
        return (parentIndex*2) + 2;
    }

    private Integer getLeftChildIndex(Integer parentIndex)
    {
        return (parentIndex*2) + 1;
    }

    private Boolean isValidIndex(Integer index)
    {
        return (index >= list.size() || index < 0) ? false : true;
    }

    private Integer getParentIndex(Integer childIndex)
    {
        return (childIndex - 1)/2;
    }
}