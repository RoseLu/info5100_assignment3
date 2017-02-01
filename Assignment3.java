import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Rose on 1/27/17.
 * Assignment for your lecture 3. Please finish all the questions under
 * 'Assignment'. Please try to think the extra credit questions. The deadline
 * of this assignment is 02/04/2017 23:59 PST. Please feel free to contact me
 * for any questions. Please write your comments about this assignment in the
 * end.
 */


public class Assignment3 {
    /**
     *  Given an array, reverse the elements within this array and print the result
     *  eg, given{1,2,3,4}, print{4,3,2,1}
     */
    public void reverseArray(int[] nums) {
        //write your code here
    	int lo = 0, hi = nums.length-1;
    	while(lo < hi) {
    		int temp = nums[lo];
    		nums[lo++] = nums[hi];
    		nums[hi--] = temp;
    	}
    }

    /**
     *  Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
     *  Assume the integer do not contain any leading zero, except the number 0 itself.
     *  The digits are stored such that the most significant digit is at the head of the array.
     *  eg, given {1,2,9}, reutrn{1,3,0}.
     */
    public int[] plusOne(int[] digits) {
        //write your code here
    	int len = digits.length, i = len - 1;
    	while(i >= 0 && digits[i] == 9) digits[i--] = 0;
    	if(i < 0) {
    		int[] ans = new int[len+1];
    		ans[0] = 1;
    		return ans;
    	}
    	digits[i] += 1;
    	return digits;
    }

    /**
     *  Write a program that takes an integer as input and returns all the primes between 1 and that integer(inclusive).
     *  eg, input is 18, you should return{2,3,5,7,11,13,17}
     */
    public int[] generatePrimes(int n) {
        //write your code here
    	if(n <= 1) return null;
    	int[] ans = new int[n];
    	int cnt = 0;
    	for(int i = 2; i <= n; i++) {
    		int j = 2;
    		while(j <= Math.pow(i, 0.5) && i % j != 0) j++;
    		if(j > Math.pow(i, 0.5)) ans[cnt++] = i; 
    	}
    	int[] anst = new int[cnt];
    	for(int i = 0; i < cnt; i++) anst[i] = ans[i];
    	return anst;
    }
    //A better sol: Sieve of Eeatosthese

    /**
     *  Assume you have a method isSubstring which checks if one word is a substring of another.
     *  Given two strings, s1 and s2, write a program to check if s2 is a rotation of s1, using only one call
     *  to isSubstring
     *  eg, "pineapple" is a rotation of "neapplepi"
     */
    public boolean isRotation(String s1, String s2) {  	
    	return s1.length() == s2.length() && (s1+s1).indexOf(s2) != -1;
    }

    /**
     *  Given two strings, write a method to decide if one is a permutation of the other
     *  hint: the comparison is case sensitive and whitespace is significant
     */
    public boolean isPermutation(String s1, String s2) {
        //write your code here
    	int len1 = s1.length(), len2 = s2.length();
    	if(len1 != len2) return false;
    	char[] chs1 = s1.toCharArray();
    	char[] chs2 = s2.toCharArray();
    	Arrays.sort(chs1);
    	Arrays.sort(chs2);
    	return Arrays.equals(chs1, chs2);
    }

    /**
     *  Write a program to implement encoding and decoding string. The rule is simple: encode successive
     *  repeated characters by the repetition count and the character. For example, the input of encoding()
     *  is "aaaabcccaa", you should return "4a1b3c2a". The decoding of "3e4f2e" returns "eeeffffee". Assume
     *  the string to be encoded consists of letters of the alphabet, with no digits, and the string to be
     *  decoded is a valid encoding.
     */
    public static String encoding(String s) {
        //write your code here
    	String ans = "";
    	char[] chs = s.toCharArray();
    	int len = chs.length, i = 0;
    	while(i < len) {
    		int cur = i;
    		while(i < len && chs[i] == chs[cur]) i++;
    		ans += (i - cur) + "" + chs[cur];		
    	}
    	return ans;
    }
    public static String decoding(String s) {
        //write your code here
    	String ans = "";
    	char[] chs = s.toCharArray();
    	int len = chs.length, repeat = 0;
    	for(int i = 0; i < len; i++) {
    		if(i % 2 == 0) repeat = chs[i] - '0';
    		else for(int j = 0; j < repeat; j++) ans += chs[i];  		
    	}
    	return ans;
    }

    //Extra Credit
    /**
     *Given an m x n 2D matrix representing an image. Rotate the image by 90 degrees (clockwise).
     * For exmaple, given 1 2 3  , return 7 4 1
     *                    4 5,6           8 5 2
     *                    7,8,9           9 6 3
     *tip: image could be a square or a rectangle.
     */
    public void rotate(int[][] matrix) {
        //write your code here
    	int n = matrix.length;
    	if(n == 0) return;
    	int m = matrix[0].length;
    	if(m == 0) return;
    	int[][] ans = new int[m][n];
    	for(int i = 0; i < m; i++) {
    		for(int j = 0; j < n; j++) {
    			ans[i][j] = matrix[j][i];
    		}
    	}	
    	for(int i = 0; i < m; i++) {
    		for(int j = 0; j <= n/2; j++) {
    			System.out.print(ans[i][n-1-j]);
    		}
    		System.out.println();
    	}
    }

     /**
     *Given a string containing just the characters '(', ')', return the count of valid parentheses. If the
     * intput is not valid, return -1. A valid parentheses is "()". For example, given "(())", return 2;
     * given "(()))", return -1.
     */
     public int countValidParentheses(String s) {
         //write your code here
    	 int len = s.length(), cnt = 0;
    	 if(len < 2) return -1;
    	 char[] chs = s.toCharArray();	 
    	 Stack stk = new Stack();
    	 for(int i = 0; i < len; i++) {
    		 if(chs[i] == '(') stk.push('(');
    		 else {
    			 cnt++;
    			 if(stk.size() > 0) stk.pop();
    			 else return -1;
    		 }	 
    	 }
    	 return stk.size() > 0 ? -1 : cnt;
     }

    /**
     * Write anything you think about this assignment here. Easy? Difficult? Too many questions? Less fun?
     * You could write any comments here
     */
     
}