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

    /**
     * TODO (In testing)
     * Generate nested directives
     * Add parameter "nested" to {@link DirectiveGenerator}.{@link generateDirective()}
     * A string containing a relative path ending with "/" to concatenate between 'scripts/components/' + nested + 'myDirective/mydirective.component.js'
     * {@link nested} looks something like this: 'anotherDirective/yetAnotherDirective/'
     *
     * @param myModule Module name of the AngularJS app
     * @param pfx Prefix for component and resource identifiers and names. The directiveName specified in the {@link myDirective} parameter will be concatenated to this prefix to form a prefixed namespace for your custom components and resources.
     *          Prefix is usually 2-3 characters and formed from the AngularJS module name or the your project's name if you have several angular modules; so it reminds you to the module/project name.
     *          Say your AngularJS module name is "bananaApp", then your prefix can be e.g. "bna". If you create the "bananaPeeler" directive, then your directive name will be "bnaBananaPeeler" and invoked in html as "bna-banana-peeler", your controller "BnaBananaPeeler" etc.
     * @param nested If your directive is not right in the "component" or "directives" (etc...) folder but nested down in a deeper directory, then you can tell the ath here and the structure will be created for you so you can generate more directives within the structure (for now by running the Main module several times)
     * @param myDirective The directive name without prefix, and in camel case - starting with lower case character. All the names of the components generated are based on this string, but it is concatenated to the profix (see {@link pfx} parameter) and different starting letters capitalized here and there.
     */

    final String jsDirectoryName;

    public DirectiveGenerator(final String baseDirectoryName) {
        this.jsDirectoryName = baseDirectoryName;
    }

    public void generateDirective(final String myModule, final String pfx, final String nest, final String myDirective) {

        /**
         * base folder
         * TODO validate and fix {@link nest} (is there "/" on the end of String {@link nest})
         */
        final String nestFolder = null == nest ? "" : nest + "/";
        final String componentDirectoryName = null == nest ? myDirective : nest;

        // TODO validate directive name (must start with lowerCase alphabetical character, etc)

        /** pfxMyDirective */
        final String[] splitDirectiveName = splitDirectiveName(myDirective);
        final String[] capitalizedSplitDirectiveName = capitalize(splitDirectiveName);
        final String MyDirective = String.join("", capitalizedSplitDirectiveName);
        final String pfxMyDirective = pfx + MyDirective; // pfxMyDirective

        /** pfx-my-directive */
        final String pfx_my_directive = normalizeDirectiveName(pfxMyDirective); //

        final String Pfx = pfx.substring(0, 1).toUpperCase() + pfx.substring(1);
        final String PfxMyDirective = Pfx + MyDirective;
        final String MyDirectiveController = PfxMyDirective + "Controller";
        final String PfxMyDirectiveFilter = PfxMyDirective + "Filter";

        /** TODO if nested, the nested folder name goes   /-- here --\ and at every other place        */
        final File scriptsFolder = new File("generated/" + componentDirectoryName + "/" + jsDirectoryName + "/components/" + nestFolder + myDirective + "/");
        scriptsFolder.mkdirs();
        final File appFolder = new File("generated/" + componentDirectoryName + "/" + jsDirectoryName + "/app/" + nestFolder + myDirective + "/");
        appFolder.mkdirs();
        final File specsFolder = new File("generated/" + componentDirectoryName + "/specs/" + nestFolder + myDirective + "/");
        specsFolder.mkdirs();

        PrintWriter writer;
        try {
            /* directive */

            writer = new PrintWriter("generated/" + componentDirectoryName + "/" + jsDirectoryName + "/components/" + nestFolder + myDirective + "/" + myDirective + ".directive.js", "UTF-8");
            writer.println("(function " + myDirective + "DirectiveDefinition() {");
            writer.println("    'use strict';");
            writer.println("    angular.module('" + myModule + "')");
            writer.println("        .directive('" + pfx + MyDirective + "', " + myDirective + "Directive);");
            writer.println("");
            writer.println("    " + myDirective + "Directive.$inject = ['" + PfxMyDirective + "'];");
            writer.println("");
            writer.println("    function " + myDirective + "Directive(" + MyDirective + ") {");
            writer.println("        return {");
            writer.println("            restrict: 'AE',");
            writer.println("            templateUrl: '" + jsDirectoryName + "/components/" + myDirective + "/" + myDirective + ".template.html',");
            writer.println("            controller: '" + MyDirectiveController + "',");
            writer.println("            controllerAs: '" + MyDirective + "Ctrl',");
            writer.println("            link: function postLink($scope, $element, attribs, ctrl) {");
            writer.println("                // TODO create postLink initialization business logic"); // TODO link function or link object
            writer.println("            }");
            writer.println("        };");
            writer.println("    }");
            writer.println("})();");
            writer.println("");
            writer.close();

            /* controller */

            writer = new PrintWriter("generated/" + componentDirectoryName + "/" + jsDirectoryName + "/components/" + nestFolder + myDirective + "/" + myDirective + ".controller.js", "UTF-8");
            writer.println("(function " + myDirective + "ControllerDefinition() {");
            writer.println("    'use strict';");
            writer.println("");
            writer.println("    angular");
            writer.println("    	.module('" + myModule + "')");
            writer.println("        .controller('" + MyDirectiveController + "', " + myDirective + "Controller);");
            writer.println("");
            writer.println("    " + myDirective + "Controller.$inject = ['$scope', '" + PfxMyDirective + "'];");
            writer.println("");
            writer.println("    function " + myDirective + "Controller($scope, " + MyDirective + ") {");
            writer.println("        var " + myDirective + "Ctrl = this; // TODO Here is a reference to THIS controller");
            writer.println("");
            writer.println("        /* \"private\" */");
            writer.println("");
            writer.println("        // TODO declare/initialize private variables here");
            writer.println("        var myData = []; // TODO this is a sample");
            writer.println("");
            writer.println("        /* \"public\" */");
            writer.println("");
            writer.println("        /*      API */ // TODO Hook ctrl API functions on the reference to THIS controller like the example below.");
            writer.println("");
            writer.println("        " + myDirective + "Ctrl.setItemName = setItemName; // TODO this is a sample");
            writer.println("");
            writer.println("        /*      API function definitions */ // TODO place ctrl API function definitions here");
            writer.println("");
            writer.println("        /**");
            writer.println("         * TODO this is a sample");
            writer.println("         */");
            writer.println("        function setItemName(itemIndex, itemName) {");
            writer.println("            getItem(itemIndex).name = itemName;");
            writer.println("            myData.push(itemName);");
            writer.println("        }");
            writer.println("");
            writer.println("        /* implementation details */ // TODO place decomponed function definitions here");
            writer.println("");
            writer.println("        /**");
            writer.println("         * TODO this is a sample");
            writer.println("         */");
            writer.println("        function getItem(itemIndex) {");
            writer.println("            return $scope.myModel.items[itemIndex];");
            writer.println("        }");
            writer.println("    }");
            writer.println("})();");
            writer.println("");
            writer.close();

            /* Service (factory method) */

            writer = new PrintWriter("generated/" + componentDirectoryName + "/" + jsDirectoryName + "/components/" + nestFolder + myDirective + "/" + myDirective + ".service.js", "UTF-8");
            writer.println("(function " + myDirective + "ServiceDefinition() {");
            writer.println("    'use strict';");
            writer.println("");
            writer.println("    angular.module('" + myModule + "')");
            writer.println("        .factory('" + PfxMyDirective + "', " + myDirective + "Service);");
            writer.println("");
            writer.println("    " + myDirective + "Service.$inject = [];");
            writer.println("");
            writer.println("    function " + myDirective + "Service() {");
            writer.println("        var api = {");
            writer.println("            // hook service API functions here as object properties");
            writer.println("        };");
            writer.println("");
            writer.println("        // TODO create service business logic");
            writer.println("");
            writer.println("        return api;");
            writer.println("    }");
            writer.println("})();");
            writer.println("");
            writer.close();

            /* Filter */

            writer = new PrintWriter("generated/" + componentDirectoryName + "/" + jsDirectoryName + "/components/" + nestFolder + myDirective + "/" + myDirective + ".filter.js", "UTF-8");
            writer.println("(function " + myDirective + "FilterDefinition() {");
            writer.println("    'use strict';");
            writer.println("");
            writer.println("    angular.module('" + myModule + "')");
            writer.println("        .filter('" + PfxMyDirectiveFilter + "', " + myDirective + "Filter);");
            writer.println("");
            writer.println("    " + myDirective + "Filter.$inject = ['" + PfxMyDirective + "'];");
            writer.println("");
            writer.println("    function " + myDirective + "Filter(" + MyDirective + ") {");
            writer.println("        /**");
            writer.println("         * Usage in templates: "); // TODO finalize doc comment
            writer.println("         * Usage in JS: "); // TODO finalize doc comment
            writer.println("         */");
            writer.println("        return function " + myDirective + "(input, arg1, arg2) {");
            writer.println("            return /* TODO create filter business logic */;");
            writer.println("        };");
            writer.println("    }");
            writer.println("})();");
            writer.println("");
            writer.close();

            /* template */

            writer = new PrintWriter("generated/" + componentDirectoryName + "/" + jsDirectoryName + "/components/" + nestFolder + myDirective + "/" + myDirective + ".template.html", "UTF-8");
            writer.println("<!-- TODO create template -->");
            writer.println("");
            writer.close();

            /* State and state template */

            String readableName = MyDirective;
            readableName = String.join(" ", splitDirectiveName(readableName));

            writer = new PrintWriter("generated/" + componentDirectoryName + "/" + jsDirectoryName + "/app/" + nestFolder + myDirective + "/" + myDirective + ".state.js", "UTF-8");

            writer.println("(function " + myDirective + "StateDefinition() {");
            writer.println("    'use strict';");
            writer.println("");
            writer.println("    angular");
            writer.println("        .module('" + myModule + "')");
            writer.println("        .config(" + myDirective + "StateConfig);");
            writer.println("");
            writer.println("    " + myDirective + "StateConfig.$inject = ['$stateProvider'];");
            writer.println("");
            writer.println("    function " + myDirective + "StateConfig($stateProvider) {");
            writer.println("        $stateProvider.state(");
            writer.println("            '" + myDirective + "State',");
            writer.println("            {");
            writer.println("                parent: 'app', // TODO review parent");
            writer.println("                url: '/" + pfx_my_directive + "', // TODO review URL");
            writer.println("                data: {");
            writer.println("                    authorities: [], // TODO set up access rights");
            writer.println("                    pageTitle: '" + myDirective + "', // TODO review page title");
            writer.println("                    readableName: '" + readableName + "' // TODO review page title");
            writer.println("                },");
            writer.println("                views: {");
            writer.println("                    'content@': { // TODO rewise the view slot to view the template in");
            writer.println("                        templateUrl: 'app/" + myDirective + "/" + myDirective + ".html'//,   // TODO update template url if the state is not directly under the app directory");
            writer.println("                        // controller: '" + MyDirectiveController + "' // TODO review hooked controller");
            writer.println("                    }");
            writer.println("                }");
            writer.println("            }");
            writer.println("        );");
            writer.println("    }");
            writer.println("})();");
            writer.println("");

            writer.close();

            writer = new PrintWriter("generated/" + componentDirectoryName + "/" + jsDirectoryName + "/app/" + nestFolder + myDirective + "/" + myDirective + ".html", "UTF-8");
            writer.println("<" + pfx_my_directive + "></" + pfx_my_directive + ">");
            writer.close();

            /* index references */

            writer = new PrintWriter("generated/" + componentDirectoryName + "/" + myDirective + "-index.html", "UTF-8");
            writer.println("    <!-- " + myDirective + " directive -->");
            writer.println("    <script src=\"" + jsDirectoryName + "/app/" + myDirective + "/" + myDirective + ".state.js\"></script>");
            writer.println("    <script src=\"" + jsDirectoryName + "/components/" + myDirective + "/" + myDirective + ".service.js\"></script>");
            writer.println("    <script src=\"" + jsDirectoryName + "/components/" + myDirective + "/" + myDirective + ".controller.js\"></script>");
            writer.println("    <script src=\"" + jsDirectoryName + "/components/" + myDirective + "/" + myDirective + ".directive.js\"></script>");
            writer.println("    <script src=\"" + jsDirectoryName + "/components/" + myDirective + "/" + myDirective + ".filter.js\"></script>");
            writer.println("    <!-- end " + myDirective + " directive -->");
            writer.println("");
            writer.close();

            /* i18n*/

            writer = new PrintWriter("generated/" + componentDirectoryName + "/" + pfxMyDirective + "State.json", "UTF-8");
            writer.println("{");
            writer.println("    \"" + myDirective + "\": {");
            writer.println("    }");
            writer.println("}");
            writer.println("");
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
            writer = new PrintWriter("generated/" + componentDirectoryName + "/specs/" + nestFolder + myDirective + "/" + myDirective + ".directive.spec.js", "UTF-8");
            writer.println("(function " + pfxMyDirective + "DirectiveSpecsDefinition() {");
            writer.println("    'use strict';");
            writer.println("");
            writer.println("    describe('" + myDirective + " directive >', function " + myDirective + "DirectiveDescription() {");
            writer.println("");
            writer.println("        var directiveSnippet = '<" + pfx_my_directive + "></" + pfx_my_directive + ">', // TODO complete snippet with attributes/ template parameters / etc...");
            writer.println("            $compile, $scope,");
            writer.println("            " + MyDirective);
            writer.println("        ;");
            writer.println("");
            writer.println("        beforeEach(module('" + myModule + "'));");
            writer.println("        beforeEach(module('phoenixApp', function mockDependencies($provide) {");
            writer.println("            mockDependencies($provide);");
            writer.println("        }));");
            writer.println("        beforeEach(inject(function injectDependencies(_$compile_, $rootScope, _" + MyDirective + "_) {");
            writer.println("            $compile = _$compile_");
            writer.println("            $scope = $rootScope.$new();");
            writer.println("            " + MyDirective  + "= _" + MyDirective + "_;");
            writer.println("        }));");
            writer.println("");
            writer.println("        it('should render directive', function spec() {");
            writer.println("            /* compile and digest snippet */");
            writer.println("            var element = $compile(directiveSnippet)($scope);");
            writer.println("            $scope.$digest();");
            writer.println("");
            writer.println("            /* verify that the directive evaluates to the template */");
            writer.println("            expect(element.html()).toBeDefined();");
            writer.println("");
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
            writer.println("");
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
            writer.println("        });");
            writer.println("    }");
            writer.println("})();");
            writer.println("");
            writer.close();

            /* controller specs */

            writer = new PrintWriter("generated/" + componentDirectoryName + "/specs/" + nestFolder + myDirective + "/" + myDirective + ".controller.spec.js", "UTF-8");
            writer.println("(function " + pfxMyDirective + "ControllerSpecsDefinition() {");
            writer.println("    'use strict';");
            writer.println("");
            writer.println("    describe('" + MyDirectiveController + " >', function " + myDirective + "ControllerDescription() {");
            writer.println("");
            writer.println("        var $scope, " + myDirective + "Ctrl,");
            writer.println("            " + MyDirective);
            writer.println("        ;");
            writer.println("");
            writer.println("        beforeEach(module('" + myModule + "'));");
            writer.println("        beforeEach(module('phoenixApp', function mockDependencies($provide) {");
            writer.println("            mockDependencies($provide);");
            writer.println("        }));");
            writer.println("        beforeEach(inject(function injectDependencies(_$controller_, $rootScope, _" + MyDirective + "_) {");
            writer.println("            $scope = $rootScope.$new();");
            writer.println("            " + myDirective + "Ctrl = $controller('" + MyDirectiveController + "', {$rootScope: $rootScope, $scope: $scope});");
            writer.println("            " + MyDirective  + " = _" + MyDirective + "_;");
            writer.println("        }));");
            writer.println("");
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
            writer.println("");
            writer.println("            var itemName = 'Item 0';");
            writer.println("            " + myDirective + "Ctrl.setItemName(0, itemName);");
            writer.println("            expect($scope.myModel.items[0].name).toBe(itemName);");
            writer.println("        });");
            writer.println("    });");
            writer.println("");
            writer.println("    function mockDependencies($provide) {");
            writer.println("        // e.x. mockDependentComponent($provide);");
            writer.println("    }");
            writer.println("");
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
            writer.println("        });");
            writer.println("    }");
            writer.println("})();");
            writer.println("");
            writer.close();

            /* Service specs */

            writer = new PrintWriter("generated/" + componentDirectoryName + "/specs/" + nestFolder + myDirective + "/" + myDirective + ".service.spec.js", "UTF-8");
            writer.println("(function " + pfxMyDirective + "ServiceSpecsDefinition() {");
            writer.println("    'use strict';");
            writer.println("");
            writer.println("    describe('" + MyDirective + " service >', function " + myDirective + "ServiceDescription() {");
            writer.println("");
            writer.println("        var " + MyDirective);
            writer.println("        ;");
            writer.println("");
            writer.println("        beforeEach(module('" + myModule + "'));");
            writer.println("        beforeEach(module('phoenixApp', function mockDependencies($provide) {");
            writer.println("            mockDependencies($provide);");
            writer.println("        }));");
            writer.println("        beforeEach(inject(function injectDependencies(_" + MyDirective + "_) {");
            writer.println("            " + MyDirective  + " = _" + MyDirective + "_;");
            writer.println("        }));");
            writer.println("");
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
            writer.println("");
            writer.println("            var itemName = 'Item 0';");
            writer.println("            // e.g. " + myDirective + ".setItemName(myData, 0, itemName);");
            writer.println("            // e.g. expect(myData.items[0].name).toBe(itemName);");
            writer.println("        });");
            writer.println("    });");
            writer.println("");
            writer.println("    function mockDependencies($provide) {");
            writer.println("        // e.x. mockDependentComponent($provide);");
            writer.println("    }");
            writer.println("");
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
            writer.println("        });");
            writer.println("    }");
            writer.println("})();");
            writer.println("");
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
