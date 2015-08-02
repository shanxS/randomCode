// circular tour

public class Main
{
    public static void main(String[] er)
    {
        (new Tourist()).find(new Integer[]{4,6,7,4}, new Integer[]{6,5,3,8});
    }
}

class Tourist
{
    public void find(Integer[] fuel, Integer[] distance)
    {
        int start = 0;
        int end = 1;
        int currentFuel = fuel[start] - distance[start];

        while(start < fuel.length && (start != end || currentFuel < 0))
        {
            if (currentFuel < 0)
            {
                while (currentFuel < 0 && start != end && start < fuel.length)
                {
                    currentFuel += distance[start];
                    currentFuel -= fuel[start];
                    ++start;
                }
            }

            currentFuel -= distance[end];
            currentFuel += fuel[end];
            System.out.println("end " + end + " start " + start + " fuel cost " + currentFuel);
            end = (end+1)%distance.length;
        }

        System.out.print("end " + end + " start " + start);
    }
}