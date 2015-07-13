// find path with min cost

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] ar = {
                {1,2,3},
                {4,5,2},
                {1,5,3}
        };

        MinCostFinder mcf = new MinCostFinder();
        System.out.print(mcf.find(ar, 2, 2));
    }
}

class MinCostFinder
{
    private Integer[][] ar;
    private Location target;
    public Integer find(Integer[][] matrix, Integer R, Integer C)
    {
        ar = matrix;
        target = new Location(R, C);
        return contemplate (new Location(0,0));
    }

    private Integer contemplate(Location thisLocationlocation)
    {
        if (!isValidLocation(thisLocationlocation))
        {
            return Integer.MAX_VALUE;
        }
        else if (thisLocationlocation.equals(target))
        {
            return ar[thisLocationlocation.r][thisLocationlocation.c];
        }
        else
        {
            Integer r=thisLocationlocation.r;
            Integer c=thisLocationlocation.c;

            return ar[thisLocationlocation.r][thisLocationlocation.c] + Math.min(contemplate(new Location(r+1, c)) ,
                    Math.min(contemplate(new Location(r, c+1)), contemplate(new Location(r+1, c+1))));
        }
    }

    private boolean isValidLocation(Location location)
    {
        Integer r = location.r;
        Integer c = location.c;
        return r>=0 && r<ar.length
                && c>=0 && c<ar[0].length;
    }
}

class Location
{
    public final Integer r,c;

    public Location(Integer r, Integer c)
    {
        this.r = r;
        this.c = c;
    }

    @Override
    public boolean equals(Object o)
    {
        Location otherLocation = (Location)o;
        return otherLocation.r == r && otherLocation.c == c;
    }
}
