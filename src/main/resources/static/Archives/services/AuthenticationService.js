(function () {
    'use strict';

    angular
        .module('ParasolApp')
        .factory('AuthenticationService', AuthenticationService);

    AuthenticationService.$inject = ['$http', '$cookies', '$rootScope', '$timeout', '$window', 'UserService'];
    function AuthenticationService($http, $cookies, $rootScope, $timeout, $window, UserService) {
        var service = {};

        service.Login = Login;
        service.SetCredentials = SetCredentials;
        service.ClearCredentials = ClearCredentials;

        return service;

        function Login() 
        {          
            $window.location.href = '/auth/login';
        }

        function ClearCredentials() {
            $rootScope.globals = {};
            $rootScope.authenticated = false;
            $cookies.remove('globals');
            $http.defaults.headers.common.Authorization = 'Basic';
        }
    }
})();