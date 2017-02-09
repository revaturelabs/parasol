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
            $window.location.href = '/login';
//            AuthenticationService.Login(function (response) 
//            {
//                if (response.success) 
//                {
//                    AuthenticationService.SetCredentials(vm.username, vm.password);
//                    $location.path('/');
//                } 
//                else
//                {
//                    ErrorService.Error(response.message);
//                    vm.dataLoading = false;
//                }
//            });
        }
    }
})();