// test if array is majority array
// set 187, telephonic q2
// code question

public class Main
{
    static Integer[] array = new Integer[]{5, 2, 5, 1, 5, 3, 5};
    public static void main(String[] er)
    {
        Integer candidate = find();

        Integer count = 0;
        for (Integer value : array)
        {
            if (candidate == value)
            {
                ++count;
            }
        }

        if (count > array.length/2)
        {
            System.out.print(candidate);
        }
        else
        {
            System.out.print("not possible");
        }
    }

    private static Integer find()
    {
        Integer index = 0;
        Integer candidate = array[index];
        Integer count = 1;

        for (Integer cursor = 1; cursor<array.length; ++cursor)
        {
            if (array[cursor] == candidate)
            {
                ++count;
            }
            else
            {
                --count;
            }

            if (count == 0)
            {
                candidate = array[cursor];
                count = 1;
            }
        }

        return candidate;
    }
}