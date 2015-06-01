// max length of continuous sub array with at max 3 unique elements
// set37, r1, q1
// code question 

import java.util.ArrayList;
import java.util.List;

public class Main
{
    static Integer[] array = new Integer[]{1, 2, 3, 1, 4, 3, 4, 1, 2};
    public static void main(String[] er)
    {
        List<Integer> list = new ArrayList<>();

        Integer size = 0;
        Integer maxSize = Integer.MIN_VALUE;
        for (Integer cursor=0; cursor<array.length; ++cursor)
        {
            Integer value = array[cursor];

            if (list.size() < 3)
            {
                list.add(value);
                ++size;
            }
            else
            {
                if (!list.contains(value))
                {
                    list.remove(getNumberToBeRemoved(list, cursor-1));
                    list.add(value);

                    if (maxSize < size)
                    {maxSize = size;}

                    size = 3;
                }
                else
                {
                    ++size;
                }
            }
        }

        System.out.print(maxSize);
    }

    private static Integer getNumberToBeRemoved(List<Integer> origList, Integer cursor)
    {
        List<Integer> list = new ArrayList<>(origList);

        while(cursor >= 0 && list.size() > 1)
        {
            if (list.contains(array[cursor]))
            {
                list.remove((Integer)array[cursor]);
            }

            --cursor;
        }

        return list.get(0);
    }
}