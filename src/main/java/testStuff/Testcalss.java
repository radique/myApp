package testStuff;

import java.util.LinkedList;

public class Testcalss {
    private String name, street, city, state, code;
    Testcalss (String n, String s, String c, String st, String cd) {
        name = n;
        street = s;
        city = c;
        state = st;
        code = cd;
    }

    public String toString () {
        return  name + "\n" + street + "\n" +
                city + " " + state + " " + code;
    }
}

class MailList {
    public static void main(String[] args) {
        LinkedList<Testcalss> m1 = new LinkedList<>();

        m1.add(new Testcalss("J.w West","11 Oak Ave","Urbana", "Test","1444"));
        m1.add(new Testcalss("J.w West","11 Oak Ave","Urbana", "Test","1555"));
        m1.add(new Testcalss("J.w West","11 Oak Ave","Urbana", "Test","666"));

        for (Testcalss elem : m1) {
            System.out.println(elem + "\n");
        }
    }
}
