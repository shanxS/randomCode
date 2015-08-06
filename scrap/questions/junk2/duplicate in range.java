// duplicate in range

import java.util.HashSet;

public class Main
{
    public static void main(String[] er)
    {
        (new RangeDuplicateFinder()).find(new Integer[]{1, 2, 3, 4, 4}, 3);
    }
}

class RangeDuplicateFinder
{
    public void find(Integer[] ar, Integer k)
    {
        HashSet<Integer> hash = new HashSet<>();

        Integer start = 0, end = 0;

        while (end < ar.length)
        {
            if (!hash.add(ar[end]))
            {
                System.out.print(ar[end]);
            }

            if (end - start >= k)
            {
                hash.remove(ar[start]);
                ++start;
            }

            ++end;
        }
    }
}