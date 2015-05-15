// find the longest sub sequence which contains only unique characters
// f2f 1, q4

public class Main
{
    public static void main(String[] args)
    {
//        String str = "shashank";
//        String str = "sumedha";
        String str = "abcdaefghija";

        Integer[] cache = new Integer[26];
        for (Integer i=0; i<cache.length; ++i)
        {
            cache[i] = -1;
        }

        cache[index(str.charAt(0))] = 0;
        Integer currentMax = 1;
        Integer maxSoFar = currentMax;
        for (Integer cursor=1; cursor<str.length(); ++cursor)
        {
            Integer previousIndex = cache[index(str.charAt(cursor))];

            if (previousIndex == -1 || cursor-currentMax > previousIndex)
            {
                cache[index(str.charAt(cursor))] = cursor;
                ++currentMax;
            }
            else
            {
                if (maxSoFar < currentMax)
                {
                    maxSoFar = currentMax;
                }

                currentMax = 1;
                cache[index(str.charAt(cursor))] = cursor;
            }

        }
        if (maxSoFar < currentMax)
        {
            maxSoFar = currentMax;
        }

        System.out.print("maz liength " + maxSoFar);

    }

    private static Integer index(char c)
    {
        return c - 'a';
    }
}