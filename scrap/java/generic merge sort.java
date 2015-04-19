// generic merge sort

import java.util.function.Function;

public class Main {

    public static void main(String[] args)
    {
        Function<Integer, Integer[]> getIntegerArray = (Integer size) -> {return new Integer[size];};

        Integer[] array = new Integer[] {6, 3, 8, 1, 9 };
        MergeSort<Integer> sorter = new MergeSort<>(array, getIntegerArray);

        for (Integer value : sorter.getSortedData())
        {
            System.out.print(value +  " ");
        }
    }
}

class MergeSort <T extends Comparable>
{
    final private T[] m_data;
    final Function<Integer, T[]> m_getTArray;

    public MergeSort(T[] data, Function<Integer, T[]> arrayGetter)
    {
        m_data = data;
        m_getTArray = arrayGetter;
    }

    public T[] getSortedData()
    {
        return doMergeSort(0, m_data.length - 1);
    }

    private T[] doMergeSort(Integer startIndex, Integer endIndex) {

        if (endIndex == startIndex )
        {
            T[] array = m_getTArray.apply(1);
            array[0] = m_data[startIndex];
            return array;
        }

        Integer length = endIndex - startIndex;
        T[] firstSortedArray = doMergeSort(startIndex, startIndex + (length / 2));
        T[] secondSortedArray = doMergeSort(startIndex + (length / 2) + 1, endIndex);

        return mergeSortedArray(firstSortedArray, secondSortedArray);
    }

    private T[] mergeSortedArray(T[] firstSortedArray, T[] secondSortedArray)
    {
        T[] mergedArray = m_getTArray.apply(firstSortedArray.length + secondSortedArray.length);
        Integer mergedCounter = 0;
        Integer firstCounter = 0;
        Integer secondCounter = 0;

        while(firstCounter < firstSortedArray.length
                && secondCounter < secondSortedArray.length)
        {
            if (firstSortedArray[firstCounter].compareTo(secondSortedArray[secondCounter]) < 0)
            {
                mergedArray[mergedCounter] = secondSortedArray[secondCounter];
                ++secondCounter;
            }
            else
            {
                mergedArray[mergedCounter] = firstSortedArray[firstCounter];
                ++firstCounter;
            }

            ++mergedCounter;
        }

        while(firstCounter < firstSortedArray.length)
        {
            mergedArray[mergedCounter] = firstSortedArray[firstCounter];
            ++firstCounter;
            ++mergedCounter;
        }

        while(secondCounter < secondSortedArray.length)
        {
            mergedArray[mergedCounter] = secondSortedArray[secondCounter];
            ++secondCounter;
            ++mergedCounter;
        }

        return mergedArray;
    }
}