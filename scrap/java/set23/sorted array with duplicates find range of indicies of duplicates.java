// sorted array with duplicates find range of indicies of duplicates
// related: r3, q1, set23

public class Main
{
    public static void main(String[] wer)
    {
//        Integer[] array = new Integer[]{0,0,0};
//        BinarySearch bs = new BinarySearch();
//        System.out.print(bs.findFirstInDuplicate(array));

//        Integer[] array = new Integer[]{1,3,5,6,7,10};
//        BinarySearch bs = new BinarySearch();
//        System.out.print(bs.find(10, array));

        //Integer[] array = new Integer[]{2,2,2,2,2,2,2,2,2,2,2,2,3,3,3};
        Integer[] array = new Integer[]{0,2,3,3,4,5,5,5};
        BinarySearch bs = new BinarySearch();
        System.out.println(bs.findFirstOf(2, array));
        System.out.println(bs.findLastOf(2, array));
    }
}

class BinarySearch
{
    public Integer findFirstOf(Integer key, Integer[] array)
    {
        Integer start = 0;
        Integer end = array.length - 1;

        while(start <= end)
        {
            Integer mid = (start + end)/2;

            if (array[mid] == key)
            {
                if (isValidIndex(mid-1, array.length))
                {
                    if (array[mid-1] == key)
                    {
                        end = mid-1;
                    }
                    else
                    {
                        return mid;
                    }
                }
                else
                {
                    return mid;
                }
            }
            else if (array[mid] > key)
            {
                end = mid-1;
            }
            else
            {
                start = mid+1;
            }
        }

        return -1;
    }

    public Integer findLastOf(Integer key, Integer[] array)
    {
        Integer start = 0;
        Integer end = array.length-1;

        while(start <= end)
        {
            Integer mid = (start + end);

            if (array[mid] == key)
            {
                if (isValidIndex(mid+1, array.length))
                {
                    if (array[mid+1] == key)
                    {
                        start = mid + 1;
                    }
                    else
                    {
                        return mid;
                    }
                }
                else
                {
                    return mid;
                }
            }
            else if (array[mid] < key)
            {
                start = mid+1;
            }
            else
            {
                end = mid-1;
            }

        }

        return -1;
    }

    public Integer find (Integer key, Integer[] array)
    {
        Integer start = 0;
        Integer end = array.length - 1;

        while(start <= end)
        {
            Integer mid = (start + end)/2;

            if (array[mid] == key)
            {
                return mid;
            }
            else if (array[mid] > key)
            {
                end = mid-1;
            }
            else
            {
                start = mid+1;
            }
        }

        return -1;
    }

    public Integer findFirstInDuplicate (Integer[] array)
    {
        Integer start = 0;
        Integer end = array.length-1;

        while(start <= end)
        {
            Integer mid = (start + end)/2;

            if (array[mid] == 1)
            {
                if (isValidIndex(mid-1, array.length))
                {
                    if (array[mid-1] == 0)
                    {
                        return mid;
                    }
                    else
                    {
                        end = mid - 1;
                    }
                }
                else
                {
                    return mid;
                }
            }
            if (array[mid] == 1)
            {
                end = mid - 1;
            }
            else
            {
                start = mid + 1;
            }
        }

        return -1;
    }

    private boolean isValidIndex(Integer index, Integer size)
    {
        return (index >= 0 && index < size) ? true : false;
    }
}