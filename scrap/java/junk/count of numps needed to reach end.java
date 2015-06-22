// count of numps needed to reach end

public class Main
{
    private static Integer[] array = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};

    public static void main(String[] er)
    {
        System.out.print(findJumps(0));
    }

    private static Integer findJumps(Integer index)
    {
        if (index == array.length)
        {
            return 0;
        }

        if (index > array.length)
        {
            return null;
        }

        Integer jumpCountFromNext = array.length;
        for (Integer i=1;i<=array[index]; ++i)
        {
            Integer tmp = findJumps(index + i);
            if (tmp != null && tmp < jumpCountFromNext)
            {
                jumpCountFromNext = tmp;
            }
        }

        return 1+jumpCountFromNext;
    }
}