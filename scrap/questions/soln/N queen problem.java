// N queen problem

public class Main
{
    public static void main(String[] er)
    {
        NQueenPlacer nqp = new NQueenPlacer();
        Integer N = 4;
        int[][] maze = nqp.findPositions(N);
        for (Integer r=0; r<N; ++r)
        {
            for (Integer c=0; c<N; ++c)
            {
                System.out.print(maze[r][c] + " ");
            }
            System.out.println();
        }
    }
}


class NQueenPlacer
{
    private int[][] board;
    private Integer N;

    public int[][] findPositions(Integer N)
    {
        this.N = N;
        this.board = new int[N][N];

        Integer r = 0;
        Boolean found = false;
        for (Integer c=0; c<N; ++c)
        {
            board[r][c] = 1;
            if (find(r+1, N-1))
            {
                return board;
            }
            board[r][c] = 0;
        }

        return null;
    }

    private boolean find(Integer r, Integer queenCount)
    {
        if (queenCount == 0)
        {
            return true;
        }

        for (Integer c=0; c<N; ++c)
        {
            if (testSafty(r, c))
            {
                board[r][c] = 1;
                if (find(r + 1, queenCount - 1))
                {
                    return true;
                }
                board[r][c] = 0;
            }
        }

        return false;
    }

    private boolean testSafty(Integer r, Integer c)
    {
        return testColum(c)
                && testRow(r)
                && testLeftDiagonal(r, c)
                && testRightDiagonal(r,c);
    }

    private boolean testRightDiagonal(Integer r, Integer c)
    {
        return testRightUpDiagonal(r,c )
                && testRightDownDiagonal(r, c);
    }

    private boolean testRightDownDiagonal(Integer R, Integer C)
    {
        for(Integer r=R, c=C; r<N && c<N; ++r, ++c)
        {
            if (board[r][c] == 1)
            {
                return false;
            }
        }

        return true;
    }

    private boolean testRightUpDiagonal(Integer R, Integer C)
    {
        for (Integer r=R, c=C; r>=0 && c>=0; --r, --c)
        {
            if (board[r][c] == 1)
            {
                return false;
            }
        }

        return true;
    }

    public void printBoard()
    {
        for (Integer r = 0; r < N; ++r )
        {
            for (Integer c=0; c<N; ++c)
            {
                System.out.print(board[r][c]);
            }

            System.out.println();
        }
    }

    private boolean testLeftDiagonal(Integer r, Integer c)
    {
        return testLeftUpDiagonal(r, c)
                && testLeftDownDiagonal(r, c);
    }

    private boolean testLeftDownDiagonal(Integer R, Integer C)
    {
        for (Integer r=R, c=C; r<N && c>=0; ++r, --c)
        {
            if (board[r][c] == 1)
            {
                return false;
            }
        }

        return true;
    }

    private boolean testLeftUpDiagonal(Integer R, Integer C)
    {
        for (Integer r=R, c=C; r>=0 && c<N; --r, ++c)
    {
            if (board[r][c] == 1)
            {
                return false;
            }
        }

        return true;
    }

    private boolean testRow(Integer r)
    {
        for (Integer c=0; c<N; ++c)
        {
            if (board[r][c] == 1)
            {
                return false;
            }
        }

        return true;
    }

    private boolean testColum(Integer c)
    {
        for (Integer r=0; r<N; ++r)
        {
            if (board[r][c] == 1)
            {
                return false;
            }
        }

        return true;
    }
}