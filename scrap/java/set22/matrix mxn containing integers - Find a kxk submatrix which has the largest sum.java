// matrix mxn containing integers - Find a kxk submatrix which has the largest sum
// r2, q1, set22

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] matrix = new Integer[][]{
                {1,2,3,4,5,6},
                {7,8,9,10,11,12},
                {13,14,15,16,17,18}
        };

        SubMatrixFinder smf = new SubMatrixFinder(matrix);
        smf.find1D(3);
        smf.find2D(2);
    }
}

class SubMatrixFinder
{
    private Integer[][] matrix;

    public SubMatrixFinder(Integer[][] matrix)
    {
        this.matrix = matrix;
    }

    public Integer find2D(Integer k)
    {
        Integer colCount = matrix[0].length;
        Integer rowCount = matrix.length;

        if (rowCount < k || colCount < k)
        {
            System.out.print("too small matrix");
            return null;
        }

        Integer max = Integer.MIN_VALUE;
        Integer maxC = -1;
        Integer maxR = -1;
        for(Integer r=k; r<=rowCount; ++r)
        {
            Integer previousCount = 0;
            for (Integer c=0; c<colCount; ++c)
            {
                if (c < k)
                {
                    for (Integer i=r-k; i<r; ++i)
                    {
                        previousCount += matrix[i][c];
                    }
                }
                else
                {
                    if (max < previousCount)
                    {
                        max = previousCount;
                        maxC = c-k;
                        maxR = r-k;
                    }

                    Integer toBeSub = 0;
                    Integer toBeAdd = 0;
                    for (Integer i=r-k; i<r; ++i)
                    {
                        toBeSub += matrix[i][c-k];
                        toBeAdd += matrix[i][c];
                    }

                    previousCount -= toBeSub;
                    previousCount += toBeAdd;
                }
            }

            if (max < previousCount)
            {
                max = previousCount;
                maxC = colCount-k;
                maxR = r-k;
            }


        }

        return max;
    }

    public Integer find1D(Integer k)
    {
        Integer colCount = matrix[0].length;
        Integer rowCount = matrix.length;

        Integer max = Integer.MIN_VALUE;
        for(Integer r=0; r<rowCount; ++r)
        {
            Integer previousCount = 0;
            for (Integer c=0; c<colCount; ++c)
            {
                if (c<k)
                {
                    previousCount += matrix[r][c];
                }
                else
                {
                    if (max < previousCount)
                    {
                        max = previousCount;
                    }

                    previousCount += matrix[r][c];
                    previousCount -= matrix[r][c-k];
                }
            }


            if (max < previousCount)
            {
                max = previousCount;
            }
        }

        return max;
    }
}