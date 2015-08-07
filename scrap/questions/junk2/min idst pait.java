min idst pait

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {1, 3, 4, 7, 10};
        int sum = 15;
        (new ClosestFinder()).find(ar, sum);
    }
}

class ClosestFinder
{
    public void find(int[] ar, final Integer sum)
    {
        Integer num1 = null, num2 = null;
        Integer minDistance = Integer.MAX_VALUE;

        Integer start = 0, end = ar.length-1;
        while (start <= end)
        {
            Integer thisSum = ar[start] + ar[end];
            if (Math.abs(sum - thisSum) < minDistance)
            {
                minDistance = Math.abs(sum - thisSum);
                num1 = ar[start];
                num2 = ar[end];
            }

            if (thisSum < sum)
            {
                ++start;
            }
            else if(thisSum > sum)
            {
                --end;
            }
            else if (thisSum == sum)
            {
                break;
            }
        }

        System.out.print(num1 + " " + num2 + " " + minDistance);
    }
}