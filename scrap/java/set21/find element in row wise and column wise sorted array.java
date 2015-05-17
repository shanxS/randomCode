// find element in row wise and column wise sorted array
// r2, q3, set21

public class Main
{
    public static void main(String[] wer)
    {
        Integer[][] array = new Integer[][] {
                {1, 2, 3, 4},
                {2, 3, 4, 5},
                {3, 4, 5, 6},
                {4, 5, 6, 7},
                {5, 6, 7, 8}
        };

        Integer r=0;
        Integer c=array[0].length-1;
        Integer target = 6;
        while(r < array.length
                && c>=0)
        {
            if (array[r][c] == target)
            {
                System.out.print("found");
                break;
            }
            if (array[r][c] < target)
            {
                ++r;
            }
            else
            {
                --c;
            }
        }
    }
}