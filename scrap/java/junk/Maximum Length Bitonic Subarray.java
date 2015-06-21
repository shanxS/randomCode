// Maximum Length Bitonic Subarray

public class Main
{
    enum Status {increasing, decreasing, constant};

    public static void main(String[] er)
    {
        Integer[] array = {20, 4, 1, 2, 3, 4, 2, 10};//{12, 4, 78, 90, 45, 23};
        Integer startIndex = null;
        Integer endIndex = null;

        Status currentStatus = null;


        Integer cursor = 0;
        while (cursor < array.length-1)
        {
            if (startIndex == null)
            {
                if (array[cursor] <= array[cursor+1])
                {
                    startIndex = cursor;
                    currentStatus = Status.increasing;

                    while (currentStatus == Status.increasing && cursor < array.length-1)
                    {
                        if (array[cursor] <= array[cursor+1])
                        {
                            currentStatus = Status.increasing;
                        }
                        else if (array[cursor] > array[cursor+1])
                        {
                            currentStatus = Status.decreasing;
                        }
                        ++cursor;
                    }

                    while (currentStatus == Status.decreasing && cursor < array.length-1)
                    {
                        endIndex = cursor;
                        if (array[cursor] <= array[cursor+1])
                        {
                            currentStatus = Status.increasing;
                        }
                        else if (array[cursor] > array[cursor+1])
                        {
                            currentStatus = Status.decreasing;
                        }

                        ++cursor;
                    }
                }

                if (endIndex != null)
                {
                    System.out.print("found at " + startIndex + " " + (endIndex+1));
                }

                startIndex = null;
                endIndex = null;
                ++cursor;
            }
            else
            {
                ++cursor;
            }
        }
    }
}