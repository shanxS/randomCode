// n bunk problem - Find the first circular tour that visits all petrol pumps
// r3, q1, set21
// more than O(n) less than O(n^2)
// check out other soln with better comlexity

public class Main
{
    public static void main(String[] wer)
    {
        Integer[] petrol = new Integer[]{4,6,7,4};
        Integer[] distance = new Integer[]{6,5,3,5};

        Integer n = distance.length - 1;
        Integer start = 0;
        Integer fuel = 0;
        while(start < distance.length-1)
        {
            fuel = 0;
            for (Integer i=start; i<distance.length && fuel >= 0; ++i)
            {
                fuel += petrol[i];
                fuel -= distance[i];
            }

            for (Integer i=0; i<start && fuel >= 0; ++i)
            {
                fuel += petrol[i];
                fuel -= distance[i];
            }

            if (fuel >= 0)
            {
                break;
            }
            else
            {
                ++start;
            }
        }

        System.out.print(fuel);
    }
}