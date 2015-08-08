// palindrome

public class Main
{
    public static void main(String[] er)
    {
        String s = "forgeeksskeegfor";
        (new Palindrome()).find(s.toCharArray());
    }
}

class Palindrome
{
    public void find(char[] ar)
    {
        int max = 0;

        for (int i=1; i<ar.length; ++i)
        {
            int even = 0;
            int j=i-1, k=i;
            while (j>=0 && k<ar.length && ar[j]==ar[k])
            {
                --j;
                ++k;
                even += 2;
            }

            int odd = 1;
            j=i-1; k=i+1;
            while (j>=0 && k<ar.length && ar[j]==ar[k])
            {
                --j;
                ++k;
                odd += 2;
            }

            max = Math.max(max, Math.max(odd, even));
        }

        System.out.print(max);
    }
}