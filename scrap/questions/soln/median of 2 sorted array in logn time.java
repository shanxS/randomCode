// median of 2 sorted array of different sizes in logn time

import java.util.Arrays;

public class Main
{
//    private static Integer[] ar1 = {4,8,9,10,13};//{3,5,7,8};
//    private static Integer[] ar2 = {2,3,5,11};

    private static Integer[] ar1 = {4,8,9,10};
    private static Integer[] ar2 = {5,6,7,12};

    public static void main(String[] er)
    {
        MedianFinder mf = new MedianFinder();
        System.out.print(mf.find(ar1, ar2));
    }
}

class MedianFinder
{
    public Integer find(Integer[] ar1, Integer[] ar2)
    {
        Integer[] smaller, bigger;
        if (ar1.length <= ar2.length)
        {
            smaller = ar1;
            bigger = ar2;
        }
        else
        {
            smaller = ar2;
            bigger = ar1;
        }

        return median(smaller, bigger);
    }

    private Integer median(Integer[] smaller, Integer[] bigger)
    {
        if (smaller.length == 1)
        {
            if (bigger.length == 1)
            {
                return average(smaller[0], bigger[0]);
            }
            else
            {
                if (isEven(bigger))
                {
                    Integer bigLength = bigger.length;
                    Integer midBig = bigger[bigLength/2];
                    Integer midLess = bigger[(bigLength/2) - 1];

                    Integer number = smaller[0];
                    if (number < midLess)
                    {
                        return midLess;
                    }
                    else if (number > midBig)
                    {
                        return midBig;
                    }
                    else
                    {
                        return number;
                    }
                }
                else
                {
                    Integer bigLenght = bigger.length;
                    Integer mid = bigger[bigLenght/2];
                    Integer midLess = bigger[(bigLenght/2) - 1];
                    Integer midMore = bigger[(bigLenght/2) + 1];

                    Integer number = smaller[0];
                    if ((number > midLess && number < mid)
                            || (number > mid && number < midMore))
                    {
                        return average(mid, number);
                    }
                    else if (number < midLess)
                    {
                        return average(midLess, mid);
                    }
                    else if (number > midMore)
                    {
                        return average(midMore, mid);
                    }
                }
            }
        }
        else if (smaller.length == 2)
        {
            if (bigger.length == 2)
            {
                return median4(smaller, bigger);
            }
            else
            {
                if (isEven(bigger))
                {
                    Integer bigLength = bigger.length;
                    Integer midMax = bigger[bigLength/2];
                    Integer midMin = bigger[(bigLength/2) - 1];
                    Integer midHigh = bigger[(bigLength/2) + 1];
                    Integer midLow = bigger[(bigLength/2) - 2];

                    Integer[] array = new Integer[4];
                    array[0] = Math.max(midLow, smaller[0]);
                    array[1] = Math.min(midHigh, smaller[1]);
                    array[2] = midMin;
                    array[3] = midMax;

                    Arrays.sort(array);

                    return median4(Arrays.copyOfRange(array, 0, 3), Arrays.copyOfRange(array, 2, 4));
                }
                else
                {
                    Integer bigLength = bigger.length;
                    Integer mid = bigger[bigLength/2];
                    Integer midhigh = bigger[(bigLength/2) + 1];
                    Integer midlow = bigger[(bigLength/2) - 1];

                    Integer[] array = new Integer[3];
                    array[0] = Math.max(midlow, smaller[0]);
                    array[1] = Math.min(midhigh, smaller[1]);
                    array[2] = mid;

                    Arrays.sort(array);
                    return array[1];
                }
            }
        }
        else
        {
            Integer medianSmall = median(smaller);
            Integer medianBig = median(bigger);

            while (medianSmall != medianBig
                    && smaller.length > 2 && bigger.length > 2)
            {
                ///////////////////////////////////////////////////
                // THIS IS THE MEAT
                ///////////////////////////////////////////////////
                if (medianSmall > medianBig)
                {
                    Integer newLenghtSmaller = (smaller.length/2);
                    if (smaller.length%2 != 0)
                    {
                        ++newLenghtSmaller;
                    }
                    smaller = Arrays.copyOfRange(smaller, 0, newLenghtSmaller);

                    Integer newLengthBigger = (bigger.length/2);
                    if (bigger.length%2 != 0)
                    {
                        ++newLengthBigger;
                    }
                    Integer start = bigger.length - newLengthBigger;
                    bigger = Arrays.copyOfRange(bigger, start, bigger.length);
                }
                else
                {
                    Integer newLengthBigger = (bigger.length/2);
                    if (bigger.length%2 != 0)
                    {
                        ++newLengthBigger;
                    }
                    bigger = Arrays.copyOfRange(bigger, 0, newLengthBigger);

                    Integer newLengthSmaller = (smaller.length/2);
                    if (smaller.length%2 != 0)
                    {
                        ++newLengthSmaller;
                    }
                    Integer start = smaller.length - newLengthSmaller;
                    smaller = Arrays.copyOfRange(smaller, start, smaller.length);
                }

                medianSmall = median(smaller);
                medianBig = median(bigger);
            }

            if (medianSmall == medianBig)
            {
                return medianSmall;
            }
            else
            {
                return median(smaller, bigger);
            }
        }

        return null;
    }

    private Integer median(Integer[] ar)
    {
        if (isEven(ar))
        {
            Integer length = ar.length;
            return average(ar[length/2], ar[(length/2)-1]);
        }
        else
        {
            return ar[ar.length/2];
        }
    }

    private Integer median4(Integer[] ar1, Integer[] ar2)
    {
        return (Math.min(ar1[1], ar2[1])
                + Math.max(ar1[0], ar2[0])
                )/2;
    }

    private boolean isEven(Integer[] array)
    {
        return array.length%2 == 0;
    }

    private Integer average(Integer num1, Integer num2)
    {
        return (num1 + num2)/2;
    }
}