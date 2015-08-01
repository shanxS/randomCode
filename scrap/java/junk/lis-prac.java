// lis

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        LIS lis = new LIS();
        System.out.print(lis.find(ar));
    }
}

class LIS
{
    public Integer find(Integer[] ar)
    {
        List<Integer> list = new ArrayList<>();
        Integer length = 0;

        for (Integer i=0; i<ar.length; ++i)
        {
            if (i == 0)
            {
                list.add(ar[i]);
                ++length;
            }
            else if (ar[i] > list.get(length-1))
            {
                list.add(ar[i]);
                ++length;
            }
            else if (ar[i] < list.get(length-1))
            {
                Integer replacementIndex = findReplacementIndex(list, ar[i]);
                list.set(replacementIndex, ar[i]);
            }
        }

        return length;
    }

    private Integer findReplacementIndex(List<Integer> list, Integer value)
    {
        Integer cursor = list.size()-1;

        while (cursor >= 0 && value < list.get(cursor))
        {
            --cursor;
        }

        return cursor+1;
    }
}