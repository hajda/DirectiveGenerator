(function configServiceDefinition() {
    'use strict';

    angular.module('phoenixApp')
        .factory('PhxConfig', configService);

    configService.$inject = [];

    function configService() {
        return {
        };
    }
})();
