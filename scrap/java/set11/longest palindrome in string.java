// longest palindrome in string
// technical, q5, set11

public class Main
{
    public static void main(String[] args)
    {
        String str = "abccbdmalayalam";

        Integer cursor = 1;
        Integer length = 0;
        Integer palinStart = -1;
        Integer palinEnd = str.length();
        while (cursor < str.length())
        {
            Integer tempLenght = 0;
            Integer start = cursor-1;
            Integer end = cursor;
            while(start >= 0 && end < str.length()
                    && str.charAt(start) == str.charAt(end))
            {
                ++tempLenght;
                --start;
                ++end;
            }
            if (tempLenght > length)
            {
                length = tempLenght;
                palinStart = start+1;
                palinEnd = end-1;
            }

            tempLenght = 0;
            start = cursor - 1;
            end = cursor + 1;
            while(start >= 0 && end < str.length()
                    && str.charAt(start) == str.charAt(end))
            {
                ++tempLenght;
                --start;
                ++end;
            }
            if (tempLenght > length)
            {
                length = tempLenght;
                palinStart = start+1;
                palinEnd = end-1;
            }

            ++cursor;
        }

        //System.out.print("size " + length);
        for (Integer i=palinStart; i<=palinEnd; ++i)
        {
            System.out.print(str.charAt(i));
        }


    }
}