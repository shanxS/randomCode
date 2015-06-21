// find missing and repeated numebers in array of size n and numbers are in range 1 to n
// code question: 111

public class Main
{
    private static Integer[] array = {3,1,3};//{4, 3, 6, 2, 1, 1};

    public static void main(String[] er)
    {
        final Integer range = array.length;

        Integer cursor = 0;
        while (cursor < array.length)
        {
            if (!setAndSwap(cursor))
            {
                ++cursor;
            }
        }

        cursor = 0;
        while (cursor < array.length)
        {
            if (array[cursor] == 0)
            {
                System.out.println("missing " + (cursor+1));
            }
            else if(array[cursor]/(cursor+1) < -1)
            {
                System.out.println("repeated " + (cursor+1));
            }

            ++cursor;
        }

    }

    private static boolean setAndSwap(Integer cursor)
    {
        if (array[cursor] <= 0)
        {
            return false;
        }

        Boolean wasSet = false;
        Integer homeIndex = array[cursor] - 1;

        if (homeIndex == cursor)
        {
            array[homeIndex] = -1*(array[cursor]);

            wasSet = false;
        }
        else if (array[homeIndex] < 0)
        {
            array[homeIndex] *= 2;
            array[cursor] = 0;

            wasSet = false;
        }
        else  if (array[homeIndex] > 0)
        {
            Integer tmp = array[homeIndex];
            array[homeIndex] = -1*(array[cursor]);
            array[cursor] = tmp;

            wasSet = true;

        }
        else  if (array[homeIndex] == 0)
        {
            array[homeIndex] = -1*(array[cursor]);
            array[cursor] = 0;

            wasSet = false;
        }

        return wasSet;
    }
}