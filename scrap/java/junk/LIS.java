import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {2, 5, 3, 7, 11, 8, 10, 13, 6 };//{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        Integer length = 0;
        List<Integer> list = new ArrayList<>();

        for (Integer i=0;i<array.length; ++i)
        {
            if (i==0)
            {
                list.add(array[i]);
                ++length;
            }
            else if (array[i] > list.get(length-1))
            {
                list.add(array[i]);
                ++length;
            }
            else
            {
                Integer replacementIndex = getReplacementIndex(list, array[i]);
                list.set(replacementIndex, array[i]);
            }
        }

        System.out.println(length);

        list.stream().forEach(x -> System.out.print(x + " "));
    }

    private static Integer getReplacementIndex(List<Integer> list, Integer value)
    {
        Integer cursor = list.size()-1;
        while (cursor >= 0 && list.get(cursor) > value)
        {
            --cursor;
        }

        return cursor+1;
    }
}