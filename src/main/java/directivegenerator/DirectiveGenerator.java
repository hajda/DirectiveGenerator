package main.java.directivegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by PD on 2016.05.08..
 * TODO @author doc comments on top of every files
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

        final File scriptsFolder = new File("generated/" + myDirective + "/scripts/components/" + myDirective + "/");
        scriptsFolder.mkdirs();
        final File appFolder = new File("generated/" + myDirective + "/scripts/app/" + myDirective + "/");
        appFolder.mkdirs();
        final File specsFolder = new File("generated/" + myDirective + "/specs/" + myDirective + "/");
        specsFolder.mkdirs();

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

            /* SPECS */

            /* directive specs */

            /**
             * TODO How to obtain the controller hooked with the rendered directive
             * TODO How to mock dependencies
             * TODO How to element.click() + mock accont service
             * TODO How to Spy
             * TODO How to expectation error messages
             * TODO How to use $injector:
             *      $scope = $injector.get('$rootScope').$new();
             *      $httpBackend = $injector.get('$httpBackend');
             *      $injector.get('$controller')('ActivationController as vm', locals);
             */
            writer = new PrintWriter("generated/" + myDirective + "/specs/" + myDirective + "/" + myDirective + ".directive.spec.js", "UTF-8");
            writer.println("(function " + pfxMyDirective + "DirectiveSpecsDefinition() {");
            writer.println("    'use strict';");
            writer.println("    ");
            writer.println("    describe('" + myDirective + " directive >', function " + myDirective + "DirectiveDescription() {");
            writer.println("        ");
            writer.println("        var directiveSnippet = '<" + pfx_my_directive + "></" + pfx_my_directive + ">', // TODO complete snippet with attributes/ template parameters / etc...");
            writer.println("            $compile, $scope,");
            writer.println("            " + MyDirective);
            writer.println("        ;");
            writer.println("        ");
            writer.println("        beforeEach(module('" + myModule + "'));");
            writer.println("        beforeEach(module('phoenixApp', function mockDependencies($provide) {");
            writer.println("            mockDependencies($provide);");
            writer.println("        }));");
            writer.println("        beforeEach(inject(function injectDependencies(_$compile_, $rootScope, _" + MyDirective + "_) {");
            writer.println("            $compile = _$compile_");
            writer.println("            $scope = , $rootScope.$new();");
            writer.println("            " + MyDirective  + "= _" + MyDirective + "_;");
            writer.println("        }));");
            writer.println("        ");
            writer.println("        it('should render directive', function spec() {");
            writer.println("            /* compile and digest snippet */");
            writer.println("            var element = $compile(directiveSnippet)($scope);");
            writer.println("            $scope.$digest();");
            writer.println("            ");
            writer.println("            /* verify that the directive evaluates to the template */");
            writer.println("            expect(element.html()).toBeDefined();");
            writer.println("            ");
            writer.println("            /* expectations */");
            writer.println("            // e.g. expect(element.find('label')[0].innerHTML.trim()).toEqual('myDataLabel');");
            writer.println("            // e.g. expect(element.find('input')[0].getAttribute('name')).toEqual('myDataInput');");
            writer.println("            // e.g. expect(element.find('input')[0].getAttribute('id')).toEqual('" + pfx + "-input-' + $scope.myModel.items[0].name);");
            writer.println("            // e.g. expect($scope.myModel.items[0].value).toBe(element.find('#" + pfx + "-input-' + $scope.myModel.items[0].name).val());");
            writer.println("        });");
            writer.println("    });");
            writer.println("    function mockDependencies($provide) {");
            writer.println("        // e.x. mockDependentComponent($provide);");
            writer.println("    }");
            writer.println("    ");
            writer.println("    /** This is an example */");
            writer.println("    function mockDependentComponent($provide) {");
            writer.println("        var componentPrivateVariableMock = [];");
            writer.println("        $provide.factory('DependentComponent', function mockComponent() {");
            writer.println("            return {");
            writer.println("                setItemName: function setItemName(myData, index, itemName) {");
            writer.println("                    myData.items[index].name = itemName");
            writer.println("                },");
            writer.println("                otherFunction: function addElement(param) {");
            writer.println("                    componentPrivateVariableMock.push(param);");
            writer.println("                }");
            writer.println("            };");
            writer.println("        }");
            writer.println("    }");
            writer.println("})();");
            writer.close();

            /* controller specs */

            writer = new PrintWriter("generated/" + myDirective + "/specs/" + myDirective + "/" + myDirective + ".controller.spec.js", "UTF-8");
            writer.println("(function " + pfxMyDirective + "ControllerSpecsDefinition() {");
            writer.println("    'use strict';");
            writer.println("    ");
            writer.println("    describe('" + MyDirectiveController + " >', function " + myDirective + "ControllerDescription() {");
            writer.println("        ");
            writer.println("        var $scope, " + myDirective + "Ctrl,");
            writer.println("            " + MyDirective);
            writer.println("        ;");
            writer.println("        ");
            writer.println("        beforeEach(module('" + myModule + "'));");
            writer.println("        beforeEach(module('phoenixApp', function mockDependencies($provide) {");
            writer.println("            mockDependencies($provide);");
            writer.println("        }));");
            writer.println("        beforeEach(inject(function injectDependencies(_$controller_, $rootScope, _" + MyDirective + "_) {");
            writer.println("            $scope = , $rootScope.$new();");
            writer.println("            " + myDirective + "Ctrl = $controller('" + MyDirectiveController + "', {$rootScope: $rootScope, $scope: $scope});");
            writer.println("            " + MyDirective  + " = _" + MyDirective + "_;");
            writer.println("        }));");
            writer.println("        ");
            writer.println("        it('should ...', function spec() {");
            writer.println("            /* Example $scope initialiyation*/");
            writer.println("            $scope.myModel = {");
            writer.println("                items: [");
            writer.println("                    {");
            writer.println("                        name: undefined");
            writer.println("                    },");
            writer.println("                    {");
            writer.println("                        name: 'Item 1'");
            writer.println("                    }");
            writer.println("                ]");
            writer.println("            };");
            writer.println("            ");
            writer.println("            var itemName = 'Item 0';");
            writer.println("            // e.g. " + myDirective + "Ctrl.setItemName(0, itemName);");
            writer.println("            // e.g. expect($scope.myModel.items[0].name).toBe(itemName);");
            writer.println("        });");
            writer.println("    });");
            writer.println("    ");
            writer.println("    function mockDependencies($provide) {");
            writer.println("        // e.x. mockDependentComponent($provide);");
            writer.println("    }");
            writer.println("    ");
            writer.println("    /** This is an example */");
            writer.println("    function mockDependentComponent($provide) {");
            writer.println("        var componentPrivateVariableMock = [];");
            writer.println("        $provide.factory('DependentComponent', function mockComponent() {");
            writer.println("            return {");
            writer.println("                setItemName: function setItemName(myData, index, itemName) {");
            writer.println("                    myData.items[index].name = itemName");
            writer.println("                },");
            writer.println("                otherFunction: function addElement(param) {");
            writer.println("                    componentPrivateVariableMock.push(param);");
            writer.println("                }");
            writer.println("            };");
            writer.println("        }");
            writer.println("    }");
            writer.println("})();");
            writer.close();

            /* Service specs */

            writer = new PrintWriter("generated/" + myDirective + "/specs/" + myDirective + "/" + myDirective + ".service.spec.js", "UTF-8");
            writer.println("(function " + pfxMyDirective + "ServiceSpecsDefinition() {");
            writer.println("    'use strict';");
            writer.println("    ");
            writer.println("    describe('" + MyDirective + " service >', function " + myDirective + "ServiceDescription() {");
            writer.println("        ");
            writer.println("        var " + myDirective);
            writer.println("        ;");
            writer.println("        ");
            writer.println("        beforeEach(module('" + myModule + "'));");
            writer.println("        beforeEach(module('phoenixApp', function mockDependencies($provide) {");
            writer.println("            mockDependencies($provide);");
            writer.println("        }));");
            writer.println("        beforeEach(inject(function injectDependencies(_" + MyDirective + "_) {");
            writer.println("            " + MyDirective  + " = _" + MyDirective + "_;");
            writer.println("        }));");
            writer.println("        ");
            writer.println("        it('should ...', function spec() {");
            writer.println("            /* Example $scope initialiyation*/");
            writer.println("            var myData = {");
            writer.println("                items: [");
            writer.println("                    {");
            writer.println("                        name: undefined");
            writer.println("                    },");
            writer.println("                    {");
            writer.println("                        name: 'Item 1'");
            writer.println("                    }");
            writer.println("                ]");
            writer.println("            };");
            writer.println("            ");
            writer.println("            var itemName = 'Item 0';");
            writer.println("            // e.g. " + myDirective + ".setItemName(myData, 0, itemName);");
            writer.println("            // e.g. expect(myData.items[0].name).toBe(itemName);");
            writer.println("        });");
            writer.println("    });");
            writer.println("    ");
            writer.println("    function mockDependencies($provide) {");
            writer.println("        // e.x. mockDependentComponent($provide);");
            writer.println("    }");
            writer.println("    ");
            writer.println("    /** This is an example */");
            writer.println("    function mockDependentComponent($provide) {");
            writer.println("        var componentPrivateVariableMock = [];");
            writer.println("        $provide.factory('DependentComponent', function mockComponent() {");
            writer.println("            return {");
            writer.println("                setItemName: function setItemName(myData, index, itemName) {");
            writer.println("                    myData.items[index].name = itemName");
            writer.println("                },");
            writer.println("                otherFunction: function addElement(param) {");
            writer.println("                    componentPrivateVariableMock.push(param);");
            writer.println("                }");
            writer.println("            };");
            writer.println("        }");
            writer.println("    }");
            writer.println("})();");
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
