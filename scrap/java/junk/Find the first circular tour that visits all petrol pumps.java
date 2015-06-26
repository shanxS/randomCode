// Find the first circular tour that visits all petrol pumps

public class Main
{
    public static void main(String[] er)
    {
        Integer[] petrol = {4,6,7,4};
        Integer[] distance = {6,5,3,5};

        Integer startIndex = 0;
        Integer endIndex = 1;
        Integer petrolLeft = petrol[startIndex] - distance[startIndex];

        Boolean canTravel = true;

        while (endIndex != startIndex && startIndex < petrol.length)
        {
            if (petrolLeft < 0)
            {
                while (startIndex < petrol.length && petrolLeft < 0)
                {
                    petrolLeft -= petrol[startIndex] - distance[startIndex];
                    ++startIndex;
                }

                if (startIndex == petrol.length && petrolLeft < 0)
                {
                    canTravel = false;
                    break;
                }
            }

            petrolLeft += petrol[endIndex] - distance[endIndex];
            endIndex = (endIndex+1)%petrol.length;
        }

        if (canTravel)
        {
            System.out.print("can travel");
        }
        else
        {
            System.out.print("cant travel");
        }

    }
}