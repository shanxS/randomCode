// find not repeated number in array

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {7, 3, 5, 4, 5, 3, 4};
        Integer xor = 0;
        for (Integer v : ar)
        {
            xor ^= v;
        }

        System.out.print(xor);
    }
}