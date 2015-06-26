// count number of fib numbers in given range

public class Main
{
    public static void main(String[] er)
    {
        Integer A = 3, B = 18;

        Integer thisNumber = A;
        Integer count = 0;
        while (thisNumber < B)
        {
            thisNumber += (thisNumber-1);
            ++count;
        }

        System.out.print(count-1);
    }
}