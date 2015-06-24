// knap sack problem

public class Main
{
    final private static Integer[] wt = {10, 20, 30};
    final private static Integer[] val = {60, 100, 120};
    private static Integer maxVal = 0;
    final private static Integer W = 50;

    public static void main(String[] er)
    {
        findMax(0, W, 0);
        System.out.print(maxVal);
    }

    private static void findMax(Integer startIndex, Integer availableW, Integer previousValue)
    {
        if (availableW == 0 || startIndex >= val.length)
        {
            if (maxVal < previousValue)
            {
                maxVal = previousValue;
            }
            return;
        }

        findMax(startIndex+1, availableW, previousValue);

        if (wt[startIndex] <= availableW)
        {
            findMax(startIndex+1, availableW-wt[startIndex], previousValue+val[startIndex]);
        }
        else
        {
            if (maxVal < previousValue)
            {
                maxVal = previousValue;
            }
        }
    }
}
