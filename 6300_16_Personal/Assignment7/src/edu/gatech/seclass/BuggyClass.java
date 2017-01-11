package edu.gatech.seclass;
import com.sun.xml.internal.ws.api.model.ExceptionType;
import static java.lang.Math.abs;
/**
 * Created by yywxenia on 11/4/16.
 */

public class BuggyClass {
    public void buggyMethod1(int a, int b) {
        if (a > b) {
            System.out.println(">>> Result 1");
            System.out.println(a / b);
        } else {
            System.out.println(">>> Result 2");
            System.out.println(a);
            System.out.println(b);
            System.out.println(a / b);
        }
    }


    public void buggyMethod2(int a, int b) {
        if (a > 0 ) {
            System.out.println(">>> Output 1");
            System.out.println(a / b);
        } else if (a<0) {
            System.out.println(">>> Output 2");
            System.out.println(a / b);
        } else if (a == 0) {
            System.out.println(">>> Output 3");
            System.out.println(a / b);
        }
    }


    public void buggyMethod3(int a, int b) {
            if (a > 0 ) {
                System.out.println(">>> Output 1");
            } else if (a<0) {
                System.out.println(">>> Output 2");
                System.out.println(a/b);
                //try {
                //    int result =a/b;
                //} catch (Exception e) {
                //    System.out.println(e.getMessage());
                //}
            }
        }

    public void buggyMethod4(int a, int b) {
        if (a > 0 ) {
            System.out.println(">>> Output 1");
        } else if (a < 0) {
            System.out.println(">>> Output 2");
            System.out.println(a/b);
            //try {
            //    int result =a/b;
            //} catch (Exception e) {
            //    System.out.println(e.getMessage());
            //}
        }
    }


    public void buggyMethod5(int i) {
        /**
         * 1. I tried but I cannot achieve this goal.
         * 2. The input i is integer and x is defined as integer as well. Thus, it is impossible for int i/0 to
         * get aways with the divide by 0 fault.
         * 3. Since we cannot create other parameters/using catch, and we cannot do specified changes,
         * there is no way I can transfer x or i into float using the same parameter name to get "infinity" or
         * to re-dfine the operation "/" and others.
         */

        int x;

        x = i/0;

    }
}