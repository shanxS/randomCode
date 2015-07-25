// sum of 3 numbers is equal to target

import java.util.Arrays;

public class Main
{
    private static Integer[] ar = {-25, -10, -7, -3, 2, 3, 4, 8, 10};

    public static void main(String[] er)
    {
        Arrays.sort(ar);
        print(0, ar.length-1);
    }

    private static void print(Integer start, Integer end)
    {
        if (start >= end)
        {
            return;
        }

        Integer target = (ar[start] + ar[end])*(-1);
        Integer midIndex = findIndex(start, end, target);
        if (midIndex != null)
        {
            System.out.println(ar[start] + " " + ar[end] + " " + ar[midIndex]);
        }

        ++start;

        print(start+1, end);
        print(start, end-1);
    }

    public static Integer findIndex(Integer start, Integer end, Integer target)
    {
        while (start <= end)
        {
            Integer mid = start + (end-start)/2;

            if (ar[mid] == target)
            {
                return mid;
            }
            else if (ar[mid] < target)
            {
                start = mid+1;
            }
            else if (ar[mid] > target)
            {
                end = mid-1;
            }
        }

        return null;
    }
}