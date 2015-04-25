// policy bazaar - find 3d largest and 3rd smallest - not working
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {

    static BufferedReader br;
    static int T;
    static Map<Integer, Integer> valFreq = null;
    static List<Integer> list;
    static List<Integer> sortedList;
    private static Integer uniqueCount;
    private static MinHeap minHeap;
    private static MaxHeap maxHeap;

    public static void main(String[] args) {
        try {

            br = new BufferedReader(new InputStreamReader(System.in));
            T = Integer.parseInt(br.readLine());

            for (int i = 0; i < T; ++i) {
                String str = br.readLine();
                if (str.length() < 3) {
                    System.out.print("Not possible!");
                } else {
                    parse(str);


                    Collections.sort(list);
                    sortedList = new ArrayList<>(list);
                    uniqueCount = getUniqueCountFromIndex(3);
                    if (uniqueCount == getUniqueCountFromIndex(1)) {
                        System.out.print(str);
                    } else {
                        List<Integer> bag = list.subList(uniqueCount, list.size());
                        maxHeap = new MaxHeap();
                        permute(bag, true);
                        if (uniqueCount != 0) {
                            Integer prefix = convetToInteger(list.subList(0, uniqueCount));
                            System.out.println(prefix);
                        }
                        System.out.print(maxHeap.getMax());
                    }
                    System.out.print(" ");

                    //Collections.sort(list);
                    Collections.reverse(sortedList);
                    list = sortedList;

                    uniqueCount = getUniqueCountFromIndex(3);
                    if (uniqueCount == getUniqueCountFromIndex(1)) {
                        System.out.print(str);
                    } else
                    {
                        List<Integer> bag = list.subList(uniqueCount, list.size());
                        minHeap = new MinHeap();
                        permute(bag, false);
                        if (uniqueCount != 0) {
                            Integer prefix = convetToInteger(list.subList(0, uniqueCount));
                            System.out.println(prefix);
                        }
                        System.out.print(minHeap.getMin());
                    }

                }

                System.out.println();
            }
        } catch (Exception e)
        {}

    }

    private static void parse(String s) {

        list = new ArrayList<>();

        for (Character c : s.toCharArray())
        {
            Integer val  = Integer.parseInt("" + c);
            list.add(val);

            /*
            Integer val  = Integer.parseInt("" + c);
            Integer freq = valFreq.get(val);
            if (freq == null)
            {
                freq = 0;
            }
            ++freq;
            valFreq.put(val, freq);
            System.out.println("put " + val + " " + freq);*/
        }

    }

    private static List<List<Integer>> permute(List<Integer> bag, boolean ismax)
    {
        List<List<Integer>> ll = new ArrayList<>();
        ll.addAll(permuteAt(new ArrayList<Integer>(), bag, ismax));

        return ll;
    }

    private static Integer getUniqueCountFromIndex(Integer limit)
    {
        Integer i = null;
        for (i=list.size()-1; i>0 && limit>=1; --i)
        {
            if (list.get(i) != list.get(i-1))
            {
                --limit;
            }
        }

        return i;
    }

    private static Integer convetToInteger(List<Integer> l) {
        Integer result = 0;

        for (int i=l.size()-1, j=0; i>=0; --i, ++j)
        {
            result += l.get(j) * (int)(Math.pow(10, i));
        }

        return result;
    }

    private static List<List<Integer>> permuteAt(List<Integer>origList, List<Integer> bag, boolean isMax) {

        List<List<Integer>> partialLL = new ArrayList<>();
        if (bag.size() == 1)
        {
            List<Integer> newL = new ArrayList<>(origList);
            newL.add(bag.get(0));

            partialLL.add(newL);
            return partialLL;
        }

        for (int i=0; i<bag.size(); ++i)
        {
            List<Integer> newL = new ArrayList<>(origList);
            newL.add(bag.get(i));

            List<Integer> newBag = new ArrayList<>(bag);
            newBag.remove(bag.get(i));
            for (List<Integer> l : permuteAt(newL, newBag, isMax)) {
                partialLL.add(l);
            }

            if (bag.size() == list.size() - uniqueCount)
            {
                for (List<Integer> subList : partialLL)
                {
                    if (isMax)
                    {
                        if (maxHeap.getSize() >= 3) {
                            return partialLL;
                        }
                        maxHeap.insert(convetToInteger(subList));
                    }
                    else {
                        if (minHeap.getSize() >= 3) {
                            return partialLL;
                        }
                        minHeap.insert(convetToInteger(subList));
                    }

                }
            }
        }

        return partialLL;
    }

    static class MinHeap
    {
        private List<Integer> list;

        public MinHeap()
        {
            list = new ArrayList<>();
        }

        public Integer getSize()
        {
            return list.size();
        }

        public Integer getMin()
        {
            return list.size() >0 ? list.get(0) : null;
        }

        public void insert(Integer value)
        {
            if (list.contains(value))
            {
                return;
            }

            list.add(value);
            bubbleUp();
        }

        private void bubbleUp()
        {
            Integer cursorIndex = list.size() - 1;
            Integer parentIndex = getParentIndex(cursorIndex);

            while (parentIndex >= 0)
            {
                if (list.get(parentIndex) > list.get(cursorIndex))
                {
                    Collections.swap(list, parentIndex, cursorIndex);
                    cursorIndex = parentIndex;
                }
                else
                {
                    break;
                }

                parentIndex = getParentIndex(cursorIndex);
            }

        }

        private Integer getLeftChildIndex(Integer parentIndex)
        {
            return (parentIndex*2) + 1;
        }

        private Integer getRightChildIndex(Integer parentIndex)
        {
            return (parentIndex*2) + 2;
        }

        private Integer getParentIndex(Integer childIndex)
        {
            return (childIndex-1)/2;
        }

        public void print()
        {
            for (Integer i : list)
            {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    static class MaxHeap
    {
        private List<Integer> list;

        public MaxHeap()
        {
            list = new ArrayList<>();
        }

        public Integer getSize()
        {
            return list.size();
        }

        public Integer getMax()
        {
            return list.size() >0 ? list.get(0) : null;
        }

        public void insert(Integer value)
        {
            if (list.contains(value))
            {
                return;
            }

            list.add(value);
            bubbleUp();
        }

        private void bubbleUp()
        {
            Integer cursorIndex = list.size() - 1;
            Integer parentIndex = getParentIndex(cursorIndex);

            while (parentIndex >= 0)
            {
                if (list.get(parentIndex) < list.get(cursorIndex))
                {
                    Collections.swap(list, parentIndex, cursorIndex);
                    cursorIndex = parentIndex;
                }
                else
                {
                    break;
                }

                parentIndex = getParentIndex(cursorIndex);
            }

        }

        private Integer getLeftChildIndex(Integer parentIndex)
        {
            return (parentIndex*2) + 1;
        }

        private Integer getRightChildIndex(Integer parentIndex)
        {
            return (parentIndex*2) + 2;
        }

        private Integer getParentIndex(Integer childIndex)
        {
            return (childIndex-1)/2;
        }

        public void print()
        {
            for (Integer i : list)
            {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }


}


