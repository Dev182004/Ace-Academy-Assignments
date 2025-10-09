/* 
@filename - Main2.java
@description -  Implemented a menu-driven program in Java that performs multiple string and number operations based on user choice.
@author - Divyansh Tak
*/


import java.util.*;

public class Main2 {

    //  Nth number in the Fibonacci sequence
    public static int getNthFibonacciNumber(int n){
         
        if(n==1) return 0;
        if(n==2) return 1;
        
         int prev =0;
         int curr=1;
          

         for(int i=3;i<=n;i++)
         {
            int next = prev+curr;
            prev = curr; 
            curr=next;
         }

         return curr;
    }
   
    // snake_case string to camelCase format
    public static String getCamelCase(String str){

        int idx =0;
        String s="";
        while(idx<str.length())
        {
            if(str.charAt(idx)=='_'){
                idx++;
                s+=(char)(str.charAt(idx)-32);
            }
            else{
                s+=str.charAt(idx);
            }
            idx++;


        }

        return s;
        
    }

    //Count Consonants in a String 
    public static int getConsonants(String str){
          
        int idx =0;
        int count=0;
        str = str.toLowerCase();

        while(idx<str.length())
        {
            char ch = str.charAt(idx);

           if (ch >= 'a' && ch <= 'z') {
              if (!(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')) {
                count++;
               }
            }
           idx++;
        }

        return count;
    }

    //Binary to Decimal Conversion
    public static int getDecimal(String str){
          
          int idx =str.length()-1;
          int pow=0;
          int ans =0;
          while(idx>=0)
          {
             int digit =str.charAt(idx) - '0';
             ans = ans + ((int)(Math.pow(2,pow))*digit);
             pow++;
             idx--;
          }

          return ans;
    }

    // Expanding Characters in a String
    public static String getExpandedString(String str){
             String s ="";

             int idx=1;
             while(idx<str.length()) {
                 
                    int val = str.charAt(idx) -'0';
                    int num=1;
                    while(num<=val){
                        s+=str.charAt(idx-1);
                        num++;
                    }
                    idx+=2;
                  }

             return s;
    }

    //Compressed Characters in a String
    public static String getCompressedString(String str){

        String s="";
        int idx =0;
        while(idx<str.length())
        {
            int count=0;
            char ch=str.charAt(idx);
            s+=ch;
            while( idx<str.length() && str.charAt(idx)==ch )
            {
                count++;
                idx++;
            }
            s+=count;
           
        }
        return s;
    }

    //Check given no. is PRIME or NOT
    public static void primeNumberCheck(int n)
    {
        if(n < 2) {
            System.out.println("The given number is NOT prime");
              return;
          }

        for(int i=2;i<=Math.sqrt(n);i++)
        {
            if(n%i==0)
            {
                System.out.println("The given number is NOT prime");
                return;
            }
        }

        System.out.println("The given number is PRIME");
    }


    //Longest Substring Without Repeating Characters 


 public static int lengthOfLongestSubstring(String s){
        int maxLen=0;
        for(int i=0;i<s.length();i++){
            String sub="";
            for(int j=i;j<s.length();j++){
                if(sub.indexOf(s.charAt(j))!=-1) break;
                sub += s.charAt(j);
            }
            if(sub.length()>maxLen) maxLen=sub.length();
        }
        return maxLen;
    }


    //Number to Words Converter(0-999)
    public static String numberToWords(int num){
        if(num==0) return "zero";
        String[] below20={"","one","two","three","four","five","six","seven","eight","nine",
                          "ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen",
                          "seventeen","eighteen","nineteen"};
        String[] tens={"","", "twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};

        String words="";
        if(num>=100){
            words += below20[num/100] + " hundred ";
            num %=100;
        }
        if(num>=20){
            words += tens[num/10] + " ";
            num %=10;
        }
        if(num>0){
            words += below20[num] + " ";
        }
        return words.trim();
    }



    //Count Unique Palindromes 
    public static int countUniquePalindromes(String str) {
        int count = 0;
        int n = str.length();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String sub = str.substring(i, j);
                if (isPalindrome(sub) && !isAlreadyCounted(str, sub, i)) {
                    count++;
                }
            }
        }

        return count;
    }

    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static boolean isAlreadyCounted(String str, String sub, int start) {
        for (int i = 0; i < start; i++) {
            if (i + sub.length() <= str.length() && str.substring(i, i + sub.length()).equals(sub)) {
                return true;
            }
        }
        return false;
    }





    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose operation:");
            System.out.println("1. Count Unique Palindromes");
            System.out.println("2. Fibonacci Sequence Nth Number");
            System.out.println("3. Snake Case to Camel Case Conversion");
            System.out.println("4. Count Consonants in a String");
            System.out.println("5. Binary to Decimal Conversion");
            System.out.println("6. Characters in a String Expansion");
            System.out.println("7. Character Frequency in a String");
            System.out.println("8. Prime Number Checker");
            System.out.println("9. Number to Words Converter");
            System.out.println("10. Longest Substring Without Repeating Characters");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch(choice) {
                case 1:
                    System.out.print("Enter string: ");
                    String str = sc.nextLine();
                    System.out.println("Unique palindromes count: " + countUniquePalindromes(str));
                    break;
                case 2:
                    System.out.print("Enter num: ");
                    int n = sc.nextInt();
                    System.out.println("Nth Fibonacci number: " + getNthFibonacciNumber(n));
                    break;
                case 3:
                    System.out.print("Enter snake_case string: ");
                    String str1 = sc.nextLine();
                    System.out.println("CamelCase: " + getCamelCase(str1));
                    break;
                case 4:
                    System.out.print("Enter string: ");
                    String str2 = sc.nextLine();
                    System.out.println("Consonants count: " + getConsonants(str2));
                    break;
                case 5:
                    System.out.print("Enter binary string: ");
                    String binary = sc.nextLine();
                    System.out.println("Decimal: " + getDecimal(binary));
                    break;
                case 6:
                    System.out.print("Enter string: ");
                    String str3 = sc.nextLine();
                    System.out.println("Expanded string: " + getExpandedString(str3));
                    break;
                case 7:
                    System.out.print("Enter string: ");
                    String str4 = sc.nextLine();
                    System.out.println("Compressed string: " + getCompressedString(str4));
                    break;
                case 8:
                    System.out.print("Enter number: ");
                    int num = sc.nextInt();
                    primeNumberCheck(num);
                    break;
                case 9:
                    System.out.print("Enter number (0-999): ");
                    int num1 = sc.nextInt();
                    System.out.println("In words: " + numberToWords(num1));
                    break;
                case 10:
                    System.out.print("Enter string: ");
                    String str5 = sc.nextLine();
                    System.out.println("Longest substring without repeating chars: " + lengthOfLongestSubstring(str5));
                    break;
                case 0:
                    System.out.println("Exiting the program.....");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

