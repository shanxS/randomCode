// longest sinusoidal sequence in array
// r3, q1, set34
// code question 33

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{ 374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
                600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
                67, 669, 810, 704, 52, 861, 49, 640, 370, 908,
                477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
                249, 22, 176, 279, 23, 22, 617, 462, 459, 244 };//{1, 2, 3, 4, 5, 6, 7, 8, 9};//{1, 7, 4, 9, 2, 5};
        List<Integer> minMax = new ArrayList<>(); minMax.add(array[0]);
        Boolean lookForMinInMinMax = false;

        List<Integer> maxMin = new ArrayList<>(); maxMin.add(array[0]);
        Boolean lookForMaxInMaxMin = false;


        for (Integer cursor=1; cursor<array.length; ++cursor)
        {
            if (lookForMinInMinMax)
            {
                if (getLast(minMax) > array[cursor])
                {
                    minMax.add(array[cursor]);
                    lookForMinInMinMax = !lookForMinInMinMax;
                }
                else
                {
                    minMax.set(minMax.size()-1, array[cursor]);
                }
            }
            else
            {
                if (getLast(minMax) < array[cursor])
                {
                    minMax.add(array[cursor]);
                    lookForMinInMinMax = !lookForMinInMinMax;
                }
                else
                {
                    minMax.set(minMax.size()-1, array[cursor]);
                }
            }

            if (lookForMaxInMaxMin)
            {
                if (getLast(maxMin) < array[cursor])
                {
                    maxMin.add(array[cursor]);
                    lookForMaxInMaxMin = !lookForMaxInMaxMin;
                }
                else
                {
                    maxMin.set(maxMin.size()-1, array[cursor]);
                }
            }
            else
            {
                if (getLast(maxMin) > array[cursor])
                {
                    maxMin.add(array[cursor]);
                    lookForMaxInMaxMin = !lookForMaxInMaxMin;
                }
                else
                {
                    maxMin.set(maxMin.size()-1, array[cursor]);
                }
            }
        }

        System.out.print("sdf");
    }

    private static Integer getLast(List<Integer> list)
    {
        return list.get(list.size()-1);
    }
}