// summing embedded int

public class Main
{
    public static void main(String[] er)
    {
        String s = "abc123def-567ghi89jk";
        System.out.print(sumInt(s));
    }

    private static Integer sumInt(String s)
    {
        Integer cursor = 0, sum = 0;

        while (cursor < s.length())
        {
            if (Character.isDigit(s.charAt(cursor)))
            {
                sum += Integer.parseInt(s.charAt(cursor) + "");
            }
            else if (s.charAt(cursor) == '-')
            {
                ++cursor;
                sum -= Integer.parseInt(s.charAt(cursor) + "");
            }

            ++cursor;
        }

        return sum;
    }
}