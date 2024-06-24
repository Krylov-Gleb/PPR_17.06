package Tests;

public class Test3 {
    public static void main(String[] args) {
        class Main {
            public static void main(String[] args) {
                int i = 0;
                while (i < 100) {
                    System.out.println("Line " + (i + 1));
                    i++;
                }
            }
        }
    }
}