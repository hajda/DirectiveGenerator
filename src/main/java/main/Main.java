package main.java.main;

import main.java.directivegenerator.DirectiveGenerator;

public class Main {

    public static void main(final String[] args) {
        final DirectiveGenerator directiveGenerator = new DirectiveGenerator();
        directiveGenerator.generateDirective("exampleApp", "exp", "exampleComponent", null);
        System.out.println("READY.");
    }
}
