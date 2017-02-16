(function () {
    'use strict';

    angular
        .module('ParasolApp', [])
        .controller('ModuleRegistrationController', ModuleRegistrationController);
     // injections go here
    ModuleRegistrationController.$inject = ['$location'];
    function ModuleRegistrationController($location) 
    {
        var vm = this;

        

        (function initController() {
            // anything I need controller to do on load
            
        })();

        function registerModule() 
        {
            //Prevent multiple submissions
            vm.dataLoading = true;
            AuthenticationService.Login(function (response) 
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

