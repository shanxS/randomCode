// Building with n floor - person can take 1 step or 2 step to climb - Find the number of ways to reach nth floor
// r2, q2, set23

public class Main
{
    public static void main(String[] wer)
    {
        ClimbCounter cc = new ClimbCounter(4);
        System.out.print(cc.count());
    }
}

class ClimbCounter
{
    final private Integer N;

    public ClimbCounter(Integer N)
    {
        this.N = N;
    }

    public Integer count()
    {
        Integer max2Count = N/2;
        Integer min1Count = null;

        if (N%2 == 0)
        {
            min1Count = 0;
        }
        else
        {
            min1Count = 1;
        }

        Integer count = 1;
        for (Integer i=1; i<=max2Count; ++i)
        {
            Integer this1Count = N - (2*i) + min1Count;
            Integer this2Count = i;

            count += permute(this1Count, this2Count);
        }

        return count;
    }

    private Integer permute(Integer this1Count, Integer this2Count)
    {
        Integer totalDigita = this1Count + this2Count;
        Integer result = getFactorial(totalDigita) / (getFactorial(this1Count) * getFactorial(this2Count));

        return result;
    }

    private Integer getFactorial (Integer value)
    {
        Integer result = 1;
        while (value > 0)
        {
            result *= value;
            --value;
        }

        return result;
    }
}