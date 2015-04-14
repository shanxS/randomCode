import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args)
    {
        Set<Pair> pairs = new LinkedHashSet<>();
        pairs.add(new Pair(1, 4));
        pairs.add(new Pair(8, 10));
        pairs.add(new Pair(11, 6));
        pairs.add(new Pair(15, 18));

        Runner runner = new Runner(pairs);
        System.out.println(runner.run());
        runner.print();
    }

}

class Pair
{
    Integer first, second;

    public Pair(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }

    public Pair() {
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }
}