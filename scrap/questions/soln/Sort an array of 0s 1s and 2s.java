// Sort an array of 0s 1s and 2s
// code question: 109

public class Main
{
    private static Integer[] array = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};

    public static void main(String[] er)
    {
        Integer zeroCursor = 0;
        while (zeroCursor < array.length
                && array[zeroCursor] != 0)
        {
            ++zeroCursor;
        }
        swap (0, zeroCursor);
        zeroCursor = 0;

        Integer oneCursor = 1;
        while(oneCursor < array.length
                && array[oneCursor] != 1)
        {
            ++oneCursor;
        }
        swap(1, oneCursor);
        oneCursor = 1;

        Integer twoCursor = array.length - 1;
        while (twoCursor >= 0
                && array[twoCursor] != 2)
        {
            --twoCursor;
        }
        swap (array.length-1, twoCursor);
        twoCursor = array.length-1;

        Integer cursor = oneCursor + 1;

        while (cursor < twoCursor)
        {
            if (array[cursor] == 1)
            {
                swap(oneCursor+1, cursor);
                ++oneCursor;
            }
            else if (array[cursor] == 2)
            {
                swap(cursor, twoCursor-1);
                --twoCursor;
            }
            else if (array[cursor] == 0)
            {
                swap(zeroCursor+1, oneCursor+1);
                ++oneCursor;

                if (oneCursor != cursor)
                {
                    swap(zeroCursor + 1, cursor);
                }

                ++zeroCursor;
            }

            if (cursor == oneCursor)
            {
                ++cursor;
            }
        }

        for (Integer v : array)
        {
            System.out.print(v + " ");
        }

    }

    private static void swap(Integer from, Integer to)
    {
        Integer tmp = array[from];
        array[from] = array[to];
        array[to] = tmp;
    }
}