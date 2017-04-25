package main.java.main;

import java.io.File;
import java.io.PrintWriter;

import main.java.directivegenerator.DirectiveGenerator;

public class Main {

    public static void main(final String[] args) {
        final DirectiveGenerator directiveGenerator = new DirectiveGenerator();
        directiveGenerator.generateDirective("alcApp", "alc", "showDates", null);
        System.out.println("READY.");
    }
}
