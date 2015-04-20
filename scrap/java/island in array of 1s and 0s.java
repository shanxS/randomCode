// find islands in array of 0 and a
// r2, q3, set5, amazon

public class Main {

    static Integer[][] array = {
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 1, 0, 0, 1, 0, 0, 1},
            {0, 1, 0, 1, 0, 0, 0, 1},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 1, 1, 1, 1, 1}
    };

    final static Integer rowCount = array.length;
    final static Integer colCount = array[0].length;

    public static void main(String[] args)
    {
        Integer count = 0;
        for(Integer r=0; r<array.length; ++r)
        {
            for (Integer c=0; c<array[r].length; ++c)
            {
                if (array[r][c] == 1)
                {
                    checkConnection(r, c);
                    print();
                    ++count;
                }
            }
        }

        System.out.println("count " + count);

    }

    private static void print()
    {
        System.out.println("---------------------------------");
        for(Integer r=0; r<array.length; ++r)
        {
            for (Integer c=0; c<array[r].length; ++c)
            {
                System.out.print(array[r][c] + " ");
            }

            System.out.println();
        }
    }

    private static void checkConnection(Integer r, Integer c)
    {
        if (r<0 || r >= rowCount)
        {
            return;
        }
        if (c<0 || c >= colCount)
        {
            return;
        }
        if (array[r][c] == 0 || array[r][c] == -1)
        {
            return;
        }

        if (array[r][c] == 1)
        {
            array[r][c] = -1;
        }

        if (c+1 < colCount)
        {
            checkConnection(r, c+1);
        }
        if (c+1 < colCount && r+1 < rowCount)
        {
            checkConnection(r+1, c+1);
        }
        if (r+1 < rowCount)
        {
            checkConnection(r+1, c);
        }
        if (c-1 >= 0 && r+1 < rowCount)
        {
            checkConnection(r+1, c-1);
        }
        if (c-1 >=0)
        {
            checkConnection(r, c-1);
        }
        if (r-1 >= 0 && c-1 >= 0)
        {
            checkConnection(r-1, c-1);
        }
        if (r-1 >= 0)
        {
            checkConnection(r-1, c);
        }
        if (r-1 >= 0 && c+1 < colCount)
        {
            checkConnection(r-1, c+1);
        }
    }
}
