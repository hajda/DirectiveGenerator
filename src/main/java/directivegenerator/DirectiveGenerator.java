package main.java.directivegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by PD on 2016.05.08..
 */
public class DirectiveGenerator {
    public void generateDirective(final String moduleName, final String prefix, final String directiveName, final boolean state) {

        // TODO handle bad directive name

        final String bareDirectiveName = directiveName.substring(0, 1).toUpperCase() + directiveName.substring(1);
        final String controllerPrefix = prefix.substring(0, 1).toUpperCase() + prefix.substring(1);
        final String controllerName = controllerPrefix + bareDirectiveName + "Controller";

        final File scriptsFolder = new File("scripts/components/" + directiveName + "/");
        scriptsFolder.mkdirs();
        final File appFolder = new File("scripts/app/" + directiveName + "/");
        appFolder.mkdirs();

        PrintWriter writer;
        try {
            /* directive */

            writer = new PrintWriter("scripts/components/" + directiveName + "/" + directiveName + ".directive.js", "UTF-8");
            writer.println("(function " + directiveName + "DirectiveDefinition() {");
            writer.println("    'use strict';");
            writer.println("    angular.module('" + moduleName + "')");
            writer.println("        .directive('" + prefix + bareDirectiveName + "', " + directiveName + "Directive);");
            writer.println("");
            writer.println("    " + directiveName + "Directive.$inject = [];");
            writer.println("");
            writer.println("    function " + directiveName + "Directive() {");
            writer.println("        return {");
            writer.println("            restrict: 'AE',");
            writer.println("            templateUrl: 'scripts/components/" + directiveName + "/" + directiveName + ".template.html',");
            writer.println("            controller: '" + controllerName + "',");
            writer.println("            controllerAs: 'vm',");
            writer.println("            link: function postLink($scope, elementInstance, attributeInstances, controller) {");
            writer.println("");
            writer.println("            }");
            writer.println("        };");
            writer.println("    }");
            writer.println("})();");
            writer.close();


            /* controller */

            writer = new PrintWriter("scripts/components/" + directiveName + "/" + directiveName + ".controller.js", "UTF-8");
            writer.println("(function " + directiveName + "ControllerDefinition() {");
            writer.println("    'use strict';");
            writer.println("");
            writer.println("    angular.module('" + moduleName + "')");
            writer.println("        .controller('" + controllerName + "', " + directiveName + "Controller);");
            writer.println("");
            writer.println("    " + directiveName + "Controller.$inject = [];");
            writer.println("");
            writer.println("    function " + directiveName + "Controller() {");
            writer.println("        var vm = this;");
            writer.println("    }");
            writer.println("})();");
            writer.close();


            /* Service (factory method) */

            writer = new PrintWriter("scripts/components/" + directiveName + "/" + directiveName + ".service.js", "UTF-8");
            writer.println("(function " + directiveName + "ServiceDefinition() {");
            writer.println("    'use strict';");
            writer.println("");
            writer.println("    angular.module('" + moduleName + "')");
            writer.println("        .factory('" + controllerPrefix + bareDirectiveName + "', " + directiveName + "Service);");
            writer.println("");
            writer.println("    " + directiveName + "Service.$inject = [];");
            writer.println("");
            writer.println("    function " + directiveName + "Service() {");
            writer.println("        return {");
            writer.println("        };");
            writer.println("    }");
            writer.println("})();");
            writer.close();


            /* Filter */

            writer = new PrintWriter("scripts/components/" + directiveName + "/" + directiveName + ".filter.js", "UTF-8");
            writer.println("(function " + directiveName + "FilterDefinition() {");
            writer.println("    'use strict';");
            writer.println("");
            writer.println("    angular.module('" + moduleName + "')");
            writer.println("        .filter('" + controllerPrefix + bareDirectiveName + "', " + directiveName + "Filter);");
            writer.println("");
            writer.println("    " + directiveName + "Filter.$inject = [];");
            writer.println("");
            writer.println("    function " + directiveName + "Filter() {");
            writer.println("        return function " + directiveName + "(input, arg1, arg2) {");
            writer.println("            return input && (arg1 || arg2);");
            writer.println("        };");
            writer.println("    }");
            writer.println("})();");
            writer.close();


            /* template */

            writer = new PrintWriter("scripts/components/" + directiveName + "/" + directiveName + ".template.html", "UTF-8");
            writer.println("TODO generate state");
            writer.close();


            /* State and state template */

            writer = new PrintWriter("scripts/app/" + directiveName + "/" + directiveName + ".js", "UTF-8");

            writer.println("(function " + directiveName + "StateDefinition() {");
            writer.println("    'use strict';");
            writer.println("    angular.module('" + moduleName + "')");
            writer.println("        .config(function ($stateProvider) {");
            writer.println("            $stateProvider");
            writer.println("                .state('" + directiveName + "', {");
            writer.println("                    parent: 'site', // TODO review parent");
            writer.println("                    url: '/" + directiveName + "', // TODO review URL");
            writer.println("                    data: {");
            writer.println("                        authorities: [], // TODO set up access rights");
            writer.println("                        pageTitle: '" + directiveName + "' // TODO review page title");
            writer.println("                },");
            writer.println("                views: {");
            writer.println("                    'content@': {");
            writer.println("                        templateUrl: 'scripts/app/" + directiveName + "/" + directiveName + ".html'//,   // TODO update template url if the state is not directly under the app directory");
            writer.println("                        // controller: '" + controllerName + "' // TODO review hooked controller");
            writer.println("                    }");
            writer.println("                }");
            writer.println("            });");
            writer.println("        });");
            writer.println("})();");

            writer.close();

            writer = new PrintWriter("scripts/app/" + directiveName + "/" + directiveName + ".html", "UTF-8");
            writer.println("<!-- TODO invoke generated directive -->");
            writer.close();


            /* index references */

            writer = new PrintWriter(directiveName + "-index.html", "UTF-8");
            writer.println("    <!-- " + directiveName + " directive -->");
            writer.println("    <script src=\"scripts/app/" + directiveName + "/" + directiveName + ".js\"></script>");
            writer.println("    <script src=\"scripts/components/" + directiveName + "/" + directiveName + ".service.js\"></script>");
            writer.println("    <script src=\"scripts/components/" + directiveName + "/" + directiveName + ".controller.js\"></script>");
            writer.println("    <script src=\"scripts/components/" + directiveName + "/" + directiveName + ".directive.js\"></script>");
            writer.println("    <script src=\"scripts/components/" + directiveName + "/" + directiveName + ".filter.js\"></script>");
            writer.println("    <!-- end " + directiveName + " directive -->");
            writer.close();

        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
