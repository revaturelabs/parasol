(function () {
    'use strict';

    angular
        .module('ParasolApp')
        .controller('WelcomeController', WelcomeController);

    WelcomeController.$inject = ['$location', '$window', '$http', 'AuthenticationService', 'ErrorService'];
    function WelcomeController($location, $window, $http, AuthenticationService, ErrorService) 
    {
        var vm = this;

        vm.login = login;

        (function initController() {
            	//AuthenticationService.ClearCredentials();
	       	 $http.get('/rolesandmodules').then(function(response){
	          	if(response){
	          		$rootScope.authenticated = true;
	          		$rootScope.moduleResponse = response.userAuthentication.details.modules;
	          		$location.path('/');
	          	} 
	          	else{
	          		$rootScope.authenticated = false;	        		    
	          	}
	          }).error(function(){
	          	$rootScope.authenticated = false;
	          });  
	    })();

        function login() 
        {
            //Prevent multiple submissions
            vm.dataLoading = true;
            
            AuthenticationService.Login();
        }
    }
})();