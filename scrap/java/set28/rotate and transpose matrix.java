// rotate and transpose matrix
// r1, q2, set28

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] matrix = new Integer[][]{
                {1,2,3,4},
                {5,6,7,8}
        };
        final Integer ROW = matrix.length;
        final Integer COL = matrix[0].length;

        Integer[][] newMatrix = new Integer[matrix[0].length][matrix.length];

        System.out.println("rotating: ");
        for (Integer r=0; r<ROW; ++r)
        {
            for (Integer c=0; c<COL; ++c)
            {
                newMatrix[c][ROW-r-1] = matrix[r][c];
            }
        }
        for (Integer r=0; r<COL; ++r)
        {
            for (Integer c = 0; c < ROW; ++c)
            {
                System.out.print(newMatrix[r][c] + " ");
            }
            System.out.println();
        }

        System.out.println("transposing: ");
        for (Integer r=0; r<ROW; ++r)
        {
            for (Integer c=0; c<COL; ++c)
            {
                newMatrix[c][r] = matrix[r][c];
            }
        }
        for (Integer r=0; r<COL; ++r)
        {
            for (Integer c = 0; c < ROW; ++c)
            {
                System.out.print(newMatrix[r][c] + " ");
            }
            System.out.println();
        }

    }
}