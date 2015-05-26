// generic heap

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{10,11,9,12,8,13,7};
        GenericHeap minHeap = new GenericHeap(GenericHeap.MIN);
        GenericHeap maxHeap = new GenericHeap(GenericHeap.MAX);

        for (Integer value : array)
        {
            minHeap.insert(value);
            maxHeap.insert(value);
            System.out.println("max " + maxHeap.getTop() + " min " + minHeap.getTop());
        }

        System.out.println("-------------------------");

        while(minHeap.size() > 0)
        {
            System.out.println("max " + maxHeap.removeTop() + " min " + minHeap.removeTop());
        }
    }
}

class GenericHeap
{
    static final Boolean MIN = false;
    static final Boolean MAX = true;
    private Boolean type;

    private List<Integer> list;

    public GenericHeap(Boolean type)
    {
        this.type = type;
        this.list = new ArrayList<>();
    }

    public Integer size()
    {
        return list.size();
    }

    public Integer removeTop()
    {
        if (list.size() == 0)
        {
            return null;
        }

        Integer top = getTop();
        Collections.swap(list, 0, list.size()-1);
        list.remove((int) list.size() - 1);
        bubbleDown();

        return top;
    }

    private void bubbleDown()
    {
        Integer index = 0;
        Integer leftChildIndex = getLeftChildIndex(index);
        Integer rightChildIndex = getRightChildIndex(index);

        while(isValidIndex(leftChildIndex) || isValidIndex(rightChildIndex))
        {
            if (isValidIndex(leftChildIndex) && isValidIndex(rightChildIndex))
            {
                if (((list.get(leftChildIndex) > list.get(rightChildIndex)) == type)
                        && ((list.get(index) > list.get(leftChildIndex)) != type))
                {
                    Collections.swap(list, index, leftChildIndex);
                    index = leftChildIndex;
                }
                else if (((list.get(leftChildIndex) > list.get(rightChildIndex)) != type)
                        && ((list.get(index) > list.get(rightChildIndex)) != type))
                {
                    Collections.swap(list, index, rightChildIndex);
                    index = rightChildIndex;
                }
                else
                {
                    break;
                }
            }
            else if (isValidIndex(leftChildIndex)
                    && ((list.get(index) > list.get(leftChildIndex)) != type))
            {
                Collections.swap(list, index, leftChildIndex);
                index = leftChildIndex;
            }
            else if (isValidIndex(rightChildIndex)
                    && ((list.get(index) > list.get(rightChildIndex)) != type))
            {
                Collections.swap(list, index, rightChildIndex);
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

    public void insert(Integer value)
    {

        list.add(value);

        if (list.size() > 1)
        {
            bubbleUp();
        }
    }

    public Integer getTop()
    {
        return (list.size() > 0) ? list.get(0) : null;
    }

    private void bubbleUp()
    {
        Integer index = list.size()-1;
        Integer parentIndex = getParentIndex(index);

        while (index != 0 && isValidIndex(parentIndex)
                && ((list.get(parentIndex) > list.get(index)) != type))
        {
            Collections.swap(list, parentIndex, index);
            index = parentIndex;
            parentIndex = getParentIndex(index);
        }
    }

    private Integer getParentIndex(Integer index)
    {
        return (index/2);
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