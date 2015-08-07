// test internal node has 1 child

public class Main
{
    public static void main(String[] re)
    {
        int[] pre = {20, 10, 11, 13, 12};
        (new ChildTester()).test(pre);
    }
}

class ChildTester
{
    public void test(int[] pre)
    {
        boolean status = true;
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;

        if (pre[0] > pre[1])
        {
            max = pre[0];
        }
        else
        {
            min  = pre[0];
        }

        for (Integer i=1; i<pre.length; ++i)
        {
            if (pre[i] > max || pre[i] < min)
            {
                status = false;
                break;
            }

            if (pre[i] < pre[i-1])
            {
                max = pre[i-1];
            }
            else if (pre[i] > pre[i-1])
            {
                min = pre[i-1];
            }

            System.out.println("for " + pre[i] + " " + min + " " + max);
        }


        System.out.print(status);
    }
}