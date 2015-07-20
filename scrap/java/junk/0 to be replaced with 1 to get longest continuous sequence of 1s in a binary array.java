// 0 to be replaced with 1 to get longest continuous sequence of 1s in a binary array

public class Main
{
    public static void main(String []er)
    {
//        Integer[] ar = {1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1};
//        Integer[] ar = {1,1,1,0,0,1,1,1,1,1};
        Integer[] ar = {1,0,1,0,1,1,1};
        Integer maxLen = Integer.MIN_VALUE;
        Integer maxCursor = -1;
        Integer prevStreak = 0;
        Integer nextStreak = 0;

        for (Integer lag=0, lead=0; lag<ar.length; ++lag)
        {
            if (ar[lag] != 0)
            {
                ++prevStreak;
            }
            else
            {
                lead = lag+1;
                nextStreak = 0;
                while (lead<ar.length && ar[lead] != 0)
                {
                    ++nextStreak;
                    ++lead;
                }

                if (nextStreak+prevStreak > maxLen)
                {
                    maxLen = nextStreak+prevStreak;
                    maxCursor = lag;
                }

                lag = lead-1;
                prevStreak = nextStreak;
            }
        }


        System.out.print(maxCursor + " " + maxLen);
    }
}