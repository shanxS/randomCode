// find square root using binary search
// telephonic q3, set28

public class Main
{
    public static void main(String[] er)
    {
        Integer target = 30;
        System.out.print(findSquareRoot(target));
    }

    private static float findSquareRoot(Integer target)
    {
        float start  = 0;
        float end = target;

        while(start <= target)
        {
            float mid = (start+end)/2;
            float pow = mid*mid;
            if (pow < target + 0.1 && pow > target-0.1)
            {
                break;
            }
            else if (pow < target)
            {
                start = mid + 1;
            }
            else if (pow > target)
            {
                end = mid - 1;
            }
        }

        return (start+end)/2;
    }
}