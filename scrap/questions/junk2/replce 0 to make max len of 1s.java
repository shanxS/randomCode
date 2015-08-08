// replce 0 to make max len of 1s

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] arr =  {1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1};
        (new ZeroReplacer()).replace(arr);
    }
}

class ZeroReplacer
{
    Integer globalMax;
    Integer replacementIndex;
    public void replace(Integer[] ar)
    {
        globalMax = Integer.MIN_VALUE;
        replacementIndex = null;

        Integer[] colced = colace(ar);
        compute(colced);

        System.out.print(globalMax + " " + replacementIndex);
    }

    private void compute(Integer[] colaced)
    {
        Integer cursor = 0;
        Integer realIndex = 0;
        while (cursor < colaced.length)
        {
            if (colaced[cursor] != 0)
            {
                realIndex += colaced[cursor]+1;

                if (cursor+2 >= colaced.length
                        || colaced[cursor+2] == 0)
                {
                    Integer thisMax = colaced[cursor] + 1;
                    if (globalMax < thisMax)
                    {
                        globalMax = thisMax;
                        replacementIndex = realIndex;
                    }
                }
                else
                {
                    Integer thisMax = colaced[cursor] + 1 + colaced[cursor+2];
                    if (globalMax < thisMax)
                    {
                        globalMax = thisMax;
                        replacementIndex = realIndex;
                    }
                }

                cursor += 2;
            }
            else
            {
                ++realIndex;
                if (cursor - 1 >= 0 && colaced[cursor - 1] > 0)
                {
                    Integer thisMax = 1 + colaced[cursor-1];
                    if (globalMax < thisMax)
                    {
                        globalMax = thisMax;
                        replacementIndex = realIndex;
                    }
                }

                ++cursor;
            }
        }
    }

    private Integer[] colace(Integer[] ar)
    {
        Integer cursor = 0;
        List<Integer> list = new ArrayList<>();
        while (cursor < ar.length)
        {
            if (ar[cursor] == 0)
            {
                list.add(0);
                ++cursor;
            }
            else
            {
                int sum  = 0;
                while (cursor<ar.length && ar[cursor] == 1)
                {
                    ++sum;
                    ++cursor;
                }
                list.add(sum);
            }
        }

        Integer[] colaced = new Integer[list.size()];
        list.toArray(colaced);

        return colaced;
    }
}