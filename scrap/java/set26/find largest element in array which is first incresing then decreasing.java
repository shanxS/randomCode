// find largest element in array which is first incresing then decreasing
// r4, q2, set26

public class Main
{
    public static void main(String[] er)
    {
        //Integer[] array = new Integer[]{10,9,8,7,6,3};
        //Integer[] array = new Integer[]{6,7,8,9,10};
        Integer[] array = new Integer[]{6,7,8,9,10,9,8,7,6,3};
        System.out.print(getMax(array));
    }

    private static Integer getMax(Integer[] array)
    {
        Integer start = 0;
        Integer end = array.length - 1;

        while(start <= end)
        {
            Integer mid = (start + end)/2;

            if (mid+1 == array.length || mid == 0)
            {
                return array[mid];
            }
            else if (array[mid] > array[mid+1]
                    && array[mid] > array[mid-1])
            {
                return array[mid];
            }
            else if (array[mid] < array[mid+1])
            {
                start = mid+1;
            }
            else if (array[mid] > array[mid+1])
            {
                end = mid-1;
            }
        }

        return null;
    }
}