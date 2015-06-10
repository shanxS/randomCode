// min cost path
// code question: 67

public class Main
{
    public static void main(String[] we)
    {
        Integer[][] array = {
                {1, 2, 3},
                {4, 8, 2},
                {1, 5, 3} };
        MinCostPath mcp = new MinCostPath();
        System.out.print(mcp.findMinCost(array, 2, 2));
    }
}

class MinCostPath
{
    private Integer[][] array, dp;
    private Integer maxRow, maxCol, targetRow, targetCol;

    public Integer findMinCost(Integer[][] array, Integer targetRow, Integer targetCol)
    {
        this.array = array;
        this.maxCol = array[0].length;
        this.maxRow = array.length;
        this.targetRow = targetRow;
        this.targetCol = targetCol;

        resetDp();

        contemplate(1, 1, array[0][0]);
        contemplate(1, 0, array[0][0]);
        contemplate(0, 1, array[0][0]);

        return dp[targetRow][targetCol];
    }

    private void contemplate(Integer r, Integer c, Integer previousWeight)
    {
        if (!isValidPoint(r, c))
        {
            return;
        }

        Integer thisWeight = previousWeight + array[r][c];

        if (thisWeight < dp[r][c])
        {
            dp[r][c] = thisWeight;
        }

        if (r == targetRow && c == targetCol)
        {
            return;
        }
        else
        {
            contemplate(r+1, c+1, thisWeight);
            contemplate(r+1, c, thisWeight);
            contemplate(r, c+1, thisWeight);
        }
    }

    private void resetDp()
    {
        dp = new Integer[array.length][array[0].length];
        for (Integer r=0; r<array.length; ++r)
        {
            for (Integer c = 0; c < array[0].length; ++c)
            {
                dp[r][c] = Integer.MAX_VALUE;
            }
        }
    }

    private boolean isValidPoint(Integer r, Integer c)
    {
        return (r>=0 && r<maxRow && c>=0 && c<maxCol);
    }
}