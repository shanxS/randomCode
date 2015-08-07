// common number in  arrays

public class Main
{
    public static void main(String[] er)
    {
        int[][] ar = {{1, 5, 5},
        {3, 4, 5, 5, 10},
        {5, 5, 10, 20}};

        (new ConnonFinder()).find(ar);
    }
}

class ConnonFinder
{
    public void find(int[][] ar)
    {
        int[] cursors = new int[ar.length];

        Integer max = Integer.MIN_VALUE;
        for (Integer r=0; r<ar.length; ++r)
        {
            max = Math.max(max, ar[r][cursors[r]]);
        }

        boolean stop = false;
        while (!stop)
        {
            boolean stable = true;
            for (Integer r=0; r<ar.length; ++r)
            {
                while (cursors[r] < ar[0].length && ar[r][cursors[r]] < max)
                {
                    ++cursors[r];
                }

                if (cursors[r] >= ar[r].length)
                {
                    stable = false;
                    stop = true;
                    break;
                }
                else if (ar[r][cursors[r]] != max)
                {
                    stable = false;
                }
                else
                {
                    ++cursors[r];
                }
            }

            if (stable)
            {
                System.out.print(max + " ");
            }

            max = Integer.MIN_VALUE;
            for (Integer r=0; r<ar.length; ++r)
            {
                if (cursors[r] >= ar[0].length)
                {
                    stop = true;
                    break;
                }

                max = Math.max(max, ar[r][cursors[r]]);
            }
        }
    }
}