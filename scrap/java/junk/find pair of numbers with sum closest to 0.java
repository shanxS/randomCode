// find pair of numbers with sum closest to 0

public class Main
{
    private static Integer[] array = {-80,-10,1,60,70,85};

    public static void main(String[] er)
    {
        Integer startCursor = 0;
        Integer endCursor = array.length-1;
        Integer value1=Integer.MIN_VALUE, value2=Integer.MIN_VALUE;
        Integer distanceFrom0 = Integer.MAX_VALUE;

        while (startCursor < endCursor)
        {
            if (Math.abs(array[startCursor] + array[endCursor]) < distanceFrom0 )
            {
                distanceFrom0 = Math.abs(array[startCursor] + array[endCursor]);
                value1 = array[startCursor];
                value2 = array[endCursor];
            }

            if (array[startCursor] + array[endCursor]  > 0)
            {
                --endCursor;
            }
            else if(array[startCursor] + array[endCursor]  < 0)
            {
                ++startCursor;
            }
            else
            {
                break;
            }
        }

        System.out.print(value1 + " " + value2);
    }
}