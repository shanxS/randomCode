// geting next largest number form given number using tree set
// r4, q5, set30

import java.util.TreeSet;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{3,4,5,2,1};
//        Integer[] array = new Integer[]{2,1,8,7,6,5};
        TreeSet<Integer> tset = new TreeSet<>();

        Integer reverseCursor = array.length-1;
        while (reverseCursor>0)
        {
            if (array[reverseCursor] <= array[reverseCursor-1])
            {
                tset.add(array[reverseCursor]);
            }
            else
            {
                tset.add(array[reverseCursor]);
                --reverseCursor;
                tset.add(array[reverseCursor]);
                break;
            }
            --reverseCursor;
        }


        if (reverseCursor == 0)
        {
            System.out.print("not possible");
            return;
        }

        Integer cursor = reverseCursor+1;
        Boolean replaced = false;
        while (tset.size() > 0)
        {
            if (tset.first() > array[reverseCursor] && !replaced)
            {
                Integer tmp = tset.pollFirst();
                array[reverseCursor] = tmp;
                replaced = true;
            }
            else
            {
                Integer tmp = tset.pollFirst();
                array[cursor] = tmp;

                ++cursor;
            }
        }

        for (Integer value : array)
        {
            System.out.print(value + " ");
        }
    }
}