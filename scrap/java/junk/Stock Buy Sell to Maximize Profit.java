// Stock Buy Sell to Maximize Profit

public class Main
{
    private static Integer[] array = {310, 40, 535, 695};//{100, 180, 260, 310, 40, 535, 695};

    private static Integer localMin = null;
    private static Integer localMax = null;
    private static Integer cursor = 0;
    private static Integer profit = 0;

    public static void main(String[] er)
    {
        while (cursor < array.length-1)
        {
            if (array[cursor] > array[cursor+1])
            {
                if (localMax != null && localMin != null)
                {
                    sell();
                }

                localMin = array[cursor + 1];
            }
            else if (array[cursor] < array[cursor+1])
            {
                if (localMin == null)
                {
                    localMin = array[cursor];
                }

                localMax = array[cursor+1];
            }

            ++cursor;
        }
        if (localMax != null && localMin != null)
        {
            sell();
        }

        System.out.print(profit);
    }

    private static void sell()
    {
        profit += localMax - localMin;
        localMin = null;
        localMax = null;
    }
}