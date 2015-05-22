// n stairs person can climb 1 or 2 steps - calculate number of ways to climb
// r3, last quetion, set26

public class Main
{
    static Integer ways = 0;
    public static void main(String[] er)
    {
        Integer n = 8;
        find(n-1);
        find(n-2);

        System.out.print(ways);
    }

    private static void find(Integer n)
    {
        if (n == 0)
        {
            ++ways;
            return;
        }

        if (n < 0)
        {
            return;
        }

        find(n-1);
        find(n-2);
    }
}