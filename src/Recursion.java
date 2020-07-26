import java.util.ArrayList;

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


}
