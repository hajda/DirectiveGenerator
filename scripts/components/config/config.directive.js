(function configDirectiveDefinition() {
    'use strict';
    angular.module('phoenixApp')
        .directive('phxConfig', configDirective);

    configDirective.$inject = [];

    function configDirective() {
        return {
            restrict: 'AE',
            templateUrl: 'scripts/components/config/config.template.html',
            controller: 'PhxConfigController',
            controllerAs: 'vm',
            link: function postLink($scope, elementInstance, attributeInstances, controller) {

            }
        };
    }
})();
