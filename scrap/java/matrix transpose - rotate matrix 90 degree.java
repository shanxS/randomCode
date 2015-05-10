// matrix transpose - rotate matrix 90 degree

public class Main
{
    public static void main(String[] args)
    {
        Integer[][] matrix = new Integer[][] {
                {1,2,3,4},
                {10,11,12,5},
                {9,8,7,6}
        };

        Rotator rotator = new Rotator(matrix);
        Integer[][] result = rotator.rotate();
        for (Integer r=0; r<result.length; ++r)
        {
            for (Integer c=0; c<result[r].length; ++c)
            {
                System.out.print(result[r][c] + " ");
            }

            System.out.println();
        }
    }
}

class Rotator
{
    private Integer[][] matrix;
    private Integer maxRow, maxCol;
    private Integer[][] rotatedMatrix;

    public Rotator(Integer[][] matrix)
    {
        this.matrix = matrix;
        this.maxRow = matrix.length;
        this.maxCol = matrix[0].length;
        this.rotatedMatrix = new Integer[maxCol][maxRow];
    }

    public Integer[][] rotate()
    {

        for (Integer r = 0; r<maxRow; ++r)
        {
            for (Integer c = 0; c<maxCol; ++c)
            {
                rotatedMatrix[c][maxRow-1-r] = matrix[r][c];
            }
        }

        return rotatedMatrix;
    }
}