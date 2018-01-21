package main.java.main;

import main.java.directivegenerator.DirectiveGenerator;

public class GenerateDirective {

    public static void main(final String[] args) {
        final DirectiveGenerator directiveGenerator = new DirectiveGenerator("js"); // parameter is the JavaScript directory name
        directiveGenerator.generateDirective("myApp", "pfx", "path/to/component/folder", "componentName");
        System.out.println("READY.");
    }
}
