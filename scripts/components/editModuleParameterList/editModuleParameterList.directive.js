(function editModuleParameterListDirectiveDefinition() {
    'use strict';
    angular.module('phoenixApp')
        .directive('phxEditModuleParameterList', editModuleParameterListDirective);

    editModuleParameterListDirective.$inject = [];

    function editModuleParameterListDirective() {
        return {
            restrict: 'AE',
            templateUrl: 'scripts/components/editModuleParameterList/editModuleParameterList.template.html',
            controller: 'PhxEditModuleParameterListController',
            controllerAs: 'vm',
            link: function link($scope, elementInstance, attributeInstances, controller) {

            }
        };
    }
})();
