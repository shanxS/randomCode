// palindrom in stream of char

public class Main
{
    public static void main(String[] er)
    {
        String s = "aabaacaabaa";
        StreamingPalindrome sp = new StreamingPalindrome();
        for (Character c : s.toCharArray())
        {
            sp.evaluate(c);
        }
    }
}

class StreamingPalindrome
{
    private String fwd, bkwd, mid;

    public StreamingPalindrome()
    {
        fwd = "";
        bkwd = "";
        mid = "";
    }

    public void evaluate(Character c)
    {
        if (fwd.length() == 0)
        {
            fwd += c;
        }
        else if (fwd.length() == 1 && bkwd.length() == 0)
        {
            bkwd = c + bkwd;
        }
        else if ((fwd.length() == bkwd.length()) && (mid.length() == 0))
        {
            mid = "" + bkwd.charAt(bkwd.length()-1);
            bkwd = bkwd.substring(0, bkwd.length()-1);
            bkwd = c + bkwd;
        }
        else if ((fwd.length() == bkwd.length()) && (mid.length() == 1))
        {
            fwd += mid;
            mid = "";
            bkwd = c + bkwd;
        }

        System.out.println(fwd + " " + bkwd);
    }
}