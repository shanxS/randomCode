// array from sum array

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {14, 9, 10, 11, 12, 7};
        (new ArrayRet()).print(4, ar);
    }
}

class ArrayRet
{
    public void print(Integer n, Integer[] ar)
    {
        int a0 = (ar[0] + ar[1] - ar[n-1])/2;

        System.out.print(a0 + " ");
        for (Integer i=0; i<n-1; ++i)
        {
            System.out.print(ar[i] - a0 + " ");
        }
    }
}