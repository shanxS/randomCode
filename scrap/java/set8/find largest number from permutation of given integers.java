// find largest number from permutation of given integers

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(998);
        list.add(97);
        list.add(905);
        list.add(91);

        Collections.sort(list, new StringedNumberComparator());

        list.stream().forEach(x -> System.out.print(x + " "));
    }
}

class StringedNumberComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        String s1 = String.valueOf(o1);
        String s2 = String.valueOf(o2);

        if (Integer.parseInt(s1+s2) > Integer.parseInt(s2+s1))
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }
}