/* 
@filename - MyString.java
@description - This will perform most of the string operations
@author - Divyansh Tak
*/

import java.util.*;

public class Main {
    
    
    public static void main(String[] args) {

         Scanner sc = new Scanner(System.in);
         System.out.print("Enter String : ");
         String currentString=sc.nextLine();

          MyString str = new MyString(currentString.trim());  // trim is used to remove spaces from start and end
          
          // select operation to perform
          while(true) {
            System.out.println("\nChoose operation:");
            System.out.println("1. Append");
            System.out.println("2. Count Words");
            System.out.println("3. Replace");
            System.out.println("4. Is Palindrome");
            System.out.println("5. Slice");
            System.out.println("6. Split");
            System.out.println("7. Max Repeat");
            System.out.println("8. Sort");
            System.out.println("9. Reverse");
            System.out.println("10. Shift");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch(choice) {
                case 1:
                    System.out.print("Enter string to append: ");
                    String appendStr = sc.nextLine();
                    System.out.println("Result: " + str.append(appendStr));
                    break;
                case 2:
                    System.out.println("Word count: " + str.countWords());
                    break;
                case 3:
                    System.out.print("Enter old substring: ");
                    String oldStr = sc.nextLine();
                    System.out.print("Enter new substring: ");
                    String newStr = sc.nextLine();
                    System.out.println("Result: " + str.replacestr(oldStr, newStr));
                    break;
                case 4:
                    boolean flag = str.isPalindrome();
                    if(flag==true){
                        System.out.println("String is palindrome");
                    }
                    else
                    {
                        System.out.println("String is not palindrome");
                    }
                    break;
                case 5:
                    System.out.print("Enter start index: ");
                    int start = sc.nextInt();
                    System.out.print("Enter length: ");
                    int len = sc.nextInt();
                    System.out.println("Result: " + str.slice(start, len));
                    break;
                case 6:
                    ArrayList<String> list = str.split();
                     System.out.print(list);
                    break;
                case 7:
                    System.out.print(str.maxRepeat());
                    break;
                case 8:
                    System.out.println("Sorted string: " + str.sort());
                    break;
                case 9:
                    System.out.println("Reversed string: " + str.reverse());
                    break;
                case 10:
                    System.out.print("Enter number of characters to shift: ");
                    int n = sc.nextInt();
                    System.out.println("Result: " + str.shift(n));
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

class MyString{

    String  currentString;

//constructor
    MyString(String str){
        this.currentString = str;
    }
    
//append String
    String append(String newString){
        
        currentString+=newString;
        return currentString;
        
    }

// countWords in a string
    int countWords(){
        int idx=0;
        int count=0;

        if(currentString.length() ==0)
        {
            return 0;
        }
        
        while(idx<currentString.length()){

            if(currentString.charAt(idx)==' '){
                count++;
                
                while(idx < currentString.length() && currentString.charAt(idx) == ' ')  //removing extra spaces in between
                {
                      idx++;
                }
                idx--;
            }
            idx++;
        }
        return count+1; // count+1 to count last word also
    }


    

// check if substring matches or not  
     boolean matchesAt(String a, int idx) {
        if (idx + a.length() > currentString.length()) return false;

       for (int j = 0; j < a.length(); j++) {
          if (currentString.charAt(idx + j) != a.charAt(j)) {
            return false; 
           }
    }
    return true; 
}

// replaces occurences of string a with string b
    String replacestr(String a, String b) {

      String result = "";
       int idx = 0;

      while (idx < currentString.length()) {
        if (matchesAt(a, idx)) {
            result += b;
            idx += a.length(); 
        } else {
            result += currentString.charAt(idx);
            idx++;
        }
      }

       return result;
}


// palindrome check
    boolean isPalindrome(){

        int idx=0;
        String str= replacestr(" ","");
        str=str.toLowerCase();
         int n = str.length();
        while(idx<str.length()/2){
           
            if(str.charAt(idx) != str.charAt(n-idx-1))
            {
                return false;
            }
            idx++;
        }

        return true;

    }

// slice method
    String slice(int start,int length)
    {
        if (start + length > currentString.length()) {
         length = currentString.length() - start;   // adjust length
       }
         int idx=0;
         String s="";
        while(idx<currentString.length()){
            
            if(idx<start || idx>=start+length) // removing charcter in range(start,end-1)
            {
               s+=currentString.charAt(idx);
            }
                  idx++;
           
        }

        return s;

    }

// get substring from currentString
    String substr(int start,int end){
         
         String s="";
         for(int i=start;i<end;i++)
         {
            s+=currentString.charAt(i);
         }

         return s;
    }

// string words converted into array
    ArrayList<String> split(){

        
        ArrayList<String> list= new ArrayList<>();

        int idx=0, idx2=0, i=0 ;
      
        while(idx<currentString.length()){
            if(currentString.charAt(idx)==' '){
                list.add(substr(idx2,idx)); // store words in array
                 i++;
                 while(idx < currentString.length() && currentString.charAt(idx) == ' ') {  // removing spaces from start and end
                    idx++;
                 }
                 idx2=idx;
            }
            idx++;
        }

        if(idx2 < currentString.length()) {
           list.add(substr(idx2, idx));
          }
       
       return list;

    }

// Max occuring character
   int maxRepeat() {
   
    int[] freq = new int[256];

   
    for(int i = 0; i < currentString.length(); i++) {
        char ch = currentString.charAt(i);
        if(ch == ' ') continue; 
        freq[ch]++;
    }

    
    char maxChar = '\0';
    int maxCount = 0;

    for(int i = 0; i < 256; i++) {
        if(freq[i] > maxCount) {
            maxCount = freq[i];
            maxChar = (char)i;
        }
    }
    System.out.print("Max Repeating Char : " + maxChar+ " -> ");
    return maxCount;
  }



// sorting string 
  String sort() {
    
    int[] freq = new int[256]; 

    for(int i = 0; i < currentString.length(); i++) {
        char ch = currentString.charAt(i);
        if(ch == ' ') continue; 
        freq[ch]++;
    }

    
    String s = "";     
    for(int i = 0; i < 256; i++) {
        while(freq[i] > 0) {
            s += (char)i;
            freq[i]--;
        }
    }

    return s;
 }

 // reverse a string
    String reverse(){

        String s="";
        int n = currentString.length();
        for(int i=n-1;i>=0;i--){
            s+=currentString.charAt(i);
        }

        return s;
    }
  
//shift n places
    String shift(int n) {
        
       
        int len = currentString.length();
         n = n % len;
          if(n==0) return currentString;
        String s="";
        int idx =0 ;
        int shift=len - n;
        int idx2=shift;
        while(idx2<len){  // adding charcters which are to be shifted
            s+=currentString.charAt(idx2);
            idx2++;
        }

        while(idx<shift){  //now adding remaining characters
            s+=currentString.charAt(idx);
            idx++;
        }

        return s;
    }

}

