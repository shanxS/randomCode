// mutiple rigid partitions

public class Main
{
    private Integer[] ar;

    public static void main(String[] er)
    {
        Main obj = new Main();
        System.out.print(obj.findCost(new Integer[]{100,200,300,400,500,600,700,800,900},
                3));
    }

    private Integer findCost(Integer[] ar, Integer k)
    {
        this.ar = ar;
        return findCostFor(ar.length-1, k);
    }

    private Integer findCostFor(Integer end, Integer k)
    {
        if (k == 1)
        {
            return computeSum(0, end);
        }
        else if (end == 0)
        {
            return ar[end];
        }

        Integer thisCost = Integer.MAX_VALUE;
        for (Integer from=0; from<=end; ++from)
        {
            thisCost = Math.min(thisCost,
                    Math.max(findCostFor(from, k - 1), computeSum(from+1, end)));
        }

        return thisCost;
    }

    private Integer computeSum(Integer from, Integer to)
    {
        Integer sum = 0;
        for (Integer i=from; i<=to; ++i)
        {
            sum += ar[i];
        }

        return sum;
    }
}

