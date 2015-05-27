// inflexion point for rotated increasing sequence

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1,2,3,4,5,6,7,8,9};
        Integer[] array2 = new Integer[]{9,8,7,6,5,4,3,2,1};
        InflexionFinder inflexionFinder = new InflexionFinder();
        for (Integer i=0; i<array.length; ++i)
        {
            Integer cache = array[array.length-1];
            for (Integer cursor=0; cursor<array.length; ++cursor)
            {
                Integer temp = array[cursor];
                array[cursor] = cache;
                cache = temp;
            }
            for (Integer value : array)
            {
                System.out.print(value + " ");
            }
            System.out.println(" inflexion at " + array[inflexionFinder.findIndex(array)]);

            cache = array2[array2.length-1];
            for (Integer cursor=0; cursor<array2.length; ++cursor)
            {
                Integer temp = array2[cursor];
                array2[cursor] = cache;
                cache = temp;
            }
//            for (Integer value : array2)
//            {
//                System.out.print(value + " ");
//            }
//            System.out.println(" inflexion at " + array2[inflexionFinder.findIndex(array2)]);

            System.out.println();

        }
    }
}

class InflexionFinder
{
    private Integer[] array;
    private Boolean ruleIncreasing;

    public Integer findIndex(Integer[] array)
    {
        if (array.length < 3)
        {
            if (array.length == 2)
            {
                if (array[0] > array[1])
                {
                    return 0;
                }
                else
                {
                    return 1;
                }
            }
            else
            {
                return 0;
            }
        }

        this.array = array;

        return doBinarySearch();
    }

    private Integer doBinarySearch()
    {
        Integer start = 0;
        Integer end = array.length-1;

        while(start < end)
        {
            Integer mid = start + ((end-start)/2);
            if (isInfexionPoint(mid) != null)
            {
                return isInfexionPoint(mid);
            }
            else if ((array[mid] < array[start]))
            {
                end = mid - 1;
            }
            else
            {
                start = mid + 1;

            }
        }

        return start;
    }

    private Integer isInfexionPoint(Integer index)
    {
        if (index <= 0 || index >= array.length-1)
        {
            return null;
        }

        if ((array[index] > array[index-1]) == (array[index] > array[index+1]))
        {
            if ((array[index] > array[index-1]))
            {
                return index;
            }
            else
            {
                return index-1;
            }
        }

        return null;
    }

}