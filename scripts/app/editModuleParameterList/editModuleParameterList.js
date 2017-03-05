(function editModuleParameterListFilterDefinition() {
    'use strict';
    angular.module('phoenixApp')
        .config(function ($stateProvider) {
            $stateProvider
                .state('editModuleParameterList', {
                    parent: 'app',
                    url: '/editModuleParameterList',
                    data: {
                        authorities: [],
                        pageTitle: 'editModuleParameterList'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/account/editModuleParameterList/editModuleParameterList.html',
                        controller: 'PhxEditModuleParameterListController'
                    }
                }
            });
        });
})();
