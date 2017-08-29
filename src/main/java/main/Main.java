package main.java.main;

import main.java.directivegenerator.DirectiveGenerator;

public class Main {

    public static void main(final String[] args) {
        final DirectiveGenerator directiveGenerator = new DirectiveGenerator("js");
        directiveGenerator.generateDirective("myApp", "pfx", null, "myDirective");
        System.out.println("READY.");
    }
}
