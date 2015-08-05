// amx pair sum

public class Main
{
    public static void main(String[] er)
    {
        Integer[] ar = {12, 34, 10, 6, 40};
        Integer max = Integer.MIN_VALUE, samx=null;

        for (Integer i=0;i<ar.length; ++i)
        {
            if (max < ar[i])
            {
                samx = max;
                max = ar[i];
            }
        }

        System.out.print((samx+max));
    }
}