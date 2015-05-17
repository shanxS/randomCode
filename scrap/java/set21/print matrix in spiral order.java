// print matrix in spiral order
// r4, q1, set21

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] matrix = new Integer[][] {

                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15}
        };

        Integer startCol = 0;
        Integer startRow = 0;
        Integer endCol = matrix[0].length-1;
        Integer endRow = matrix.length-1;
        Integer r, c;

        while(endCol - startCol > 2 && startRow <= endRow)
        {
                r = startRow;
                c = startCol;
                while (c <= endCol)
                {
                    System.out.print(matrix[r][c] + " ");
                    ++c;
                }
                System.out.println();
                ++startRow;

                c = endCol;
                r = startRow;
                while (r <= endRow)
                {
                    System.out.print(matrix[r][c] + " ");
                    ++r;
                }
                System.out.println();
                --endCol;

                r = endRow;
                c = endCol;
                while (c >= startCol)
                {
                    System.out.print(matrix[r][c] + " ");
                    --c;
                }
                System.out.println();
                --endRow;

                c = startCol;
                r = endRow;
                while (r >= startRow)
                {
                    System.out.print(matrix[r][c] + " ");
                    --r;
                }
                System.out.println();
                ++startCol;
        }

        r = startRow;
        while (endCol >= startCol)
        {
            System.out.print(matrix[r][startCol] + " ");
            ++startCol;
        }
    }
}