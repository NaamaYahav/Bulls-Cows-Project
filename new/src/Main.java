import java.util.Scanner;

public class Main{
    public static boolean isEqual(String a, String b) {
        if (a.length() != b.length())
            return false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i))
                return false;
        }
        return true;
    }
    public static void main(String [] args) {
    }
}




