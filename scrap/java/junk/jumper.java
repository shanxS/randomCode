// jumper

public class Main
{
    public static void main(String[] er)
    {
        int[] ar = {1, 3, 5, 0, 9, 2, 6, 7, 6, 8, 9};
        (new Jumper()).jump(ar);
    }
}

class Jumper
{
    private int[] ar;
    public void jump(int[] ar)
    {
        this.ar = ar;

        int thisIndex = 0;
        int min = ar.length;
        int thisMaxJump = ar[thisIndex];
        for (int i=1; i<=thisMaxJump; ++i)
        {
            Integer thisJump = 1 + jumpFor(thisIndex + i);
            min = Math.min(thisJump, min);
        }

        System.out.print(min);
    }

    private int jumpFor(int thisIndex)
    {
        if (thisIndex >= ar.length)
        {
            return 0;
        }
        if (ar[thisIndex] == 0)
        {
            return ar.length;
        }

        int min = ar.length;
        int thisMaxJump = ar[thisIndex];
        for (int i=1; i<=thisMaxJump; ++i)
        {
            Integer thisJump = 1 + jumpFor(thisIndex + i);
            min = Math.min(thisJump, min);
        }

        return min;
    }
}