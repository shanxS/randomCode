// rotate matrix
// r1, q4. set29

public class Main
{
    public  static void main(String[] er)
    {
        Integer[][] matrix = new Integer[][]{
                {1, 2, 3, 4},
                {6, 7, 8, 9}
        };

        Integer ROW = matrix.length;
        Integer COL = matrix[0].length;

        Integer[][] rotated = new Integer[COL][ROW];
        for (Integer r = 0; r < ROW; ++r)
        {
            for (Integer c=0; c<COL; ++c)
            {
                rotated[c][ROW-r-1] = matrix[r][c];
            }
        }

        for (Integer i=0; i<rotated.length; ++i)
        {
            for(Integer j=0; j<rotated[0].length; ++j)
            {
                System.out.print(rotated[i][j] + " ");
            }
            System.out.println();
        }
    }
}