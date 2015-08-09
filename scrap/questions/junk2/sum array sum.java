sum array sum

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {1, 10, 5, 2, 7};
        final int k = 9;

        int start = 0, end = 1;
        int minSum = Integer.MAX_VALUE;
        int elemCount = 0;
        int sum = ar[start];
        while (end < ar.length)
        {
            if (sum > k)
            {
                int prevSum = sum;
                while (sum > k && start < end)
                {
                    prevSum = sum;
                    sum -= ar[start];
                    ++start;
                }

                if (minSum > prevSum)
                {
                    minSum = prevSum;
                    elemCount = end-start+1;
                }
            }
            else
            {
                sum += ar[end];
                ++end;
            }
        }

        System.out.print(minSum + " " + elemCount);
    }
}