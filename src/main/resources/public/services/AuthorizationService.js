(function () {
    'use strict';

    angular
        .module('ParasolApp')
        .controller('AuthorizationController', AuthorizationController);

    AuthorizationController.$inject = ['$location', '$rootScope', 'AuthenticationService', 'ErrorService'];
    function AuthorizationController($location, $rootScope, AuthenticationService, ErrorService) 
    {
        var vm = this;

        vm.authorize = authorize;

//        (function initController() {
//            // Clear credentials upon return to login screen
//            //**This may not be needed but is left here for historical context**
//        	//AuthenticationService.ClearCredentials();
//        })();

        function authorize() 
        {	
            AuthenticationService.SetCredentials($location.search());
            $rootScope.authorizing = false;
            location.path($rootScope.requestedPath);
        }
    }
})();