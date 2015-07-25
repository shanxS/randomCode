// find min and max with min comparision

public class Main
{
    private static Integer[] ar = {9,2,7,3,6,4,5};
    public static void main(String[] er)
    {
        Pair p = process(0, ar.length-1);

        System.out.print(p.max + " " + p.min);
    }

    private static Pair process(Integer start, Integer end)
    {
        if (end-start == 2)
        {
            Integer localMax = Math.max(ar[start], Math.max(ar[start+1], ar[end]));
            Integer localMin = Math.min(ar[start], Math.min(ar[start + 1], ar[end]));

            return new Pair(localMin, localMax);
        }

        if (end-start == 1)
        {
            return new Pair(Math.min(ar[start], ar[end]),
                    Math.max(ar[start], ar[end]));
        }

        Integer mid = start + (end-start)/2;
        Pair p1 = process(start, mid);
        Pair p2 = process(mid+1, end);

        Integer thisMax = Math.max(p1.max, p2.max);
        Integer thisMin = Math.min(p1.min, p2.min);

        return new Pair(thisMin, thisMax);
    }
}

class Pair
{
    public Integer min, max;

    public Pair(Integer min, Integer max)
    {
        this.min = min;
        this.max = max;
    }
}