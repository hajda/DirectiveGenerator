(function editModuleParameterListFilterDefinition() {
    'use strict';

    angular.module('phoenixApp')
        .filter('PhxEditModuleParameterList', editModuleParameterListFilter);

    editModuleParameterListFilter.$inject = [];

    function editModuleParameterListFilter() {
        return function editModuleParameterList(input, arg1, arg2) {
            return input && (arg1 || arg2);
        };
    }
})();
