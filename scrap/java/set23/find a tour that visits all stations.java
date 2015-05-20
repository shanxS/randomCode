// find a tour that visits all stations
// r4, q10, set23

public class Main
{
    public static void main(String[] args)
    {
        Integer[] petrol = new Integer[]{4,6,7,4};
        Integer[] distance = new Integer[] {6,5,3,5};

        Integer stationCount = distance.length;
        Integer start = 0;
        Integer end = 1;
        Integer fuel = petrol[start] - distance[start];
        Boolean found  = true;

        while(start != end || fuel < 0)
        {
            while (fuel < 0)
            {
                fuel -= petrol[start] - distance[start];
                start = (start+1) % stationCount;

                if (start == 0)
                {
                    System.out.print("cant find");
                    return;
                }
            }

            fuel += petrol[end] - distance[end];
            end = (end+1) % stationCount;
        }
    }
}