// min path finder in 2d array

public class Main
{
    public static void main(String[] ew)
    {
        int[][] ar = { {1, 2, 3},
                {4, 8, 2},
                {1, 5, 3} };

        (new MinPathFinder()).find(ar, 2,2, 0,0);
    }
}

class MinPathFinder
{
    private int[][] ar;
    private int tR, tC;

    public void find(int[][] ar, int tR, int tC, int sR, int sC)
    {
        this.ar = ar;
        this.tC = tC;
        this.tR = tR;

        int min = ar[sR][sC] + Math.min(contemplate(sR, sC+1),
                Math.min(contemplate(sR + 1, sC),
                contemplate(sR+1, sC+1)));

        System.out.print(min);

    }

    private int contemplate(int sR, int sC)
    {
        if (sR >= ar.length || sC >= ar[0].length)
        {
            return Integer.MAX_VALUE;
        }
        if (sR == tR && sC == tC)
        {
            return ar[sR][sC];
        }

        return ar[sR][sC] + Math.min(contemplate(sR, sC+1),
                Math.min(contemplate(sR + 1, sC),
                        contemplate(sR+1, sC+1)));
    }
}