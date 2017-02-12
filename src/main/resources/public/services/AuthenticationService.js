(function () {
    'use strict';

    angular
        .module('ParasolApp')
        .factory('AuthenticationService', AuthenticationService);

    AuthenticationService.$inject = ['$http', '$cookies', '$rootScope', '$timeout', 'UserService'];
    function AuthenticationService($http, $cookies, $rootScope, $timeout, UserService) {
        var service = {};

        service.Login = Login;
        service.SetCredentials = SetCredentials;
        service.ClearCredentials = ClearCredentials;

        return service;

        function Login(callback) 
        {          
            $window.location.href = '/auth/login';
            $http.get('auth/login')
                 .then(function (response) 
                 {
                	//console.log("Response was good.");
                	//console.log(response);
                    callback(response);
                 },
                 function errorCallback(response){
                	 //console.log("Response was bad.");
                 	//console.log(response);
                     var eResponse = {success: false, 
                     message: 'Login unsuccessful. Returned status ' + response.status};
                     callback(eResponse);
                 });
        }

        function SetCredentials(token) 
        {
            // set default auth header for http requests
            $http.defaults.headers.common['Authorization'] = 'Bearer ' + token;
        }

        function ClearCredentials() {
            $rootScope.globals = {};
            $cookies.remove('globals');
            $http.defaults.headers.common.Authorization = 'Basic';
        }
    }
})();