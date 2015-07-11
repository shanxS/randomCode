// common element in row wise sorted matrix

import java.util.ArrayList;
import java.util.List;

public class Main
{
    private static Integer[][] ar = {
            {1, 2, 3, 4, 5,10},
            {2, 4, 5, 8, 10,12},
            {3, 5, 7, 9, 10,11},
            {1, 3, 5, 7, 9,10}
    };
    private static final Integer R = ar.length;
    private static final Integer C = ar[0].length;
    private static int[] cursors = new int[R];

    public static void main(String[] er)
    {
        List<Integer> list = new ArrayList<>();
        boolean shallContinue = true;
        Integer thisMax = findMax();
        while (shallContinue)
        {
            for (Integer r=0; r<R; ++r)
            {
                while (cursors[r] < C && ar[r][cursors[r]] < thisMax)
                {
                    ++cursors[r];
                }

                if (cursors[r] == C-1)
                {
                    shallContinue = false;
                }
            }

            if (areSame())
            {
                list.add(ar[0][cursors[0]]);
                ++cursors[0];
            }

            if (shallContinue)
            {
                thisMax = findMax();
            }
        }

        for (Integer i : list)
        {
            System.out.print(i + " ");
        }
    }

    private static boolean areSame()
    {
        Integer value = ar[0][cursors[0]];

        for (Integer r=1; r<R; ++r)
        {
            if (ar[r][cursors[r]] != value)
            {
                return false;
            }
        }

        return true;
    }

    private static Integer findMax()
    {
        Integer max = ar[0][cursors[0]];

        for (Integer r=1; r<R; ++r)
        {
            if (ar[r][cursors[r]] > max)
            {
                max = ar[r][cursors[r]];
            }
        }

        return  max;
    }
}