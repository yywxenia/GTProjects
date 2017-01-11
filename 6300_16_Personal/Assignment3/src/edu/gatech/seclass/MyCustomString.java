package edu.gatech.seclass;

import javax.naming.NameNotFoundException;

/**
 * Created by yywxenia on 9/9/16.
 */
public class MyCustomString implements MyCustomStringInterface {

    private String string;
       
    public String getString(){
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int countNumbers() throws NullPointerException {

        if (string == null) {
            throw new NullPointerException();
        }

        if (string != null && string.length() == 0) {
            return 0;
        }

        char[] chars = string.toCharArray();
        int numInt = 0;
        boolean prevCharIsDigit = false;
        for (int i = 0; i < chars.length; i++){
            char C = chars[i];
            if( C >= 48 && C <= 57) {
                // this is a digit
                if(!prevCharIsDigit) {
                    numInt ++;
                    prevCharIsDigit = true;
                }
            } else {
                // this is NOT a digit
                prevCharIsDigit = false;
            }
        }
        return numInt;
    }

    
    
   public  String getEveryNthCharacterFromBeginningOrEnd(int n, boolean startFromEnd) throws IllegalArgumentException, NullPointerException {

        //@throws If the current string is null and "n" is greater than zero
        if(n <= 0) {
          throw new IllegalArgumentException();
        }


        // If the current string is empty or has less than n characters, the method should return an empty string
        if (string.length() < n || string.isEmpty() ){
          return new String();
        }

       if (string == null && n >0) {
           throw new NullPointerException();
       }

       // From beginning or end?
       String  S = this.string;
       if(startFromEnd) {
           S = new StringBuffer(S).reverse().toString();
       }

       // get every nth character
       char[] C = S.toCharArray();
       StringBuffer SB = new StringBuffer();
       for( int i = (n-1); i < S.length(); i += n) {
           SB.append(C[i]);
       }

       return startFromEnd? SB.reverse().toString():SB.toString();
   }


    public void convertDigitsToNamesInSubstring(int startPosition, int endPosition) throws IllegalArgumentException, MyIndexOutOfBoundsException, NullPointerException {


        if(startPosition > endPosition) {
            throw  new IllegalArgumentException();
        } else if ( startPosition < 1 || startPosition > string.length() || endPosition < 1 || endPosition > string.length() ) {
            throw  new MyIndexOutOfBoundsException();
        } else if (string == null) {
            throw  new NullPointerException();
        }

        // Convert to zero-based
        startPosition = startPosition - 1;
        endPosition = endPosition - 1;
        StringBuffer SB = new StringBuffer();
        char[] C = string.toCharArray();

        for (int i = 0; i < C.length; i++) {

            if ( i >= startPosition && i <= endPosition) {
                switch (C[i]) {
                    case '0':
                        SB.append("Zero");
                        break;
                    case '1':
                        SB.append("One");
                        break;
                    case '2':
                        SB.append("Two");
                        break;
                    case '3':
                        SB.append("Three");
                        break;
                    case '4':
                        SB.append("Four");
                        break;
                    case '5':
                        SB.append("Five");
                        break;
                    case '6':
                        SB.append("Six");
                        break;
                    case '7':
                        SB.append("Seven");
                        break;
                    case '8':
                        SB.append("Eight");
                        break;
                    case '9':
                        SB.append("Nine");
                        break;
                    default:
                        SB.append(C[i]);
                        break;
                }
            } else {
                SB.append(C[i]);
            }
        }

        this.string = SB.toString();

    }

}
