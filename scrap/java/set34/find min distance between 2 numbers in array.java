// find min distance between 2 numbers in array
// online, q2, set34

public class Main
{
    private static Integer minDistance = Integer.MAX_VALUE;

    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1,0,0,2,0,1};
        Integer x = 1;
        //Integer y = 1;
        Integer y = 2;
        Integer lastSeenX = null;
        Integer lastSeenY = null;
        for (Integer cursor=0; cursor<array.length; ++cursor)
        {
            if (array[cursor] == x)
            {
                lastSeenX = cursor;
                upadateMin(lastSeenX, lastSeenY);
            }

            if (array[cursor] == y)
            {
                lastSeenY = cursor;
                upadateMin(lastSeenX, lastSeenY);
            }
        }

        System.out.print(minDistance);
    }

    private static void upadateMin(Integer lastSeenX, Integer lastSeenY)
    {
        if (lastSeenX == null || lastSeenY == null)
        {
            return;
        }

        if (Math.abs(lastSeenX - lastSeenY) < minDistance)
        {
            minDistance = Math.abs(lastSeenX - lastSeenY);
        }
    }
}