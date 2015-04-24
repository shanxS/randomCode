// r2, q2, set6, amazon
// Find the k maximum selling items at amazon site at the end of day. Given a file which has count all sold items. Use of min heap was expected.

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        MinHeap heap = new MinHeap();

        heap.insert(10);
        System.out.println(heap.getMin());

        heap.insert(9);
        System.out.println(heap.getMin());

        heap.insert(11);
        System.out.println(heap.getMin());

        heap.insert(0);
        System.out.println(heap.getMin());

        heap.insert(-10);
        System.out.println(heap.getMin());

        heap.deleteMin();
        System.out.println(heap.getMin());

        heap.deleteMin();
        System.out.println(heap.getMin());

        heap.deleteMin();
        System.out.println(heap.getMin());

        heap.deleteMin();
        System.out.println(heap.getMin());

        heap.deleteMin();
        System.out.println(heap.getMin());

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
        return list.size() >0 ? list.get(0) : null;
    }

    public void deleteMin()
    {
        Collections.swap(list, 0, list.size() - 1);
        list.remove(list.size() - 1);

        bubbleDown();
    }

    private void bubbleDown() {
        Integer cursorIndex = 0;
        Integer rightChildIndex = getRightChildIndex(cursorIndex);
        Integer leftChildIndex = getLeftChildIndex(cursorIndex);

        while (isValidIndex(leftChildIndex) || isValidIndex(rightChildIndex))
        {
            if (isValidIndex(leftChildIndex) && isValidIndex(rightChildIndex))
            {
                Integer comparisionIndex = null;
                if (list.get(leftChildIndex) < list.get(rightChildIndex))
                {
                    comparisionIndex = leftChildIndex;
                }
                else
                {
                    comparisionIndex = rightChildIndex;
                }

                if (list.get(comparisionIndex) < list.get(cursorIndex))
                {
                    Collections.swap(list, comparisionIndex, cursorIndex);
                    cursorIndex = comparisionIndex;
                }
                else
                {
                    break;
                }

            }
            else if (isValidIndex(leftChildIndex))
            {
                if (list.get(leftChildIndex) < list.get(cursorIndex))
                {
                    Collections.swap(list, leftChildIndex, cursorIndex);
                    cursorIndex = leftChildIndex;
                }
                else
                {
                    break;
                }
            }
            else
            {
                if (list.get(rightChildIndex) < list.get(cursorIndex))
                {
                    Collections.swap(list, rightChildIndex, cursorIndex);
                    cursorIndex = rightChildIndex;
                }
                else
                {
                    break;
                }
            }
        }
    }

    private Boolean isValidIndex (Integer index)
    {
        return (index >=0 && index<list.size()) ? true : false;
    }

    public void insert(Integer value)
    {
        list.add(value);
        bubbleUp();
    }

    private void bubbleUp()
    {
        Integer cursorIndex = list.size() - 1;
        Integer parentIndex = getParentIndex(cursorIndex);

        while (parentIndex >= 0)
        {
            if (list.get(parentIndex) > list.get(cursorIndex))
            {
                Collections.swap(list, parentIndex, cursorIndex);
                cursorIndex = parentIndex;
            }
            else
            {
                break;
            }

            parentIndex = getParentIndex(cursorIndex);
        }

    }

    private Integer getLeftChildIndex(Integer parentIndex)
    {
        return (parentIndex*2) + 1;
    }

    private Integer getRightChildIndex(Integer parentIndex)
    {
        return (parentIndex*2) + 2;
    }

    private Integer getParentIndex(Integer childIndex)
    {
        return (childIndex-1)/2;
    }
}
