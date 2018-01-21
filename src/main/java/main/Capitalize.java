package main.java.main;

import main.java.capitalizer.Capitalizer;

public class Capitalize {

    public static void main(final String[] args) {
        System.out.println(new Capitalizer(" ", "").capitalize("capitalize this"));
        System.out.println(new Capitalizer("(?=\\p{Upper})", " ").capitalize("deCapitateThis").toLowerCase());
    }
}
