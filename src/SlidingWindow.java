import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {
    static double[] averageOfSubarray(int[] arr, int k) {
        double[] averages = new double[arr.length - k + 1];
        //[1, 2, 3, 4, 5], 3 --> [2, 3, 4]
        double windowSum = 0.0;
        int windowStart = 0;
        for(int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            if(windowEnd >= k - 1) {
                averages[windowStart] = windowSum / k;
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }
        return averages;
    }

    static int minLengthSubarraySum(int[] arr, int sum) {
        int minLength = Integer.MAX_VALUE;
        int windowStart = 0, windowSum = 0;
        for(int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            while(windowSum >= sum) {
                minLength = Math.min(minLength, windowEnd - windowStart + 1);
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }
        return minLength;
    }

    static int maxSubstringWithKDistinctChars(String str, int distinct) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        int maxLength = 0, windowStart = 0, k = 0;
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++) {/*
            if(!frequencyMap.containsKey(str.charAt(windowEnd))) {
                k++;
            }*/
            frequencyMap.put(str.charAt(windowEnd), frequencyMap.getOrDefault(str.charAt(windowEnd), 0) + 1);
            while(frequencyMap.size() > distinct) {
                char leftChar = str.charAt(windowStart);
                frequencyMap.put(leftChar, frequencyMap.get(leftChar) - 1);
                windowStart++;
                if(frequencyMap.get(leftChar) == 0) {
                    frequencyMap.remove(leftChar);
                }

            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }

    static int fruitsInBaskets(char[] trees) {
        int maxFruits = 0, windowStart = 0;
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for(int windowEnd = 0; windowEnd < trees.length; windowEnd++) {
            frequencyMap.put(trees[windowEnd], frequencyMap.getOrDefault(trees[windowEnd], 0) + 1);
            while(frequencyMap.size() > 2) {
                char leftChar = trees[windowStart];
                frequencyMap.put(leftChar, frequencyMap.get(leftChar) - 1);
                if(frequencyMap.get(leftChar) == 0) {
                    frequencyMap.remove(leftChar);
                }
                windowStart++;
            }
            maxFruits = Math.max(maxFruits, windowEnd - windowStart + 1);
        }
        return maxFruits;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(fruitsInBaskets(new char[] {'a', 'b', 'c', 'b', 'b', 'c'}));
        //System.out.println(maxSubstringWithKDistinctChars("araaci", 1));
        //System.out.println(Arrays.toString(averageOfSubarray(arr, 3)));
        //System.out.println(minLengthSubarraySum(arr, 5));
        /*double[] avg = averageOfSubarray(arr, 3);
        for(double a: avg) {
            System.out.print(a + " ");
        }*/

    }
}
