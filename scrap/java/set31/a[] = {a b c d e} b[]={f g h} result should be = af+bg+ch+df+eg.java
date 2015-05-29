// a[] = {a b c d e} b[]={f g h} result should be = af+bg+ch+df+eg
// tech,q2, set31

public class Main
{
    public static void main(String[] er)
    {
        Integer[] a = new Integer[]{2,3,4,5,6};
        Integer[] b = new Integer[]{10,100};

        Integer bCursor = 0;
        Integer aCursor = 0;
        Integer sum = a[aCursor] * b[bCursor];
        ++aCursor; ++bCursor;
        while(aCursor < a.length)
        {
            sum += a[aCursor] * b[bCursor % (b.length)];

            ++aCursor;
            ++bCursor;
        }
        System.out.print(sum);
        
    }
}