// building brides for matching pair of cities
// https://stackoverflow.com/questions/7288585/building-bridges-problem-how-to-apply-longest-increasing-subsequence
// code question: 130

import java.util.Arrays;

public class Main
{
    public static void main(String[] er)
    {
        CountBridges cb = new CountBridges();
        System.out.print(cb.find(makePairs()));
    }

    private static Pair[] makePairs()
    {
//        Pair[] pairs = {
//                new Pair(2,6),
//                new Pair(5,4),
//                new Pair(8,1),
//                new Pair(10,2)
//        };

        Pair[] pairs = {
            new Pair(22,4),
            new Pair(2,6),
            new Pair(10,3),
            new Pair(15,12),
            new Pair(9,8),
            new Pair(17,17),
            new Pair(4,2)
        };

        return pairs;
    }
}

class CountBridges
{
    private Pair[] pairs;
    private Integer max;
    private Integer[] maxValues;

    public Integer find(Pair[] pairs)
    {
        Arrays.sort(pairs);
        this.pairs = pairs;

        max = Integer.MIN_VALUE;
        maxValues = new Integer[pairs.length];
        LIS(0);

        return max;
    }

    private void LIS(Integer thisIndex)
    {
        if (thisIndex == pairs.length-1)
        {
            maxValues[thisIndex] = 1;
            max = Math.max(max, maxValues[thisIndex]);
        }
        else
        {
            LIS(thisIndex+1);
        }

        Integer maxLen = 1;
        for (Integer i=thisIndex+1; i<pairs.length; ++i)
        {
            if (pairs[thisIndex].s <= pairs[i].s)
            {
                maxLen = Math.max(maxLen, 1 + maxValues[i]);
            }
        }

        maxValues[thisIndex] = maxLen;
        max = Math.max(max, maxValues[thisIndex]);
    }
}

class Pair implements Comparable
{
    final Integer n, s;

    public Pair(Integer n, Integer s)
    {
        this.n = n;
        this.s = s;
    }

    @Override
    public int compareTo(Object o)
    {
        Pair otherPair = (Pair) o;

        if (otherPair.n > n)
        {
            return -1;
        }
        else if (otherPair.n < n)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}