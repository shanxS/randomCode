// given array place negative and positive alternativly

public class Main
{
    private static Integer[] array = {-1, 2, -3, 4, 5, 6, -7, 8, 9};
    private static Integer writer = 0;
    private static Integer seeker = 0;
    final private static Boolean positive = true;
    final private static Boolean negative = false;

    public static void main(String[] er)
    {
        Boolean currentType = positive;

        while (writer < array.length)
        {
            if (array[writer] > 0 != currentType)
            {
                swap(writer, currentType);
            }

            currentType = !currentType;
            ++writer;
        }

        for (Integer v : array)
        {
            System.out.print(v + " ");
        }
    }

    private static void swap(Integer writeIndex, Boolean targetType)
    {
        if (seeker >= array.length)
        {
            return;
        }

        if (seeker < writeIndex || array[seeker] > 0 != targetType)
        {
            if (seeker < writeIndex)
            {
                seeker = 1+writeIndex;
            }

            while (seeker < array.length && array[seeker] > 0 != targetType)
            {
                ++seeker;
            }

            if (seeker >= array.length)
            {
                return;
            }
        }

        Integer tmp = array[seeker];
        array[seeker] = array[writeIndex];
        array[writeIndex] = tmp;
    }
}