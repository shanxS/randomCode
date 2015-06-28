// Check if a given array contains duplicate elements at k distance from each other

public class Main
{
    public static void main(String[] er)
    {
        Integer[] array = {1, 2, 3, 4, 1, 2, 3, 4};//{1, 2, 3, 4, 4};
        final Integer K = 3;
        Integer cursor = 0;
        Integer fwdCursor = cursor + array.length - K - 1;
        Integer revCursor = cursor + K + 1;

        while (cursor < array.length)
        {
            if (array[cursor] == array[fwdCursor] || array[cursor] == array[revCursor])
            {
                System.out.println(array[cursor]);
            }

            ++cursor;
            revCursor = (revCursor+1) % array.length;
            fwdCursor = (fwdCursor+1) % array.length;
        }
    }
}