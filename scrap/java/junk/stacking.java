// stacking

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] er)
    {
        List<Box> boxes = new ArrayList<>();
        boxes.add(new Box(4, 6, 7));
        boxes.add(new Box(1, 2, 3));
        boxes.add(new Box(4, 5, 6));
        boxes.add(new Box(10, 12, 32));

        (new BoxStacking()).find(boxes);
    }
}

class BoxStacking
{
    private List<Box> boxes;
    public void find(List<Box> boxes)
    {
        permute(boxes);
        Collections.sort(this.boxes);
        System.out.print(LIS());

    }

    private int LIS()
    {

        for (int i=0; i<boxes.size(); ++i)
        {
            System.out.print(boxes.get(i).h + " ");
        }


        int[] lis = new int[boxes.size()];
        int[] ht = new int[boxes.size()];
        for (int i=0; i<lis.length; ++i)
        {
            lis[i] = 1;
            ht[i] = boxes.get(i).h;
        }

        int max = 0;
        for (int i=1; i<lis.length; ++i)
        {
            for (int j=0; j<i; ++j)
            {
                if (boxes.get(j).h < boxes.get(i).h
                        && lis[i] < 1+lis[j])
                {
                    lis[i] = 1+lis[j];
                    ht[i] = boxes.get(i).h + ht[j];
                    max = Math.max(max, ht[i]);
                }
            }
        }

        return max;
    }

    private int getReplacementIndex(List<Integer> list, int h)
    {
        int cursor = list.size()-1;

        while (cursor >= 0 && list.get(cursor) > h)
        {
            --cursor;
        }

        return cursor+1;
    }

    private void permute(List<Box> givenBoxes)
    {
        boxes = new ArrayList<>();
        for (Box box : givenBoxes)
        {
            boxes.add(new Box(box.l, box.b, box.h));
            boxes.add(new Box(box.b, box.h, box.l));
            boxes.add(new Box(box.h, box.l, box.b));
        }
    }
}

class Box implements Comparable
{
    final int l, b, h;

    public Box(int l, int b, int h)
    {
        this.l = l;
        this.b = b;
        this.h = h;
    }


    @Override
    public int compareTo(Object o)
    {
        Box otherBox = (Box) o;
        Integer otherArea = otherBox.l * otherBox.b;
        Integer thisArea = l * b;

        if (otherArea > thisArea)
        {
            return -1;
        }
        else if (otherArea < thisArea)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}