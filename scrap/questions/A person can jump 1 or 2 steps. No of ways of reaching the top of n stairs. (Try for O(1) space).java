// A person can jump 1 or 2 steps. No of ways of reaching the top of n stairs. (Try for O(1) space.)
// r2, q3, set32
// code question 13

public class Main
{
    public static void main(String[] er)
    {
        Counter counter = new Counter();
        //System.out.print(counter.calculateCount(4));
        System.out.print(counter.calculateCount(2));
    }
}

class Counter
{
    private Integer count;

    public Counter()
    {
        this.count = 0;
    }

    public Integer calculateCount(Integer N)
    {
        count = 0;
        calculate(N-1);
        calculate(N-2);

        return count;
    }

    private void calculate(Integer n)
    {
        if (n < 0)
        {
            return;
        }

        if (n == 0)
        {
            ++count;
            return;
        }

        calculate(n - 1);
        calculate(n-2);
    }
}