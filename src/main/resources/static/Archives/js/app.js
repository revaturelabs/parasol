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
                controller: 'WelcomeController',
                templateUrl: 'welcome/welcome.html',
                controllerAs: 'vm'
            })
            .when('/moduleRegistration', {
                controller: 'ModuleRegistrationController',
                templateUrl: 'moduleRegistration/moduleRegistration.html',
                controllerAs: 'vm'
            })

            .otherwise({ redirectTo: '/welcome' });
        //$locationProvider.html5Mode(true);
    }

    run.$inject = ['$rootScope', '$window', '$location', '$timeout', '$http'];
    function run($rootScope, $window, $location, $timeout, $http) 
    {
        $rootScope.$on('$locationChangeStart', function (event, next, current) 
        {
            // redirect to login page if not logged in and trying to access a restricted page
            $rootScope.showLogout = false;
            var restrictedPage = $.inArray($location.path(), ['/welcome']) === -1;
            if (restrictedPage && !$rootScope.authenticated)
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
        
        $rootScope.$on('$viewContentLoaded', function(event, next, current)
        	{
                $rootScope.largeContent = false;
                $timeout(function(){
                     if($(".jumbotron")[0].clientHeight/window.innerHeight > 0.8)
                     {
                         $rootScope.largeContent = true;
                     }
                     else
                     {
                         $rootScope.largeContent = false;
                     }
                }, 0);                    
        });
        
        angular.element($window).bind('resize', function(){
            $rootScope.largeContent = false;
            $timeout(function(){
                 if($(".jumbotron")[0].clientHeight/window.innerHeight > 0.8)
                 {
                     $rootScope.largeContent = true;
                 }
                 else
                 {
                     $rootScope.largeContent = false;
                 }
            }, 0);
        });
    }

})();