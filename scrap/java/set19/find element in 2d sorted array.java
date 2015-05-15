// find element in 2d sorted array
// http://articles.leetcode.com/2010/10/searching-2d-sorted-matrix-part-ii.html
// f2f 1, q3

public class Main
{
    public static void main(String[] qwe)
    {
        Integer[][] array = new Integer[][] {
                {1,4,7,10},
                {2,5,8,11},
                {3,6,9,12}
        };

        Integer rowCount = array.length;
        Integer colCount = array[0].length;

        Integer target = 11;
        Boolean found = false;
        Integer r=0, c=colCount-1;
        while(r < rowCount && c >= 0)
        {
            if (array[r][c] == target)
            {
                found = true;
                break;
            }
            else if (array[r][c] > target)
            {
                --c;
            }
            else if (array[r][c] < target)
            {
                ++r;
            }
        }

        if (found)
        {
            System.out.print("found at " + r + " " + c);
        }
        else
        {
            System.out.print("not found");
        }
    }
}