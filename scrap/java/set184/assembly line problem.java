// assembly line problem
// set 184. r4, q2

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] stations = new Integer[][] {{4, 5, 3, 2},
                {2, 10, 1, 4}};
        Integer[][] transfers = new Integer[][] {{0, 7, 4, 5},
                {0, 9, 2, 8}};
        Integer[] entryCost = new Integer[] {10, 12};
        Integer[] exitCost = new Integer[] {18, 7};

        SolveAssembly sa = new SolveAssembly();
        System.out.print(sa.computeCost(stations, transfers, entryCost, exitCost));
    }

}

class SolveAssembly
{
    private Integer[][] stations;
    private Integer[][] transfers;
    private Integer[] entryCost;
    private Integer[] exitCost;
    private Integer[][] dpStationCost;
    final private Integer INVALID = -1;

    public Integer computeCost(Integer[][] stations,
                               Integer[][] transfers,
                               Integer[] entryCost,
                               Integer[] exitCost)
    {
        this.stations = stations;
        this.transfers = transfers;
        this.entryCost = entryCost;
        this.exitCost = exitCost;

        Integer stationCount = stations[0].length;
        this.dpStationCost = new Integer[2][stationCount];
        resetDp();

        Integer minExitCost0 = exitCost[0] + minCost(0, stationCount-1);
        Integer minExitCost1 = exitCost[1] + minCost(1, stationCount-1);

        return Math.min(minExitCost0, minExitCost1);
    }

    private void resetDp()
    {
        for (Integer r = 0; r < dpStationCost.length; ++r)
        {
            for (Integer c=0; c<dpStationCost[0].length; ++c)
            {
                dpStationCost[r][c] = INVALID;
            }
        }
    }

    private Integer minCost(Integer line, Integer station)
    {
        if (dpStationCost[line][station] != INVALID)
        {
            return dpStationCost[line][station];
        }

        if (station == 0)
        {
            dpStationCost[line][station] = stations[line][station] + entryCost[line];
        }
        else
        {

            Integer minCostSameLine = minCost(line, station - 1) + stations[line][station];
            Integer minCostAnotherLine = minCost(getAnotherLine(line), station - 1)
                    + stations[line][station]
                    + transfers[getAnotherLine(line)][station];

            dpStationCost[line][station] = Math.min(minCostAnotherLine, minCostSameLine);
        }

        return dpStationCost[line][station];
    }

    private Integer getAnotherLine(Integer line)
    {
        return (line == 0) ? 1 : 0;
    }
}