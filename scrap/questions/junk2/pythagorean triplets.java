// pythagorean triplets

import java.util.Arrays;

public class Main
{
    private static Integer[] ar = {3, 1, 4, 6, 5};
    public static void main(String[] er)
    {

        for (int i=0; i<ar.length; ++i)
        {
            ar[i] = (int)Math.pow(ar[i], 2);
        }

        Arrays.sort(ar);
        for (int i=ar.length-1; i>=2; --i)
        {
            for (int j=i-1; j>=1; --j)
            {
                int diff = ar[i] - ar[j];
                int index = search(diff, 0, j-1);
                if (index != -1)
                {
                    System.out.print(ar[j] + " " + ar[i] + " " + ar[index]);
                }
            }
        }
    }

    private static int search(int diff, int start, int end)
    {
        while(start <= end)
        {
            int mid = start + (end-start)/2;
            if (ar[mid] == diff)
            {
                return mid;
            }
            if (ar[mid] > diff)
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
}