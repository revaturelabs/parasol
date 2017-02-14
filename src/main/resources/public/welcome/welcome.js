(function () {
    'use strict';

    angular
        .module('ParasolApp')
        .controller('WelcomeController', WelcomeController);

    WelcomeController.$inject = ['$location', '$window', 'AuthenticationService', 'ErrorService'];
    function WelcomeController($location, $window, AuthenticationService, ErrorService) 
    {
        var vm = this;

        vm.login = login;

        (function initController() {
            AuthenticationService.ClearCredentials();
        })();

        function login() 
        {
            //Prevent multiple submissions
            vm.dataLoading = true;
            
            AuthenticationService.Login();
        }
    }
})();