// Maximum circular subarray sum

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {-1, 4, 6, 5, -4, -1};
        // {10, -3, -4, 7, 6, 5, -4, -1};//{8, -8, 9, -9, 10, -11, 12};
        Integer currentNegStart = null, negStart = null;
        Integer currentNegEnd = null, negEnd = null;
        Integer maxNeg = 0;
        Integer currentNeg = 0;
        Boolean negContinous = false;

        Integer maxSum = 0;
        for (Integer i=0; i<array.length; ++i)
        {
            maxSum += array[i];

            if (array[i] < 0)
            {
                if (!negContinous)
                {
                    currentNegStart = i;
                    negContinous = true;
                    currentNeg = array[i];
                }
                else
                {
                    currentNegEnd = i;
                    currentNeg += array[i];
                }
            }
            else
            {
                if (currentNeg < maxNeg)
                {
                    maxNeg = currentNeg;
                    negStart = currentNegStart;
                    negEnd = currentNegEnd;
                }
                currentNeg = 0;
                currentNegStart = null;
                currentNegEnd = null;
                negContinous = false;
            }
        }
        if (currentNeg < maxNeg)
        {
            maxNeg = currentNeg;
            negStart = currentNegStart;
            negEnd = currentNegEnd;
        }

        if (negEnd == array.length -1 && negStart != 0 && array[0] < 0)
        {
            Integer cursor = 0;
            while (cursor < array.length && array[cursor] < 0)
            {
                maxNeg += array[cursor];
                ++cursor;
            }
        }
        else if (negStart == 0 && negEnd != array.length-1 && array[array.length-1] < 0)
        {
            Integer cursor = array.length-1;
            while(cursor >= 0)
            {
                maxNeg += array[cursor];
                --cursor;
            }
        }


        maxSum -= maxNeg;

        System.out.print(maxSum + " " + negStart + " " + negEnd);
    }
}