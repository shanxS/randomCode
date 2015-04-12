import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        Map<Integer, Integer> rectangles = new TreeMap<Integer, Integer>();

        rectangles.put(10, 1);
        rectangles.put(2, 9);

        /*rectangles.put(4, 6);
        rectangles.put(1, 2);
        rectangles.put(4, 5);
        rectangles.put(10, 12);*/

	    Runner runner = new Runner(rectangles);
        for (Pair pair: runner.run())
        {
            System.out.println(pair.getFirst() + " " + pair.getSecond());
        }
    }
}
