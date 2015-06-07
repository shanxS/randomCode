// m Coloring Problem
// code question: 57

public class Main
{
    public static void main(String[] er)
    {
        Integer[][] amap = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0},
        };

        ColorAssigner ca = new ColorAssigner();
        for (Integer i : ca.assign(amap, 3))
        {
            System.out.print(i + " ");
        }
    }
}

class ColorAssigner
{
    final private Integer INVALID = -1;
    private Integer[] assignedColor;
    private Integer[] colors;
    private Integer[][] map;

    public Integer[] assign(Integer[][] amap, Integer colorCount)
    {
        this.map = amap;
        setColors(colorCount);
        setAssignedColors();

        final Integer thisNode = 0;
        for (Integer i=0; i<colors.length; ++i)
        {
            assignedColor[thisNode] = colors[i];
            if (contemplateColor(thisNode + 1))
            {
                return assignedColor;
            }
        }

        return null;
    }

    private boolean contemplateColor(Integer thisNode)
    {
        if (thisNode >= map.length)
        {
            return true;
        }

        for (Integer colorCursor=0; colorCursor<colors.length; ++colorCursor)
        {
            if (canAssign(colors[colorCursor], thisNode))
            {
                assignedColor[thisNode] = colors[colorCursor];
                if (contemplateColor(thisNode+1))
                {
                    return true;
                }
                assignedColor[thisNode] = INVALID;
            }
        }

        return false;
    }

    private boolean canAssign(Integer colorIndex, Integer nodeIndex)
    {
        for (Integer c = 0; c < map.length; ++c)
        {
            if (map[nodeIndex][c] == 1 && assignedColor[c] == colors[colorIndex])
            {
                return false;
            }
        }

        return true;
    }

    private void setAssignedColors()
    {
        assignedColor = new Integer[map.length];
        for (Integer i=0; i<assignedColor.length; ++i)
        {
            assignedColor[i] = INVALID;
        }
    }

    private void setColors(Integer colorCount)
    {
        colors = new Integer[colorCount];
        for (Integer i=0; i<colorCount; ++i)
        {
            colors[i] = i;
        }
    }
}