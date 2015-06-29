// Find Index of 0 to be replaced with 1 to get longest continuous sequence of 1s in a binary array

public class Main
{
    public static void main(String[] er)
    {
        //Integer[] arr = {0,1, 1, 1, 1};
        Integer[] arr  = {1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1};
        Integer previousRunCount = 0, thisRunCount = 0;
        Integer thisRunStartIndex = -1;
        Integer maxRun = 0;
        Integer replacementIndex = -1;
        Integer zeroRunCount = 0;

        Boolean runInProgress = false;
        Integer cursor = 0;
        while (cursor < arr.length)
        {
            if (runInProgress)
            {
                if (arr[cursor] == 1)
                {
                    ++thisRunCount;
                }
                else
                {
                    if (previousRunCount != 0)
                    {
                        if (previousRunCount + thisRunCount > maxRun)
                        {
                            maxRun = previousRunCount + thisRunCount;
                            replacementIndex = thisRunStartIndex-1;
                        }
                    }

                    previousRunCount = thisRunCount;
                    thisRunCount = 0;
                    zeroRunCount = 1;
                    runInProgress = false;
                }
            }
            else
            {
                if (arr[cursor] == 1)
                {
                    thisRunStartIndex = cursor;
                    ++thisRunCount;
                    runInProgress = true;
                }
                else
                {
                    previousRunCount = 0;
                    ++zeroRunCount;
                }
            }

            ++cursor;
        }
        if (previousRunCount != 0 || zeroRunCount == 1)
        {
            if (thisRunCount == 0)
            {
                thisRunCount = previousRunCount;
                previousRunCount = 0;
            }

            if (previousRunCount > 0)
            {
                if (previousRunCount + thisRunCount > maxRun)
                {
                    maxRun = previousRunCount + thisRunCount;
                    replacementIndex = thisRunStartIndex - 1;
                }
            }
            else if (zeroRunCount == 1)
            {
                if (zeroRunCount + thisRunCount > maxRun)
                {
                    maxRun = zeroRunCount + thisRunCount;
                }
            }
        }

        System.out.print(replacementIndex + " " + maxRun);
    }
}