import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = {2,3,5,6,4};

        SetFinder sf = new SetFinder(array, 10);
        System.out.println(sf.find());
    }
}

class SetFinder
{
    List<Integer> globalBag;
    Integer globalTarget;

    public SetFinder(Integer[] array, Integer target)
    {
        this.globalBag = Arrays.asList(array);
        this.globalTarget = target;
    }

    public Integer find()
    {
        List<Integer> newBag = new ArrayList<>(globalBag);
        Integer number = newBag.get(0);
        newBag.remove(number);

        return findSet(globalTarget-number, newBag, "") + findSet(globalTarget, newBag, "");
    }

    private Integer findSet(Integer thisTarget, List<Integer> thisBag, String pastSting)
    {

        if (thisTarget < 0)
        {
            return 0;
        }

        System.out.println(pastSting + " analysing for " + thisTarget);

        if (thisTarget == 0)
        {
            System.out.println(pastSting + " returning 1 " + thisTarget);
            return 1;
        }

        if (thisBag.size() == 0)
        {
            return 0;
        }

        List<Integer> newBag = new ArrayList<>(thisBag);
        Integer number = newBag.get(0);
        newBag.remove(number);

        System.out.println(pastSting + " recusing for " + (thisTarget - number) + " " + thisTarget);

        return findSet(thisTarget - number, newBag, " " + pastSting) +  findSet(thisTarget, newBag, " " + pastSting);
    }
}