// find multiple repeated numbers in array of size n with values in range 1 to n
// code question: 110

public class Main
{
    private static Integer[] array = {1, 2, 3, 1, 3, 6, 6};

    public static void main(String[] er)
    {
        for (Integer i=0; i<array.length;)
        {
            if (!setValueFor(i))
            {
                ++i;
            }
        }

        for (Integer i=0; i<array.length; ++i)
        {
            System.out.println((i+1) + " :" + Math.abs(array[i] / (i + 1)));
        }
    }

    private static Boolean setValueFor(Integer index)
    {
        Integer value = array[index];
        Boolean swappedValues = false;
        if (value < 0)
        {
            return swappedValues;
        }

        Integer targetIndex = value-1;


        if (array[targetIndex] > 0)
        {
            array[index] = array[targetIndex];
            array[targetIndex] = -1*value;

            swappedValues = true;
        }
        else
        {
            array[index] = 0;
            array[targetIndex] += -1*value;

            swappedValues = false;
        }

        return swappedValues;
    }
}