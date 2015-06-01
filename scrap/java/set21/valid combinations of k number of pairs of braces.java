// valid combinations of k number of pairs of braces
// set 21, r4, q5
// code question

public class Main
{
    private static Integer pairCount;
    public static void main(String[] er)
    {
        pairCount = 1;

        System.out.print(count(pairCount, pairCount));
    }

    private static Integer count(Integer openers, Integer closers)
    {
        if (closers <= 0 || openers < 0)
        {
            return 0;
        }

        if (openers > 0)
        {
            if (openers < pairCount)
            {
                Integer openCount = count(openers-1, closers);
                Integer closeCount = 0;
                if (pairCount-openers <= pairCount-closers)
                {
                    closeCount = count(openers, closers-1) + 1;
                }

                return openCount+closeCount;
            }
            else
            {
                return count(openers-1, closers);
            }
        }
        else if (openers == 0 && closers > 0)
        {
            return closers;
        }

        return 0;
    }
}