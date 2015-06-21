// in sorted array get index at which has same value

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {-10, -5, 3, 4, 7, 9};//{0, 2, 5, 8, 17};//{-10, -5, 0, 3, 7};

        Integer startIndex = 0;
        Integer endIndex = array.length - 1;
        Integer mid = null;

        while (startIndex <= endIndex)
        {
            mid = startIndex + (endIndex-startIndex)/2;

            if (mid == array[mid])
            {
                System.out.print("found at " + mid);
                break;
            }
            else if (mid > array[mid])
            {
                startIndex = mid+1;
            }
            else
            {
                endIndex = mid-1;
            }
        }
    }
}