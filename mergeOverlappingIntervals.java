import com.sun.deploy.util.SyncAccess;
import com.sun.org.apache.xml.internal.utils.ListingErrorHandler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by shanxS on 3/12/2015.
 */
public class Merger
{
    private LinkedList<Pair> intervals = new LinkedList<Pair>();

    public void init()
    {
        intervals.add(new Pair(1,3));
        intervals.add(new Pair(8,10));
        intervals.add(new Pair(2,6));
        intervals.add(new Pair(15,18));
    }

    public void print()
    {
        System.out.print("\n");

        ListIterator<Pair> it = intervals.listIterator();
        while(it.hasNext()){
            Pair currentPair = it.next();
            System.out.print(currentPair.first + " " + currentPair.second);
            System.out.print("\n");
        }
    }

    public void sort()
    {
        Collections.sort(intervals);
    }

    public void merge ()
    {
        ListIterator<Pair> it = intervals.listIterator();
        ListIterator<Pair> nextIt = intervals.listIterator();
        nextIt.next();

        int count = 0;

        while(nextIt.hasNext())
        {
            System.out.println("-------------------");
            print();

            Pair currentPair = it.next();
            Pair nextPair = nextIt.next();


            if (currentPair.second >= nextPair.first)
            {
                currentPair.second = nextPair.second;
                nextIt.remove();
            }


        }
    }

}


class Pair implements Comparable<Pair>
{
    Integer first;
    Integer second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Pair anotherPair) {
        if (this.first >= anotherPair.first) return 1;
        else return -1;
    }
}


