(function () {
    'use strict';

    angular
        .module('ParasolApp')
        .controller('LandingController', LandingController);

    LandingController.$inject = ['UserService', 'ErrorService', '$rootScope', '$http'];
    function LandingController(UserService, ErrorService, $rootScope, $http) {
        var vm = this;

        vm.buttons = buttonContent;
        vm.user = null;
        vm.modules = [{name:'Assign Force',url:'redirect:www.google.com'},{name:'Housing App',url:'www.bing.com'},{name:'Rideshare App',url:'www.random.org'},{name:'Bootcamp Manager',url:'www.elgoog.com'}];
        vm.hover = null;
        
        initController();

        function initController()
        {
            //getUser();
            getModules();
        }

        function buttonContent(tab)
        {
        	$("li.ng-scope").each(function(){
        		$(this).removeClass("active");
        		if($(this).attr('id') == tab.name)
        		{
        			$(this).addClass("active");
        			//window.location.href=tab.url;        			
        		}
        	});
        }
        
        function getUser()
        {
            //Get the user from the server
            //This may need to be altered to deleted based on style choice
        }

        function getModules()
        {
            //Get the list of all modules the user has access to from the server
            //This might already have been given to us in the header
        	$http.get('/rolesandmodules').then(function(response){
	          	if(response){
	          		vm.modules = response.userAuthentication.details.modules;
	          	} 
	          }).error(function(response){
	          	vm.modules = response;
	          });  

        }
    }
})();