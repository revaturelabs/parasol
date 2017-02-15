(function () {
    'use strict';

    angular
        .module('ParasolApp', ['ngRoute', 'ngCookies'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) 
    {
        $routeProvider
            .when('/', {
                controller: 'LandingController',
                templateUrl: 'landing/landing.html',
                controllerAs: 'vm'
            })

            .when('/welcome', {
                controller: 'LoginController',
                templateUrl: 'welcome/login.html',
                controllerAs: 'vm'
            })

            .otherwise({ redirectTo: '/welcome' });
    }

    run.$inject = ['$rootScope', '$location', '$cookies', '$http'];
    function run($rootScope, $location, $cookies, $http) 
    {
        // keep user logged in after page refresh
        $rootScope.globals = $cookies.getObject('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) 
        {
            // redirect to login page if not logged in and trying to access a restricted page
            $rootScope.showLogout = false;
            var restrictedPage = $.inArray($location.path(), ['/welcome']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn)
            {
                $location.path('/welcome');
            }
            if($location.path() == "/welcome")
            {
                $rootScope.showLogout = false;
            }
            else
            {
                $rootScope.showLogout = true;
            }
        });
    }

})();