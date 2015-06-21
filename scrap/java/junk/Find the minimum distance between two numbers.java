// Find the minimum distance between two numbers

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {2, 5, 3, 5, 4, 4, 2, 3};//{3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3};
        final Integer value1 = 3;
        Integer value1Index = null;
        for (Integer i=0; i<array.length; ++i)
        {
            if (array[i] == value1)
            {
                value1Index = i;
                break;
            }
        }

        final Integer value2 = 2;
        Integer value2Index = null;
        for (Integer i=0; i<array.length; ++i)
        {
            if (array[i] == value2)
            {
                value2Index = i;
                break;
            }
        }

        Integer minDistance = Math.abs(value1Index-value2Index);

        Integer cursor = Math.max(value1Index, value2Index);
        while (cursor < array.length)
        {
            if (array[cursor] == value1 || array[cursor] == value2)
            {
                if (array[cursor] == value1)
                {
                    value1Index = cursor;
                }

                if (array[cursor] == value2)
                {
                    value2Index = cursor;
                }

                if (Math.abs(value1Index-value2Index) < minDistance)
                {
                    minDistance = value1Index-value2Index;
                }
            }

            ++cursor;
        }

        System.out.print(minDistance);
    }
}