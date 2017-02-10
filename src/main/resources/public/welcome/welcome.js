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
            // Clear credentials upon return to login screen
            AuthenticationService.ClearCredentials();
        })();

        function login() 
        {
            //Prevent multiple submissions
            vm.dataLoading = true;
            
            AuthenticationService.Login(function (response) 
            {
                if (response.authenticated) 
                {
                    AuthenticationService.SetCredentials(vm.username, vm.password);
                    $location.path('/');
                } 
                else
                {
                    ErrorService.Error(response);
                    vm.dataLoading = false;
                }
            });
        }
    }
})();