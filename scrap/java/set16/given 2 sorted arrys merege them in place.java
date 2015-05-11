// given 2 sorted arrys merege them in place
// r2, q1, set16

public class Main
{
    static Integer[] array1 = new Integer[8];
    static Integer[] array2 = new Integer[]{2,4,6,8};
    static Integer elementCount = 4;

    public static void main(String[] args)
    {
        array1[0] = 1;
        array1[1] = 3;
        array1[2] = 5;
        array1[3] = 7;

        merge();

        for (Integer value : array1)
        {
            System.out.print(value + " ");
        }
    }

    private static void merge()
    {
        Integer wirteCursor = array1.length - 1;
        Integer readCursor1 = elementCount - 1;
        Integer readCursor2 = elementCount - 1;
        while(readCursor1 >= 0 && readCursor2 >= 0)
        {
            if (array1[readCursor1] > array2[readCursor2])
            {
                array1[wirteCursor] = array1[readCursor1];
                --readCursor1;
            }
            else
            {
                array1[wirteCursor] = array2[readCursor2];
                --readCursor2;
            }

            --wirteCursor;

        }

        while (readCursor1 >= 0)
        {
            array1[wirteCursor] = array1[readCursor1];
            --readCursor1;
            --wirteCursor;
        }

        while (readCursor2 >= 0)
        {
            array1[wirteCursor] = array2[readCursor2];
            --readCursor2;
            --wirteCursor;
        }
    }
}