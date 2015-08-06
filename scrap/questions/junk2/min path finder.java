// min path finder

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] ar = { {1, 2, 3},
                           {4, 8, 2},
                           {1, 5, 3} };

        System.out.print((new MinPath()).find(ar, 1,1));
    }
}

class MinPath
{
    private Integer[][] ar;
    private Integer R, C;
    public Integer find(Integer[][] ar, Integer destR, Integer destC)
    {
        this.ar = ar;
        C = destC;
        R = destR;

        Integer thisR = 0, thisC = 0;
        return ar[thisR][thisC] + Math.min(compute(thisR + 1, thisC),
                Math.min(compute(thisR, thisC + 1), compute(thisR + 1, thisC + 1)));
    }

    private Integer compute(int thisR, int thisC)
    {
        if (thisC > C || thisR > R)
        {
            return Integer.MAX_VALUE;
        }
        else if (thisC == C && thisR == R)
        {
            return ar[thisC][thisR];
        }

        return ar[thisC][thisR] + Math.min(compute(thisR+1, thisC),
                Math.min(compute(thisR, thisC+1), compute(thisR+1, thisC+1)));
    }
}