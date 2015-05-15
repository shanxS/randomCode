// array manipulation in written q2 set20

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{5, 3, 8, 9, 16};
        Integer iter = 2;
        Integer size = array.length;
        for (Integer i=0; i<iter; ++i)
        {
            Integer temp = array[size-1];
            for (Integer j=size-1; j>0; --j)
            {
                Integer temp2 = array[j-1];
                array[j-1] = temp - array[j-1];
                temp = temp2;
            }
            --size;
        }

        for (Integer j=0; j<size; ++j)
        {
            System.out.print(array[j] + " ");
        }
    }
}