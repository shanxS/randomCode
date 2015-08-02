// max product of sub array

public class Main
{
    public static void main(String[] er)
    {
        MaxProduct mp = new MaxProduct();
//        System.out.print(mp.compute(new Integer[]{-1, -3, -10, 0, 60}));
        System.out.print(mp.compute(new Integer[]{0, 0, -20, 0}));

    }
}

class MaxProduct
{
    private Integer max = Integer.MIN_VALUE;
    private Integer prevNeg, prevProd, buf, prevNegIndex;
    private Integer ar[];
    private boolean touched;

    public Integer compute(Integer[] ar)
    {
        touched = false;
        this.ar = ar;
        reset();

        for (Integer i=0; i<ar.length; ++i)
        {
            Integer val = ar[i];
            if (val == 0)
            {
                commit();
                reset();
                continue;
            }

            if (val > 0)
            {
                touched = true;
                if (prevNeg == null)
                {
                    prevProd *= val;
                }
                else
                {
                    buf *= val;
                }
            }
            else
            {
                if (prevNeg != null)
                {
                    prevProd *= prevNeg * buf * val;
                    prevNeg = null;
                    prevNegIndex = null;
                    buf = 1;
                }
                else
                {
                    prevNeg = val;
                    prevNegIndex = i;
                }
            }
        }
        commit();

        return touched ? max : null;
    }

    private void commit()
    {
        if (prevNeg == null)
        {
            max = Math.max(prevProd, max);
        }
        else
        {
            if (prevNegIndex-1 > 0 && ar[prevNegIndex-1] < 0)
            {
                prevProd = Math.max(ar[prevNegIndex-1]*prevNeg*buf, prevNeg);
            }
            else
            {
                prevProd = Math.max(prevProd, buf);
            }

            max = Math.max(prevProd, max);
        }
    }

    private void reset()
    {
        prevNeg = null;
        prevProd = 1;
        buf = 1;
        prevNegIndex = null;
    }
}
