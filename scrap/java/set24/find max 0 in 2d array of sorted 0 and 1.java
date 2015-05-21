// find max 0 in 2d array of sorted 0 and 1
// r3, q2, set24

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] matrix = new Integer[][]{
                {0,0,1,1,1},
                {0,1,1,1,1},
                {0,0,0,0,0},
                {0,0,0,0,1},
                {1,1,1,1,1}
        };

        Integer max1Index = Integer.MIN_VALUE;
        for (Integer i=0; i<matrix.length; ++i)
        {
            Integer index1 = find1(matrix[i]);
            if (index1 > max1Index)
            {
                max1Index = index1;
            }
        }

        System.out.print(max1Index);
    }

    private static Integer find1(Integer[] array)
    {
        Integer start = 0;
        Integer end = array.length-1;

        while(start <= end)
        {
            Integer mid = (start + end)/2;

            if (array[mid] == 1)
            {
                if (mid == 0 || mid == array.length-1)
                {
                    return mid;
                }
                else if (array[mid-1] == 0)
                {
                    return mid;
                }
                else
                {
                    end = mid-1;
                }
            }
            if (array[mid] == 0)
            {
                start = mid + 1;
            }
        }

        // cant find 1
        return array.length;
    }
}