// sorting a matrix according to column
// r5, q1. set14

public class Main
{
    public static void main(String[] args)
    {
        Integer[][] matrix = new Integer[][] {
                {1,1,1,3,4,5},
                {2,2,9,4,9,7},
                {3,3,2,8,3,0},
                {4,4,8,7,2,3},
                {5,5,3,1,3,5},
                {6,6,7,4,3,7}
        };
        for (Integer[] array : matrix)
        {
            for (Integer i : array)
            {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("----------------");

        Sorter sorter = new Sorter(matrix,2);
        for (Integer[] array : sorter.sort())
        {
            for (Integer i : array)
            {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}

class Sorter
{
    private Integer[][] matrix;
    private Integer column;

    public Sorter(Integer[][] matrix, Integer column)
    {
        this.matrix = matrix;
        this.column = column;
    }

    public Integer[][] sort()
    {
        sort(0, matrix.length);
        return matrix;
    }

    private void sort(final Integer start, final Integer end)
    {
        if (start >= end)
        {
            return;
        }

        Integer pivotIndex = end - 1;

        Integer cursor = pivotIndex - 1;
        while (cursor >= start)
        {
            if (matrix[cursor][column] > matrix[pivotIndex][column])
            {
                if (cursor + 1 == pivotIndex)
                {
                    swap(cursor, pivotIndex);
                }
                else
                {
                    swap(pivotIndex, pivotIndex - 1);
                    swap(cursor, pivotIndex);
                }
                --pivotIndex;
            }

            --cursor;
        }

        sort(start, pivotIndex);
        sort(pivotIndex+1, end);
    }

    private void swap(Integer index1, Integer index2)
    {
        Integer tmp;
        for (Integer i=0; i<matrix[index1].length; ++i)
        {
            tmp = matrix[index1][i];
            matrix[index1][i] = matrix[index2][i];
            matrix[index2][i] = tmp;
        }
    }
}