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
            $http.get('/login')
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

        function SetCredentials(username, password) 
        {
            $rootScope.globals = {
                currentUser: {
                    username: username,
                    password: password
                }
            };

            // set default auth header for http requests
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser;

            // store user details in globals cookie that keeps user logged in for 1 day (or until they logout)
            var cookieExp = new Date();
            cookieExp.setDate(cookieExp.getDate() + 1);
            $cookies.putObject('globals', $rootScope.globals, { expires: cookieExp });
        }

        function ClearCredentials() {
            $rootScope.globals = {};
            $cookies.remove('globals');
            $http.defaults.headers.common.Authorization = 'Basic';
        }
    }
})();