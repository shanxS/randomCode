// Maximum Sum Path in Two Arrays

public class Main
{
    public static void main(String[] er)
    {
        int[] ar1 = {2, 3, 7, 10, 12}, ar2 = {1, 5, 7, 8};
        (new CommonSummer()).sum(ar1, ar2);
    }
}

class CommonSummer
{
    final private boolean FIRST = true;
    private int[] ar1, ar2;
    public void sum(int[] ar1, int[] ar2)
    {
        this.ar1 = ar1;
        this.ar2 = ar2;
        int max = Math.max(ar1[0] + contemplate(1, FIRST),
                ar2[0] + contemplate(1, !FIRST));

        System.out.print(max);
    }

    private int contemplate(int thisIndex, boolean isFirst)
    {
        int max = 0;
        if (isFirst)
        {
            if (thisIndex >= ar1.length)
            {
                return max;
            }

            while(thisIndex<ar1.length && thisIndex<ar2.length && ar1[thisIndex] != ar2[thisIndex])
            {
                max += ar1[thisIndex];
                ++thisIndex;
            }

            if ((thisIndex<ar1.length && thisIndex<ar2.length && ar1[thisIndex] == ar2[thisIndex]))
            {
                max += ar1[thisIndex] + Math.max(contemplate(thisIndex+1, isFirst), contemplate(thisIndex+1, !isFirst));
            }
            else
            {
                while(thisIndex<ar1.length)
                {
                    max += ar1[thisIndex];
                    ++thisIndex;
                }
            }
        }
        else
        {
            if (thisIndex >= ar2.length)
            {
                return max;
            }

            while(thisIndex<ar1.length && thisIndex<ar2.length && ar1[thisIndex] != ar2[thisIndex])
            {
                max += ar2[thisIndex];
                ++thisIndex;
            }

            if ((thisIndex<ar1.length && thisIndex<ar2.length && ar1[thisIndex] == ar2[thisIndex]))
            {
                max += ar1[thisIndex] + Math.max(contemplate(thisIndex+1, isFirst), contemplate(thisIndex+1, !isFirst));
            }
            else
            {
                while(thisIndex<ar2.length)
                {
                    max += ar2[thisIndex];
                    ++thisIndex;
                }
            }
        }

        return max;
    }
}