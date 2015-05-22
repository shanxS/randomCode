// matrix of 1 and 0 - turn row and col of 1 to 1
// r1, q1, set26

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] matrix = new Integer[][]{
                {1,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,1}
        };

        Map<Integer, Integer> rowCol = new HashMap<>();
        for (Integer r=0; r<matrix.length; ++ r)
        {
            for (Integer c=0; c<matrix[r].length; ++c)
            {
                if (matrix[r][c] == 1)
                {
                    rowCol.put(r,c);
                }
            }
        }

        for(Map.Entry<Integer, Integer> entry : rowCol.entrySet())
        {
            Integer r = entry.getKey();
            for (Integer i=0; i<matrix[r].length; ++i)
            {
                matrix[r][i] = 1;
            }

            Integer c = entry.getValue();
            for (Integer i=0; i<matrix.length; ++i)
            {
                matrix[i][c] = 1;
            }
        }

        for (Integer r=0; r<matrix.length; ++ r)
        {
            for (Integer c = 0; c < matrix[r].length; ++c)
            {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }

    }
}