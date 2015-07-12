public class Main
{
    private static Integer[] ar1 = {4,6,9};//{3,5,7,8};
    private static Integer[] ar2 = {2,3,5};

    public static void main(String[] er)
    {
        System.out.print(findMean());
    }

    private static Integer findMean()
    {
        Integer start1 = 0, size1 = ar1.length;
        Integer start2 = 0, size2 = ar2.length;

        Integer median1 = median(ar1, start1, size1);
        Integer median2 = median(ar2, start2, size2);

        while (size1 > 2 && median1 != median2)
        {
            if (median1 > median2)
            {
                size1 = (size1/2);

                size2 = (size2/2);
                start2 += (size2);
            }
            else
            {
                size2 = (size2/2) + 1;

                size1 = (size1/2);
                start1 += (size1);
            }

            median1 = median(ar1, start1, size1);
            median2 = median(ar2, start2, size2);
        }

        if (median1 == median2)
        {
            return median1;
        }
        else
        {
            return (Math.max(ar1[start1], ar2[size2])
                    + Math.min(ar1[start1+1], ar2[size2+1])
                    )/2;
        }

    }

    private static Integer median(Integer[] ar, Integer start, Integer size)
    {
        if (size%2 == 0)
        {
            Integer mid = start + (size/2);
            return (ar[mid] + ar[mid-1])/2;
        }
        else
        {
            return ar[start + (size/2)];
        }
    }
}