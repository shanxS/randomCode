// find number in rowwise and columnwise sorted matrix
// telphonic1, q2, set28

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] matrix = new Integer[][]{
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {27, 29, 37, 48},
            {32, 33, 39, 50}
        };

        Integer target = 70;
        Integer c=matrix[0].length-1;
        Integer r=0;
        while (c>=0 && r<matrix.length)
        {
            if (matrix[r][c] == target)
            {
                System.out.print("fond at " + r + " " + c);
                break;
            }
            else if (matrix[r][c] > target)
            {
                --c;
            }
            else
            {
                ++r;
            }
        }

        if (c < 0 || r == matrix.length)
        {
            System.out.print("cant find");
        }
    }
}