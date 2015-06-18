// inplace heap

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {6,5,3,1,8,7,2,4};
        InplaceMinHeap imh = new InplaceMinHeap(array);
        for (Integer value : imh.getArray())
        {
            System.out.print(value + " ");
        }

        Integer min = imh.popMin();
        while (min != null)
        {
            System.out.println(min + " ");
            min = imh.popMin();
        }
    }
}

class InplaceMinHeap
{
    private Integer[] array;
    private Integer heapSize;

    public InplaceMinHeap(Integer[] array)
    {
        this.array = array;
        heapSize  = 1;
        convert();
    }

    public Integer popMin()
    {
        if (heapSize == 0)
        {
            return null;
        }

        Integer min = array[0];
        array[0] = array[heapSize-1];
        --heapSize;
        bubbleDown(0);

        return min;
    }

    private void bubbleDown(Integer index)
    {
        Integer leftChildIndex = getLeftChildIndex(index);
        Integer rightChildIndex = getRightChildIndex(index);

        while (isValidIndex(leftChildIndex) || isValidIndex(rightChildIndex))
        {
            if (isValidIndex(leftChildIndex) && isValidIndex(rightChildIndex))
            {
                if (array[leftChildIndex] < array[rightChildIndex]
                        && array[leftChildIndex] < array[index])
                {
                    swapValuesAt(leftChildIndex, index);
                    index = leftChildIndex;
                }
                else if (array[leftChildIndex] > array[rightChildIndex]
                        && array[rightChildIndex] < array[index])
                {
                    swapValuesAt(rightChildIndex, index);
                    index = rightChildIndex;
                }
            }
            else if (isValidIndex(leftChildIndex)
                    && array[leftChildIndex] < array[index])
            {
                swapValuesAt(leftChildIndex, index);
                index = leftChildIndex;
            }
            else if (isValidIndex(rightChildIndex))
            {
                swapValuesAt(rightChildIndex, index);
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

    private void swapValuesAt(Integer index1, Integer index2)
    {
        Integer tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    private Integer getRightChildIndex(Integer index)
    {
        return getLeftChildIndex(index) + 1;
    }

    private Integer getLeftChildIndex(Integer index)
    {
        return (index*2) + 1;
    }

    private void convert()
    {
        while (heapSize < array.length)
        {
            Integer index = heapSize-1;
            bubbleUp(index);
            ++heapSize;
        }
        bubbleUp(heapSize - 1);
    }

    private void bubbleUp(Integer index)
    {
        Integer parentIndex = getParentIndex(index);
        while (isValidIndex(parentIndex) && array[parentIndex] > array[index])
        {
            Integer tmp = array[index];
            array[index] = array[parentIndex];
            array[parentIndex] = tmp;

            index = parentIndex;
            parentIndex = getParentIndex(index);
        }
    }

    private boolean isValidIndex(Integer index)
    {
        return (index >= 0 && index < heapSize);
    }

    private Integer getParentIndex(Integer index)
    {
        return (index-1)/2;
    }

    public Integer[] getArray()
    {
        return array;
    }
}