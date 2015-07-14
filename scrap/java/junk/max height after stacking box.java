// max height after stacking box

import java.util.Arrays;

public class Main
{
    public static void main(String[] er)
    {
        Box[] boxes = {
                new Box(4, 6, 7),
                new Box(1, 2, 3),
                new Box(4, 5, 6),
                new Box(10, 12, 32)
        };

        HeightFinder hf = new HeightFinder();
        System.out.print(hf.find(boxes));
    }
}

class HeightFinder
{
    private Box[] boxes;
    private Integer[][] dpH;
    private Integer maxH;
    public Integer find(Box[] boxes)
    {
        this.boxes = boxes;
        generatePermutations();
        Arrays.sort(this.boxes);
        for (Box box : this.boxes)
        {
            box.compareL = false;
        }
        dpH = new Integer[2][this.boxes.length];

        maxH = Integer.MIN_VALUE;
        LIS(0);

        return maxH;
    }

    private void LIS(Integer thisIndex)
    {
        if (thisIndex == boxes.length - 1)
        {
            dpH[0][thisIndex] = boxes[thisIndex].b;
            dpH[1][thisIndex] = boxes[thisIndex].h;
            maxH = dpH[1][thisIndex];

            return;
        }
        else
        {
            LIS(thisIndex + 1);
        }

        dpH[0][thisIndex] = boxes[thisIndex].b;
        dpH[1][thisIndex] = boxes[thisIndex].h;
        for (Integer i = thisIndex+1; i<boxes.length; ++i)
        {
            if (boxes[i].b > boxes[thisIndex].b
                    && dpH[0][thisIndex] < (boxes[thisIndex].b + dpH[0][i]))
            {
                dpH[0][thisIndex] = boxes[thisIndex].b + dpH[0][i];
                dpH[1][thisIndex] = boxes[thisIndex].h + dpH[1][i];

                maxH = Math.max(maxH, dpH[1][thisIndex]);
            }
        }
    }

    private void generatePermutations()
    {
        Box[] permutations = new Box[3 * boxes.length];

        for (Integer i=0, j=0; i<boxes.length; ++i)
        {
            Box box = boxes[i];

            permutations[j] = new Box(box.l, box.b, box.h);
            permutations[j+1] = new Box(box.h, box.l, box.b);
            permutations[j+2] = new Box(box.b, box.h, box.l);

            j += 3;
        }

        boxes = permutations;
    }
}

class Box implements Comparable
{
    public final Integer l, b, h;
    public boolean compareL;
    public Box(Integer l, Integer b, Integer h)
    {
        this.b = b;
        this.h = h;
        this.l = l;

        compareL = true;
    }

    @Override
    public int compareTo(Object o)
    {
        Box otherBox = (Box) o;

        if (compareL)
        {
            if (otherBox.l > l)
            {
                return -1;
            }
            else if (otherBox.l < l)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        else
        {
            if (otherBox.b > b)
            {
                return -1;
            }
            else if (otherBox.b < b)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }
}