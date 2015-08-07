2 array and sum

public class Main
{
    public static void main(String[] er)
    {
        int ar1[] = {1, 4, 5, 7};
        int ar2[] = {10, 20, 30, 40};
        int sum = 32;
        (new ClosestFinder()).find(ar1, ar2, sum);
    }
}

class ClosestFinder
{
    private Integer num1 = null, num2 = null;
    private Integer minDistance = Integer.MAX_VALUE, sum;

    public void find(int[] ar1, int[] ar2, final Integer sum)
    {
        int s1 = 0, e1 = ar1.length-1;
        int s2 = 0, e2 = ar2.length-1;
        this.sum = sum;

        while(s1 <= e1 || s2 <= e2)
        {
            if ((s1 <= e1 && s2 <= e2))
            {
                Integer min = Math.min(ar1[s1], ar2[s2]);
                Integer max = Math.max(ar1[e1], ar2[e2]);

                Integer thisSum = min + max;
                commit(thisSum, min, max);

                if (thisSum < sum)
                {
                    if (ar1[s1] == min)
                    {
                        ++s1;
                    }
                    else
                    {
                        ++s2;
                    }
                }
                else if(thisSum > sum)
                {
                    if (ar1[e1] == max)
                    {
                        --e1;
                    }
                    else
                    {
                        --e2;
                    }
                }
                else if (thisSum == sum)
                {
                    break;
                }
            }
            else if (s1 <= e1)
            {
                Integer thisSum = ar1[s1] + ar1[e1];
                commit(thisSum, ar1[s1], ar1[e1]);

                if (thisSum < sum)
                {
                    ++s1;
                }
                else if(thisSum > sum)
                {
                    --e1;
                }
                else if (thisSum == sum)
                {
                    break;
                }
            }
            else if (s2 <= e2)
            {
                Integer thisSum = ar2[s2] + ar2[e2];
                commit(thisSum, ar2[s2], ar2[e2]);

                if (thisSum < sum)
                {
                    ++s2;
                }
                else if(thisSum > sum)
                {
                    --e2;
                }
                else if (thisSum == sum)
                {
                    break;
                }
            }
        }

        System.out.print(num1 + " " + num2 + " " + minDistance);
    }

    private void commit(Integer thisSum, Integer min, Integer max)
    {
        if (Math.abs(sum - thisSum) < minDistance)
        {
            minDistance = Math.abs(sum - thisSum);
            num1 = min;
            num2 = max;
        }
    }


}