cout ways to climb

public class Main
{
    public static void main(String[] er)
    {
        (new Climber()).find(4);
    }
}

class Climber
{
    private int N;
    public void find(int n)
    {
        this.N = n;

        int count = countFor(n-1) + countFor(n - 2);
        System.out.print(count);
    }

    private int countFor(int n)
    {
        if (n == 1)
        {
            return 1;
        }
        if (n == 2)
        {
            return 2;
        }

        return  countFor(n-1) + countFor(n - 2);
    }
}