// coin changer
// r1, q2, set11

public class Main
{
    public static void main(String[] args)
    {
        Integer[] set = new Integer[]{2,5,3,6};

        CoinChanger cc = new CoinChanger(set, 10);
        System.out.print(cc.change());

    }
}

class CoinChanger
{
    private Integer[] set;
    private Integer target;

    public CoinChanger(Integer[] set, Integer target)
    {
        this.set = set;
        this.target = target;
    }

    public Integer change()
    {
        Integer size = set.length;

        return change(target, size-1) + change(target-set[size-1], size);
    }

    private Integer change(Integer thisTarget, Integer thisSize)
    {
        if (thisTarget == 0)
        {
            return 1;
        }
        if (thisTarget < 0)
        {
            return 0;
        }
        if (thisSize <= 0 && target != 0)
        {
            return 0;
        }

        return change(thisTarget, thisSize-1) + change(thisTarget-set[thisSize-1], thisSize);
    }
}