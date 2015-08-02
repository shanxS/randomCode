// max non consecutive sum

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {3, 2, 5, 10, 7};
        System.out.print((new MaxNonConsecutiveSum()).find(ar));
    }
}

class MaxNonConsecutiveSum
{
    private Integer[] ar;
    public Integer find(Integer[] ar)
    {
        this.ar = ar;

        Integer thisIndex = 0;
        return Math.max(ar[thisIndex] + contemplate(thisIndex+2), contemplate(thisIndex+1));
    }

    private Integer contemplate(int thisIndex)
    {
        if (thisIndex >= ar.length)
        {
            return 0;
        }

        return Math.max(ar[thisIndex] + contemplate(thisIndex+2), contemplate(thisIndex+1));
    }
}