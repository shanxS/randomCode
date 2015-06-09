// Number of hops required to reach the end of an array - where the maximum hop is the value at index
// set 179, r1, q1
// code question: 65

public class Main
{
    private static Integer[] array = {1,2,3,4,5};
    private static Integer INVALID = -1;

    public static void main(String[] er)
    {
        //System.out.print(maxHopFor(0, array.length-1));
        System.out.print(maxHopFor(4, array.length));
    }

    private static Integer maxHopFor(Integer startIndex, Integer targetIndex)
    {
        if (startIndex == targetIndex)
        {
            return 0;
        }

        Integer maxHopMade = Integer.MAX_VALUE;


        for (Integer hop=1; hop<=array[startIndex]; ++hop)
        {
            Integer endIndex = startIndex + hop;

            if (endIndex == targetIndex)
            {
                maxHopMade = 1;
                break;
            }
            else if (endIndex < targetIndex)
            {
                Integer hopCount = maxHopFor(endIndex, targetIndex);
                if (hopCount != INVALID )
                {
                    if (maxHopMade == INVALID)
                    {
                        maxHopMade = hopCount + 1;
                    }
                    else if ((1 + hopCount < maxHopMade))
                    {
                        maxHopMade = hopCount + 1;
                    }
                }
            }
        }

        return maxHopMade;
    }
}