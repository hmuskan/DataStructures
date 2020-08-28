import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Recursion {

    public static int staircasePaths(int n){
        if(n == 0) {
            return 1;
        }
        if(n < 0) {
            return 0;
        }

        int count = 0;
        count += staircasePaths(n - 1);
        count += staircasePaths(n - 2);
        count += staircasePaths(n - 3);

        return count;
    }

    public static void printSequence(int range){ //Print all numbers < range that only have either 2 or 5 or both
        if(range <= 0) {
            return;
        }

        boolean is2or5 = true;
        int n = range;
        while(n > 0 && is2or5) {
            int digit = n % 10;
            if(digit != 2 && digit != 5) {
                is2or5 = false;
            }
            n = n / 10;
        }

        if(is2or5) {
            System.out.println(range);
        }

        printSequence(range - 1);
    }

    /*For the given input - num of pairs of brackets, we need to print
    all the possible combinations of balanced brackets
    Ex: 2
    ()()
    (())*/

    static void printParanthesis(String str, int numOpening, int numClosing, int totalPairs) {
        if(numOpening == totalPairs && numClosing == totalPairs) {
            System.out.println(str);
            return;
        }

        if(numOpening < totalPairs) {
            printParanthesis(str + "(", numOpening + 1, numClosing, totalPairs);
        }
        if(numOpening > numClosing) {
            printParanthesis(str + ")", numOpening, numClosing + 1, totalPairs);
        }


    }

    static ArrayList<String> printParanthesis(int numOpening, int numClosing, int totalPairs) {
        if(numOpening == totalPairs && numClosing == totalPairs) {
            //System.out.println(str);
            ArrayList<String> blank = new ArrayList<>();
            blank.add("");
            return blank;
        }

        ArrayList<String> res = new ArrayList<>();

        if(numOpening < totalPairs) {
            ArrayList<String> rreso = printParanthesis( numOpening + 1, numClosing, totalPairs);
            for(String s: rreso) {
                res.add("(" + s);
            }
        }
        if(numOpening > numClosing) {
            ArrayList<String> rresc = printParanthesis(numOpening, numClosing + 1, totalPairs);
            for(String s: rresc) {
                res.add(")" + s);
            }
        }
        return res;
    }

    private static void allPalindromicPartitons(ArrayList<ArrayList<String>> allPart,
                                            Deque<String> currPart, int start, int n, String input)
    {
        // If 'start' has reached len
        if (start >= n)
        {
            allPart.add(new ArrayList<>(currPart));
            return;
        }

        // Pick all possible ending points for substrings
        for (int i = start; i < n; i++)
        {

            // If substring str[start..i] is palindrome
            if (isPalindrome(input, start, i))
            {

                // Add the substring to result
                currPart.addLast(input.substring(start, i + 1));

                // Recur for remaining remaining substring
                allPalindromicPartitons(allPart, currPart, i + 1, n, input);

                // Remove substring str[start..i] from current
                // partition
                currPart.removeLast();
            }
        }
    }

    // A utility function to check
    // if input is Palindrome
    private static boolean isPalindrome(String input,
                                        int start, int i)
    {
        while (start < i)
        {
            if (input.charAt(start++) != input.charAt(i--))
                return false;
        }
        return true;
    }

    //Return the median of two sorted arrays of same size when they'd be merged
    // O(logn)

    static int medianSortedArrays(int[] a, int[] b, int sa, int ea, int sb, int eb) {

        if(ea - sa == 1) {
            int med = (Math.max(a[sa], b[sb]) + Math.min(a[ea], b[eb])) / 2;
            return med;
        }

        int m1 = median(a, sa, ea);
        int m2 = median(b, sb, eb);

        if(m1 == m2)
            return m1;
        else if(m1 < m2) {
            return medianSortedArrays(a, b, (ea + sa + 1) / 2, ea, sb, (eb + sb + 1) / 2);
        } else {
            return medianSortedArrays(a, b, sa, (ea + sa + 1) / 2, (eb + sb + 1) / 2, eb);
        }

    }

    private static int median(int[] a, int sa, int ea) {
        int len = ea - sa + 1;
        if(len % 2 == 0)
            return (a[sa + len / 2] + a[sa + len / 2 - 1]) / 2;
        else
            return a[sa + len / 2];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ar1[] = { 1, 2, 3, 6 };
        int ar2[] = { 4, 6, 8, 10 };
        int n1 = ar1.length;
        int n2 = ar2.length;
        if (n1 != n2) {
            System.out.println(
                    "Doesn't work for arrays "
                            + "of unequal size");
        }
        else if (n1 == 0) {
            System.out.println("Arrays are empty.");
        }
        else if (n1 == 1) {
            System.out.println((ar1[0] + ar2[0]) / 2);
        }
        else {
            System.out.println(
                    "Median is "
                            + medianSortedArrays(
                            ar1, ar2, 0,
                            ar1.length - 1, 0, ar2.length - 1));
        }
        /*String input = sc.next();
        int n = input.length();

        // To Store all palindromic partitions
        ArrayList<ArrayList<String>> allPart = new ArrayList<>();

        // To store current palindromic partition
        Deque<String> currPart = new LinkedList<String>();

        // Call recursive function to generate
        // all partiions and store in allPart
        allPalindromicPartitons(allPart, currPart, 0, n, input);

        // Print all partitions generated by above call
        for (int i = 0; i < allPart.size(); i++)
        {
            for (int j = 0; j < allPart.get(i).size(); j++)
            {
                System.out.print(allPart.get(i).get(j) + " ");
            }
            System.out.println();
        }*/
    }


}
