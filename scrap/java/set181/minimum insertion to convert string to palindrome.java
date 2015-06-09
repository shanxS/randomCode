// minimum insertion to convert string to palindrome
// set 181, r1, q2.2

public class Main
{
    public static void main(String[] er)
    {
//        String text = "abcd";
//        String text = "abcda";
        String text = "abcde";
        PalindromeMaker pm = new PalindromeMaker();
        System.out.print(pm.make(text));
    }
}

class PalindromeMaker
{
    private String text;

    public Integer make(String text)
    {
        this.text = text;

        Integer forwardCursor = 0;
        Integer reverseCursor = text.length()-1;
        Integer thisCost = 0;

        while (forwardCursor <= reverseCursor)
        {
            if (text.charAt(forwardCursor) != text.charAt(reverseCursor))
            {
                Integer costMoveForward = contemplate(forwardCursor + 1, reverseCursor);
                Integer costMoveBackward = contemplate(forwardCursor, reverseCursor-1);

                if (costMoveForward < costMoveBackward)
                {
                    thisCost += costMoveForward;
                    ++forwardCursor;
                }
                else
                {
                    thisCost += costMoveBackward;
                    --reverseCursor;
                }

                if ((reverseCursor - forwardCursor + 1) % 2 == 0)
                {
                    ++thisCost;
                }
            }

            ++forwardCursor;
            --reverseCursor;
        }

        return thisCost;
    }

    private Integer contemplate(Integer forwardCursor, Integer reverseCursor)
    {
        if (forwardCursor == reverseCursor)
        {
            return 0;
        }
        else  if (forwardCursor+1 == reverseCursor)
        {
            if (text.charAt(forwardCursor) == text.charAt(reverseCursor))
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }

        Integer thisCost = 0;

        while(forwardCursor <= reverseCursor)
        {
            if (text.charAt(forwardCursor) != text.charAt(reverseCursor))
            {
                Integer costMoveForward = contemplate(forwardCursor + 1, reverseCursor);
                Integer costMoveBackward = contemplate(forwardCursor, reverseCursor - 1);

                if (costMoveForward < costMoveBackward)
                {
                    thisCost += costMoveForward;
                    ++forwardCursor;
                } else
                {
                    thisCost += costMoveBackward;
                    --reverseCursor;
                }

                ++thisCost;
            }

            ++forwardCursor;
            --reverseCursor;
        }

        return thisCost;
    }
}