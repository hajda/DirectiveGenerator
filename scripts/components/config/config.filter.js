(function configFilterDefinition() {
    'use strict';

    angular.module('phoenixApp')
        .filter('PhxConfig', configFilter);

    configFilter.$inject = [];

    function configFilter() {
        return function config(input, arg1, arg2) {
            return input && (arg1 || arg2);
        };
    }
})();
