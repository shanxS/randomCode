Samu's Birthday is near so she had started planning a party for all of her friends. Being a kind and caring girl she calls each of her friend and asks for his/her favorite dish.Now each friend has own liking/disliking for different dishes.

A friend can only like or dislike a dish it means if we are having three dishes 1,2,3 then if a friend says that he likes Dishes 1 and 2 then its obvious that he dislikes Dish 3. So for each friend we are given a string of 1 and 0 where 1 shows that this person like this particular dish.

Now we are given that Samu has N friends and total of K dishes available to make her menu. Now Samu doesn't want to make any of her friend unhappy , After all its her birthday.

So she got confused on what dishes to count in menu and calls you for help. You need to find count of minimum dishes to order so that all of her N friends are happy which means everyone has at least one dish to eat in party.

Note : Its for sure that everyone has at least liking for one dish.

Input : Input will contain T test cases and each of the test case has following description :

First line of test case has N denoting the total number of friends and K denoting the total number of dishes available.Both separated by a space (Dishes are numbered from 1 to K) .

Then it is followed by N lines each of length K . Each of the N lines contains a string of 0 and 1 where if jth (1<=j<=K) value in ith line (1<=i<=N) is set 1 then it shows that dish number j is liked by that ith Samu's friend.

Output : You need to tell the minimum number of dishes to be taken in menu so that all friends are happy.

Constraints :

1<=T<=10

1<=N<= 500.

1<=K<=10

Each string will only contain 0 or 1 and it is sure that their is at least one 1 in each string depicting like/dislike of Samu's friend

Sample Input(Plaintext Link)
 1
2 2
10 
01
Sample Output(Plaintext Link)
 2
Explanation
Both dishes are to be taken into account as Friend 1 don't like Dish2 and Friend2 don't like Dish 1.

Time Limit: 1 sec(s) for each input file.
Memory Limit: 256 MB
Source Limit: 1024 KB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main
{
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static Integer N, K;
    private static char[][] choices;

    public static void main(String[] er)
    {
        try
        {
            Integer T = Integer.parseInt(bufferedReader.readLine());

            for (int i=0; i<T; ++i)
            {
                String[] input = bufferedReader.readLine().split(" ");
                N = Integer.parseInt(input[0]);
                K = Integer.parseInt(input[1]);

                choices = new char[N][K];
                for (Integer n=0; n<N; ++n)
                {
                    choices[n] = bufferedReader.readLine().toCharArray();
                }

                System.out.println(computeChoices());
            }
        }
        catch (Exception e)
        {
            System.out.print("exception in buffered reader " + e.getMessage());
        }

    }

    private static Integer computeChoices()
    {
        Set<Integer> people = new HashSet<>();
        for (Integer i=0; i<N; ++i)
        {
            people.add(i);
        }

        Integer count = 0;
        while (people.size() > 0)
        {
            countFor(people);
            ++count;
        }

        return count;
    }

    private static Integer countFor(Set<Integer> people)
    {
        Integer maxStreak = Integer.MIN_VALUE;
        List<Integer> streakPeople = null;

        for (Integer k=0; k<K; ++k)
        {
            Integer thisMax = 0;
            List<Integer> thisPeople = new ArrayList<>();

            for (Integer n=0; n<N; ++n)
            {
                if (people.contains(n) && choices[n][k] == '1')
                {

                    thisMax += 1;
                    thisPeople.add(n);
                }
            }

            if (maxStreak < thisMax)
            {
                maxStreak = thisMax;
                streakPeople = thisPeople;
            }
        }

        for (Integer i : streakPeople)
        {
            people.remove(i);
        }

        return maxStreak;
    }
}