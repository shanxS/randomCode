// in array replace each element by greatest element to its right
// r5, q2, set19

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = new Integer[]{1,10,5,2,7,8,3};

        Integer max = -1;
        for (Integer i=array.length-1; i>=0; --i)
        {
            Integer thisELement = array[i];
            array[i] = max;
            if(max < thisELement)
            {
                max = thisELement;
            }
        }

        for (Integer value : array)
        {
            System.out.print(value + " ");
        }
    }
}