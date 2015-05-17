// better n bunk problem - Find the first circular tour that visits all petrol pumps
// r3, q1, set21
// more than O(n) less than "n bunk problem - Find the first circular tour that visits all petrol pumps.java"

public class Main
{
    public static void main(String[] wer)
    {
        Integer[] petrol = new Integer[]{4,6,7,4};
        Integer[] distance = new Integer[]{6,5,3,5};

        Integer n = distance.length;
        Integer start = 0;
        Integer end = 1;
        Integer fuel = petrol[start] - distance[start];
        Boolean notPossible = false;
        while((end != start || fuel < 0) && !notPossible)
        {
            System.out.println(fuel + " " + start + " " + end);
            while (fuel < 0 && start != end)
            {
                fuel -= petrol[start] - distance[start];
                start = (start+1)%n;

                if (start == 0)
                {
                    notPossible = true;
                }
            }

            fuel += petrol[end] - distance[end];
            end = (end+1)%n;
        }

        System.out.print(fuel + " " + start);
    }
}