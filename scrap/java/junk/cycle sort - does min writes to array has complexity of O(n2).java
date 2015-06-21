// cycle sort - does min writes to array has complexity of O(n2)

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1,9,2,8};
        CycleSort cs = new CycleSort();
        for (Integer value : cs.sort(array))
        {
            System.out.println(value);
        }
    }
}

class CycleSort
{
    private Integer[] array;

    public Integer[] sort(Integer[] arrayParam)
    {
        this.array = arrayParam;

        Integer cursor = 0;
        Integer seeker = 0;
        Integer pos = 0;

        while (cursor < array.length)
        {
            seeker = array[cursor];
            pos = 0;

            for (Integer i=cursor+1; i<array.length; ++i)
            {
                if (array[i] < seeker)
                {
                    ++pos;
                }
            }

            if (pos == 0)
            {
                ++cursor;
                continue;
            }

            swap(cursor, cursor+pos);
        }

        return array;
    }

    private void swap(Integer fromIndex, Integer toIndex)
    {
        Integer tmp = array[fromIndex];
        array[fromIndex] = array[toIndex];
        array[toIndex] = tmp;
    }
}