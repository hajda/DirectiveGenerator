(function configControllerDefinition() {
    'use strict';

    angular.module('phoenixApp')
        .controller('PhxConfigController', configController);

    configController.$inject = [];

    function configController() {
        var vm = this;
    }
})();
