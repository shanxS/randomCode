// In a sorted array - find two numbers who’s difference is k
// r3, q2, set184
// code question: 49

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = new Integer[]{1,2, 3,4,7,8,9,11};
        Integer targer = 7;
        Integer end = 1;
        Integer start = 0;

        while(end < array.length && start < end)
        {
            if (array[end] - array[start] == 7)
            {
                System.out.println(array[end] + " " + array[start]);
                --end;
                ++start;
            }
            else if  (array[end] - array[start] < 7)
            {
                ++end;
            }
            else
            {
                ++start;
            }
        }
    }
}