// lis nlogn

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {10, 22, 9, 33, 21, 50, 41, 60, 80 };
        (new LIS()).lis(ar);
    }
}

class LIS
{
    public void lis(int[] ar)
    {
        List<Integer> list = new ArrayList<>();
        Integer len = 0;

        for(int i=0; i<ar.length; ++i)
        {
            if (i ==0)
            {
                list.add(ar[0]);
                ++len;
            }
            else if (list.get(len-1) < ar[i])
            {
                list.add(ar[i]);
                ++len;
            }
            else
            {
                Integer replacementIndex = getReplacementINdex(list, ar[i]);
                list.set(replacementIndex, ar[i]);
            }
        }

        System.out.print(len);
    }

    private Integer getReplacementINdex(List<Integer> list, int v)
    {
        Integer cursor = list.size()-1;
        while (cursor >= 0 && list.get(cursor) > v)
        {
            --cursor;
        }

        return cursor+1;
    }
}