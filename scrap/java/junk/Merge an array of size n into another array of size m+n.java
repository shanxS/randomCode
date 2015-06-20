// Merge an array of size n into another array of size m+n

public class Main
{
    public static void main(String[] er)
    {
        Integer[] mPlusN = {2,null,7,null,null,10,11,null};
        Integer[] input = {5,8,12,14};

        Merger merger = new Merger();

        Integer[] mergedArray = merger.merge(mPlusN, input);
        for (Integer value : mergedArray)
        {
            System.out.print(value + " ");
        }
    }
}

class Merger
{
    private Integer[] mPlusN, input;

    public Integer[] merge(Integer[] mPlusNParam, Integer[] inputParam)
    {
        this.mPlusN = mPlusNParam;
        this.input = inputParam;

        Integer cursorBig = shiftRight();
        Integer cursorInput = 0;
        Integer writeCursor = 0;
        while (cursorBig < mPlusNParam.length && cursorInput < input.length)
        {
            if (mPlusN[cursorBig] > input[cursorInput])
            {
                mPlusN[writeCursor] = input[cursorInput];
                ++cursorInput;
            }
            else
            {
                mPlusN[writeCursor] = mPlusN[cursorBig];
                ++cursorBig;
            }

            ++writeCursor;
        }

        while (cursorInput < input.length)
        {
            mPlusN[writeCursor] = input[cursorInput];
            ++cursorInput;
            ++writeCursor;
        }

        return mPlusN;
    }

    private Integer shiftRight()
    {
        Integer writeCursor = getNextWritePoint(mPlusN.length-1);
        Integer readCursor = getNextReadPoint(writeCursor-1);

        while (readCursor >= 0 && writeCursor >= 0)
        {
            mPlusN[writeCursor] = mPlusN[readCursor];
            mPlusN[readCursor] = null;

            writeCursor = getNextWritePoint(writeCursor-1);
            readCursor = getNextReadPoint(readCursor - 1);
        }

        return writeCursor+1;
    }

    private Integer getNextReadPoint(Integer readCursorCandidate)
    {
        while (readCursorCandidate >= 0
                && mPlusN[readCursorCandidate] == null)
        {
            --readCursorCandidate;
        }

        return readCursorCandidate;
    }

    private Integer getNextWritePoint(Integer writeCursorCandidate)
    {
        while (writeCursorCandidate >= 0
                && mPlusN[writeCursorCandidate] != null)
        {
            --writeCursorCandidate;
        }

        return writeCursorCandidate;
    }
}