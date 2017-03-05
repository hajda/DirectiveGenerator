(function configGroupStateDefinition() {
    'use strict';
    angular.module('phoenixApp')
        .config(function ($stateProvider) {
            $stateProvider
                .state('configGroup', {
                    parent: 'site', // TODO review parent
                    url: '/configGroup', // TODO review URL
                    data: {
                        authorities: [], // TODO set up access rights
                        pageTitle: 'configGroup' // TODO review page title
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/configGroup/configGroup.html'//,   // TODO update template url if the state is not directly under the app directory
                        // controller: 'PhxConfigGroupController' // TODO review hooked controller
                    }
                }
            });
        });
})();
