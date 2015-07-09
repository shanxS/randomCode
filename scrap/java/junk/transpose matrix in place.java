// transpose matrix in place

public class Main
{
    private static Integer tmp;
    private static final Integer R = 4, C = 3;
    private static final Integer last = (R*C) - 1;
    private static Integer[] array = new Integer[R*C];


    public static void main(String[] er)
    {
        for (Integer i=0; i<=last; ++i)
        {
            array[i] = i;
        }

        boolean[] copied = new boolean[R*C];
        copied[0] = true;
        copied[last] = true;

        Integer fromIndex = 1;
        while (fromIndex != last)
        {
            tmp = array[fromIndex];
            Integer toIndex = (fromIndex*R)%(last);
            copied[toIndex] = true;
            swap(toIndex);
            while (toIndex != fromIndex)
            {
                toIndex = (toIndex*R)%(last);
                copied[toIndex] = true;
                swap(toIndex);
            }

            while (fromIndex < last && copied[fromIndex])
            {
                ++fromIndex;
            }
        }

        for (Integer i=0; i<=last; ++i)
        {
            System.out.print(array[i] + " ");
        }
    }

    private static void swap(Integer index)
    {
        Integer t = array[index];
        array[index] = tmp;
        tmp = t;
    }
}