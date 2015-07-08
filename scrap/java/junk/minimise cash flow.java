public class Main
{
    private static Integer[][] adjMatrix = {
            {0,1000,0,1500,1000},
            {0,0,0,1000,0},
            {3000,0,0,0,0},
            {0,0,1000,0,0},
            {0,0,0,0,0}
    };
    private static final Integer count = adjMatrix.length;
    private static int[] debtArray = new int[count];
    private static Integer minLender;

    public static void main(String[] er)
    {
        for (Integer c=0; c<count; ++c)
        {
            Integer thisLend = 0;
            for (Integer r=0; r<count; ++r)
            {
                thisLend += adjMatrix[r][c];
            }

            debtArray[c] += thisLend;
        }

        for (Integer r=0; r<count; ++r)
        {
            Integer thisBorrow = 0;
            for (Integer c=0; c<count; ++c)
            {
                thisBorrow += adjMatrix[r][c];
            }

            debtArray[r] -= thisBorrow;
        }

        Integer maxLenderIndex = getMaxLender();
        Integer minLenderIndex = getMinLender();
        while (maxLenderIndex != null && minLenderIndex != null)
        {
            if (debtArray[maxLenderIndex] >= Math.abs(debtArray[minLenderIndex]))
            {
                System.out.println(minLenderIndex + " pays " + maxLenderIndex + " " + debtArray[minLenderIndex]);
                debtArray[maxLenderIndex] += debtArray[minLenderIndex];
                debtArray[minLenderIndex] = 0;
            }
            else
            {
                System.out.println(minLenderIndex + " pays " + maxLenderIndex + " " + debtArray[maxLenderIndex]);
                debtArray[minLenderIndex] += debtArray[maxLenderIndex];
                debtArray[maxLenderIndex] = 0;
            }
            maxLenderIndex = getMaxLender();
            minLenderIndex = getMinLender();
        }
    }

    public static Integer getMaxLender()
    {
        Integer index = null;

        for (Integer i=0; i<count; ++i)
        {
            if (debtArray[i] > 0)
            {
                if (index == null)
                {
                    index = i;
                }
                else if (debtArray[index] < debtArray[i])
                {
                    index = i;
                }
            }
        }

        return  index;
    }

    public static Integer getMinLender()
    {
        Integer index = null;

        for (Integer i=0; i<count; ++i)
        {
            if (debtArray[i] < 0)
            {
                if (index == null)
                {
                    index = i;
                }
                else if (debtArray[index] > debtArray[i])
                {
                    index = i;
                }
            }
        }

        return  index;
    }
}