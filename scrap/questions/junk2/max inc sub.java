// max inc sub

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {3, 4, 5, 10};//{1, 101, 2, 3,100,4,5};
        (new MaxSubSum()).find(ar);
    }
}

class MaxSubSum
{
    private Integer[] ar;
    public void find(Integer[] ar)
    {
        this.ar = ar;

        Integer max = Integer.MIN_VALUE;
        for (Integer i=0; i<ar.length; ++i)
        {
            int thisSum = ar[i] + contemplate(ar[i], i+1);
            max = Math.max(max, thisSum);
        }

        System.out.print(max);
    }

    private Integer contemplate(int prev, int thisIndex)
    {
        if (thisIndex >= ar.length)
        {
            return 0;
        }

        int max = 0;
        for (Integer i=thisIndex; i<ar.length; ++i)
        {
            int thisSum = 0;
            if (prev < ar[i])
            {
                thisSum += ar[i];
                thisSum += contemplate(ar[i], i + 1);
            }
            else
            {
                thisSum += contemplate(prev, i + 1);
            }

            max = Math.max(max, thisSum);
        }
        return max;
    }
}