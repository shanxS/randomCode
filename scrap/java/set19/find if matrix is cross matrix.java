// find if matrix is cross matrix
// r1, q1, set19
// A cross-matrix is a one in which all the diagonal elements are same and not repeated anywhere else.

public class Main
{
    public static void main(String[] args)
    {
//        Integer[][] arr = new Integer[][]{
//                {1,2,3},
//                {4,5,6},
//                {7,8,9}
//        };

        Integer[][] arr = new Integer[][]{
                {2,3,3},
                {4,2,6},
                {7,8,2}
        };

        CrossMatrix cm = new CrossMatrix(arr);
        System.out.print(cm.isCrossMatrix());
    }
}

class CrossMatrix
{
    private Integer[][] matrix;

    public CrossMatrix(Integer[][] matrix)
    {
        this.matrix = matrix;
    }

    public Boolean isCrossMatrix()
    {
        if (matrix.length != matrix[0].length)
        {
            return false;
        }

        Integer firstElememt = matrix[0][0];
        for (Integer i=1, j=1; i<matrix.length; ++i, ++j)
        {
            if (matrix[i][j] != firstElememt)
            {
                return false;
            }
        }

        for (Integer i=0; i<matrix.length; ++i)
        {
            for (Integer j=0; j<matrix.length; ++j)
            {
                if (i != j && matrix[i][j] == firstElememt)
                {return false;}
            }
        }

        return true;
    }
}