(function configStateDefinition() {
    'use strict';
    angular.module('phoenixApp')
        .config(function ($stateProvider) {
            $stateProvider
                .state('config', {
                    parent: 'site', // TODO review parent
                    url: '/config', // TODO review URL
                    data: {
                        authorities: [], // TODO set up access rights
                        pageTitle: 'config' // TODO review page title
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/config/config.html'//,   // TODO update template url if the state is not directly under the app directory
                        // controller: 'PhxConfigController' // TODO review hooked controller
                    }
                }
            });
        });
})();
