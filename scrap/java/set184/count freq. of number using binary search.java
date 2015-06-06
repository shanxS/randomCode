// count freq. of number using binary search
// r3, q2, set184
// code question: 50

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[] {1,1,1,2,2,2,3,3,3,3,4};
        ModifiedBinarySearch mfb = new ModifiedBinarySearch();

        //Integer target =4;
        //Integer target =3;
        Integer target =41;
        System.out.print(mfb.findLast(array, target) - mfb.findFirst(array, target) + 1);
    }
}

class ModifiedBinarySearch
{
    public Integer findLast(Integer[] array, Integer target)
    {
        Integer start = 0;
        Integer end = array.length-1;

        while(start <= end)
        {
            Integer mid = start + (end-start)/2;

            if (array[mid] == target)
            {
                if ((mid + 1 < array.length && array[mid + 1] != target)
                        || (mid == array.length-1))
                {
                    return mid;
                }
                else
                {
                    start = mid+1;
                }
            }
            else if (array[mid] < target)
            {
                start = mid+1;
            }
            else
            {
                end = mid-1;
            }
        }

        return null;
    }

    public Integer findFirst(Integer[] array, Integer target)
    {
        Integer start = 0;
        Integer end = array.length-1;

        while(start <= end)
        {
            Integer mid = start + (end-start)/1;

            if (array[mid] == target)
            {
                if ((mid > 0 && array[mid-1] != target)
                        || (mid == 0))
                {
                    return mid;
                }
                else
                {
                    end = mid-1;
                }
            }
            else if (array[mid] > target)
            {
                end = mid-1;
            }
            else
            {
                start = mid+1;
            }
        }

        return null;
    }
}