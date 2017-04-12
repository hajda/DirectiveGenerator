package main.java.directivegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by PD on 2016.05.08..
 */
public class DirectiveGenerator {
    public void generateDirective(final String myModule, final String pfx, final String myDirective, final boolean state) {

        // TODO validate directive name (must start with lowerCase alphabetical character, etc)

        /** pfxMyDirective */
        final String[] splitDirectiveName = splitDirectiveName(myDirective);
        final String[] capitalizedSplitDirectiveName = capitalize(splitDirectiveName);
        final String MyDirective = String.join("", capitalizedSplitDirectiveName);
        final String pfxMyDirective = pfx + MyDirective; // pfxMyDirective

        /** pfx-my-directive */
        final String pfx_my_directive = normalizeDirectiveName(pfxMyDirective); //

        //        final String MyDirective = myDirective.substring(0, 1).toUpperCase() + myDirective.substring(1);
        final String Pfx = pfx.substring(0, 1).toUpperCase() + pfx.substring(1);
        final String MyDirectiveController = Pfx + MyDirective + "Controller";
        final String MyDirectiveFilter = Pfx + MyDirective + "Filter";

        final File scriptsFolder = new File("generated/" + myDirective + "/" + "scripts/components/" + myDirective + "/");
        scriptsFolder.mkdirs();
        final File appFolder = new File("generated/" + myDirective + "/" + "scripts/app/" + myDirective + "/");
        appFolder.mkdirs();

        PrintWriter writer;
        try {
            /* directive */

            writer = new PrintWriter("generated/" + myDirective + "/" + "scripts/components/" + myDirective + "/" + myDirective + ".directive.js", "UTF-8");
            writer.println("(function " + myDirective + "DirectiveDefinition() {");
            writer.println("    'use strict';");
            writer.println("    angular.module('" + myModule + "')");
            writer.println("        .directive('" + pfx + MyDirective + "', " + myDirective + "Directive);");
            writer.println("");
            writer.println("    " + myDirective + "Directive.$inject = [];");
            writer.println("");
            writer.println("    function " + myDirective + "Directive() {");
            writer.println("        return {");
            writer.println("            restrict: 'AE',");
            writer.println("            templateUrl: 'scripts/components/" + myDirective + "/" + myDirective + ".template.html',");
            writer.println("            controller: '" + MyDirectiveController + "',");
            writer.println("            controllerAs: '" + MyDirective + "Ctrl',");
            writer.println("            link: function postLink($scope, $element, attribs, ctrl) {");
            writer.println("");
            writer.println("            }");
            writer.println("        };");
            writer.println("    }");
            writer.println("})();");
            writer.close();


            /* controller */

            writer = new PrintWriter("generated/" + myDirective + "/" + "scripts/components/" + myDirective + "/" + myDirective + ".controller.js", "UTF-8");
            writer.println("(function " + myDirective + "ControllerDefinition() {");
            writer.println("    'use strict';");
            writer.println("");
            writer.println("    angular.module('" + myModule + "')");
            writer.println("        .controller('" + MyDirectiveController + "', " + myDirective + "Controller);");
            writer.println("");
            writer.println("    " + myDirective + "Controller.$inject = [];");
            writer.println("");
            writer.println("    function " + myDirective + "Controller() {");
            writer.println("        var " + myDirective + "Ctrl = this;");
            writer.println("    }");
            writer.println("})();");
            writer.close();


            /* Service (factory method) */

            writer = new PrintWriter("generated/" + myDirective + "/" + "scripts/components/" + myDirective + "/" + myDirective + ".service.js", "UTF-8");
            writer.println("(function " + myDirective + "ServiceDefinition() {");
            writer.println("    'use strict';");
            writer.println("");
            writer.println("    angular.module('" + myModule + "')");
            writer.println("        .factory('" + Pfx + MyDirective + "', " + myDirective + "Service);");
            writer.println("");
            writer.println("    " + myDirective + "Service.$inject = [];");
            writer.println("");
            writer.println("    function " + myDirective + "Service() {");
            writer.println("        return {");
            writer.println("        };");
            writer.println("    }");
            writer.println("})();");
            writer.close();


            /* Filter */

            writer = new PrintWriter("generated/" + myDirective + "/" + "scripts/components/" + myDirective + "/" + myDirective + ".filter.js", "UTF-8");
            writer.println("(function " + myDirective + "FilterDefinition() {");
            writer.println("    'use strict';");
            writer.println("");
            writer.println("    angular.module('" + myModule + "')");
            writer.println("        .filter('" + Pfx + MyDirective + "Filter', " + myDirective + "Filter);");
            writer.println("");
            writer.println("    " + myDirective + "Filter.$inject = [];");
            writer.println("");
            writer.println("    function " + myDirective + "Filter() {");
            writer.println("        return function " + myDirective + "(input, arg1, arg2) {");
            writer.println("            return input && (arg1 || arg2);");
            writer.println("        };");
            writer.println("    }");
            writer.println("})();");
            writer.close();


            /* template */

            writer = new PrintWriter("generated/" + myDirective + "/" + "scripts/components/" + myDirective + "/" + myDirective + ".template.html", "UTF-8");
            writer.println("<!-- TODO create template -->");
            writer.close();


            /* State and state template */

            String readableName = MyDirective;
            readableName = String.join(" ", splitDirectiveName(readableName));

            writer = new PrintWriter("generated/" + myDirective + "/" + "scripts/app/" + myDirective + "/" + myDirective + ".state.js", "UTF-8");

            writer.println("(function " + myDirective + "StateDefinition() {");
            writer.println("    'use strict';");
            writer.println("    angular");
            writer.println("        .module('" + myModule + "')");
            writer.println("        .config(function ($stateProvider) {");
            writer.println("            $stateProvider");
            writer.println("                .state('" + pfxMyDirective + "State', {");
            writer.println("                    parent: 'site', // TODO review parent");
            writer.println("                    url: '/" + pfx_my_directive + "', // TODO review URL");
            writer.println("                    data: {");
            writer.println("                        authorities: [], // TODO set up access rights");
            writer.println("                        pageTitle: '" + myDirective + "', // TODO review page title");
            writer.println("                        readableName: '" + readableName + "' // TODO review page title");
            writer.println("                },");
            writer.println("                views: {");
            writer.println("                    'content@': {");
            writer.println("                        templateUrl: 'scripts/app/" + myDirective + "/" + myDirective + ".html'//,   // TODO update template url if the state is not directly under the app directory");
            writer.println("                        // controller: '" + MyDirectiveController + "' // TODO review hooked controller");
            writer.println("                    }");
            writer.println("                }");
            writer.println("            });");
            writer.println("        });");
            writer.println("})();");

            writer.close();

            writer = new PrintWriter("generated/" + myDirective + "/" + "scripts/app/" + myDirective + "/" + myDirective + ".html", "UTF-8");
            writer.println("<" + pfx_my_directive + "></" + pfx_my_directive + ">");
            writer.close();


            /* index references */

            writer = new PrintWriter("generated/" + myDirective + "/" + myDirective + "-index.html", "UTF-8");
            writer.println("    <!-- " + myDirective + " directive -->");
            writer.println("    <script src=\"scripts/app/" + myDirective + "/" + myDirective + ".state.js\"></script>");
            writer.println("    <script src=\"scripts/components/" + myDirective + "/" + myDirective + ".service.js\"></script>");
            writer.println("    <script src=\"scripts/components/" + myDirective + "/" + myDirective + ".controller.js\"></script>");
            writer.println("    <script src=\"scripts/components/" + myDirective + "/" + myDirective + ".directive.js\"></script>");
            writer.println("    <script src=\"scripts/components/" + myDirective + "/" + myDirective + ".filter.js\"></script>");
            writer.println("    <!-- end " + myDirective + " directive -->");
            writer.close();

            /* i18n*/

            writer = new PrintWriter("generated/" + myDirective + "/" + myDirective + ".json", "UTF-8");
            writer.println("{");
            writer.println("    \"" + myDirective + "\": {");
            writer.println("    }");
            writer.println("}");
            writer.close();

        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    private String denormalizeDirectiveName(final String directiveName) {
        final String[] splitDirectiveName = directiveName.split("-");
        capitalize(splitDirectiveName);
        return String.join("", splitDirectiveName);
    }

    private String normalizeDirectiveName(final String directiveName) {
        final String[] splitDirectiveName = splitDirectiveName(directiveName);
        final String[] decapitatedStrings = decapitate(splitDirectiveName);
        return String.join("-", decapitatedStrings);
    }

    private String[] splitDirectiveName(final String directiveName) {
        final String[] split = directiveName.split("(?=\\p{Upper})");
        return split;
    }

    private String[] decapitate(final String...strings) {
        final String[] decapitatedStrings = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            decapitatedStrings[i] = strings[i].toLowerCase();
        }
        return decapitatedStrings;
    }

    private String[] capitalize(final String...strings) {
        final String[] capitalizedStrings = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            final char first = Character.toUpperCase(strings[i].charAt(0));
            capitalizedStrings[i] = first + strings[i].substring(1);
        }
        return capitalizedStrings;
    }
}
