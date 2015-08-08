// ugly number

public class Main
{
    public static void main(String[] er)
    {
        (new UglyNumbers()).find(150);
    }
}

class UglyNumbers
{
    public void find(int n)
    {
        int[] ar = new int[n+1];
        ar[0] = 1;

        int i2=0, i3=0, i5=0;
        int cursor = 1;

        while(cursor < n+1)
        {
            int mul2 = 2*ar[i2];
            int mul3 = 3*ar[i3];
            int mul5 = 5*ar[i5];

            int min = Math.min(mul2, Math.min(mul3, mul5));

            if (min == mul2)
            {
                ++i2;
            }
            if (min == mul3)
            {
                ++i3;
            }
            if (min == mul5)
            {
                ++i5;
            }

            ar[cursor] = min;
            ++cursor;
        }

        System.out.print(ar[n-1]);
    }
}