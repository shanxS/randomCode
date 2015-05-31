// gold coin problem
// r5, q5, set33
// code question 26

public class Main
{
    public static void main(String[] er)
    {
        Integer[] coins = new Integer[]{8, 15, 3, 7};//{5, 3, 7, 10};

        GlodBoxAssitant ga = new GlodBoxAssitant();
        System.out.print(ga.guaranteedWin(coins));
    }
}

class GlodBoxAssitant
{
    private Integer[][] dp;
    private Integer[] coins;
    final private Integer INVALID = -1;

    public Integer guaranteedWin(Integer[] coins)
    {
        this.dp = new Integer[coins.length][coins.length];
        this.coins = coins;
        resetDp();

        Boolean myTurn = true;
        Integer win = 0;
        Integer forwardCursoor = 0;
        Integer reverseCursor = coins.length - 1;

        while(forwardCursoor < reverseCursor)
        {
            if (test(forwardCursoor + 1, reverseCursor, !myTurn)
                    > test(forwardCursoor, reverseCursor - 1, !myTurn))
            {
                if (myTurn)
                {
                    win += coins[forwardCursoor];
                    setDp(forwardCursoor, reverseCursor, win);
                }
                ++forwardCursoor;
            }
            else
            {
                if (myTurn)
                {
                    win += coins[reverseCursor];
                    setDp(forwardCursoor, reverseCursor, win);
                }
                --reverseCursor;
            }

            myTurn = !myTurn;
        }

        return win;
    }

    private Integer test(Integer forwardCursoor, Integer reverseCursor, Boolean myTurn)
    {
        Integer win = 0;
        while(forwardCursoor < reverseCursor)
        {
            if (test(forwardCursoor + 1, reverseCursor, !myTurn)
                    > test(forwardCursoor, reverseCursor - 1, !myTurn))
            {
                if (myTurn)
                {
                    win += coins[forwardCursoor];
                    setDp(forwardCursoor, reverseCursor, win);
                }
                ++forwardCursoor;
            }
            else
            {
                if (myTurn)
                {
                    win += coins[reverseCursor];
                    setDp(forwardCursoor, reverseCursor, win);
                }
                --reverseCursor;
            }

            myTurn = !myTurn;
        }

        return win;
    }

    private void resetDp()
    {
        for (Integer r=0; r<dp.length; ++r)
        {
            for (Integer c=0; c<dp[0].length; ++c)
            {
                dp[r][c] = INVALID;
            }
        }
    }

    private void setDp(Integer forwardCursoor, Integer reverseCursor, Integer win)
    {
        dp[forwardCursoor][reverseCursor]  = win;
        dp[reverseCursor][forwardCursoor]  = win;
    }
}