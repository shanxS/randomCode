// binary complement of number
// written, q1, set18

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        int value = 11;
        List<Integer> list = new ArrayList<>();

        while(value > 0)
        {
            if ((value&1) == 1)
            {
                list.add(1);
            }
            else
            {
                list.add(0);
            }

            value >>= 1;
        }
        Collections.reverse(list);

        for (Integer i : list)
        {
            if (i == 1)
            {
                System.out.print(0 + " ");
            }
            else
            {
                System.out.print(1 + " ");
            }
        }
    }
}
