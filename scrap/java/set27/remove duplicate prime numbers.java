// remove duplicate prime numbers
// r2, q1, set27

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{13, 59, 23, 47, 3, 59, 53, 13, 19, 23, 3, 7 };
        Integer product = 1;
        for (Integer value : array)
        {
            if (product % value == 0)
            {
                System.out.println("duplicate " + value);
            }
            else
            {
                product *= value;
            }
        }
    }
}