public class QuickSort <T extends Comparable<T>>
{
    private T[] m_data;

    public QuickSort(T[] data)
    {
        m_data = data;
    }

    public T[] doQuickSort()
    {
        sort(0, m_data.length-1);
        return m_data;
    }

    private void sort(int startIndex, int endIndex)
    {
        if (startIndex == endIndex)
        {
            return;
        }

        Integer pivotIndex = endIndex;
        T pivot = m_data[pivotIndex];
        Integer cursor = startIndex;
        while(cursor < pivotIndex)
        {
            if (m_data[cursor].compareTo(pivot) > 0)
            {
                if (cursor+1 == pivotIndex)
                {
                    swap(cursor, pivotIndex);
                }
                else
                {
                    swap(pivotIndex - 1, pivotIndex);
                    swap(pivotIndex, cursor);
                }
                --pivotIndex;
            }
            else
            {
                ++cursor;
            }
        }

        if (pivotIndex > 0)
        {
            sort(startIndex, pivotIndex - 1);
        }

        if (pivotIndex+1 < endIndex)
        {
            sort(pivotIndex + 1, endIndex);
        }

    }

    private void swap(int firstIndex, Integer secondIndex)
    {
        T temp = m_data[firstIndex];
        m_data[firstIndex] = m_data[secondIndex];
        m_data[secondIndex] = temp;
    }

}
