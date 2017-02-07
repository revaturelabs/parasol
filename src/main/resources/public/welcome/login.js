(function () {
    'use strict';

    angular
        .module('ParasolApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService', 'ErrorService'];
    function LoginController($location, AuthenticationService, ErrorService) 
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
            AuthenticationService.Login(vm.username, vm.password, function (response) 
            {
                if (response.success) 
                {
                    AuthenticationService.SetCredentials(vm.username, vm.password);
                    $location.path('/');
                } 
                else
                {
                    ErrorService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        }
    }
})();