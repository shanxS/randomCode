// shift 0 to right side of array without disturbing order
// written, q3. set15

public class Main
{
    static Integer[] array = new Integer[]{1,0,2,0,3,0,0,4,5,6,7,0,0,0};

    public static void main(String[] args)
    {
        Integer zeroCursor = setZeroCursor(0);
        Integer cursor = zeroCursor + 1;

        while (cursor < array.length)
        {
            if (zeroCursor != null && zeroCursor < cursor && array[cursor] != 0)
            {
                array[zeroCursor] = array[cursor];
                array[cursor] = 0;
                zeroCursor = setZeroCursor(zeroCursor);
            }

            ++cursor;
        }

        for (Integer i : array)
        {
            System.out.print(i + " ");
        }
    }

    private static Integer setZeroCursor(int currentZeroCursor)
    {
        Integer nextValue = currentZeroCursor;
        while (nextValue < array.length)
        {
            if (array[nextValue] == 0)
            {
                return nextValue;
            }

            ++nextValue;
        }

        return null;
    }
}