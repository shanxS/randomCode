// in matrix of cell is one - make entire row and column 1
// r3, q1, set17

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        Integer[][] matrix = new Integer[][] {
                {0,0,1,0},
                {0,0,0,0},
                {1,0,0,0}
        };

        Map<Integer, Integer> rowCol = new HashMap<>();
        for (Integer r=0; r<matrix.length; ++r)
        {
            for (Integer c=0; c<matrix[0].length; ++c)
            {
                if (matrix[r][c] == 1)
                {
                    rowCol.put(r,c);
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : rowCol.entrySet())
        {
            Integer row = entry.getKey();
            for (Integer c=0; c<matrix[row].length; ++c)
            {
                matrix[row][c] = 1;
            }

            Integer col = entry.getValue();
            for (Integer r=0; r<matrix.length; ++r)
            {
                matrix[r][col] = 1;
            }
        }

        for (Integer r=0; r<matrix.length; ++r)
        {
            for (Integer c = 0; c < matrix[0].length; ++c)
            {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }
}