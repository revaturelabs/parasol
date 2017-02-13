(function () {
    'use strict';

    angular
        .module('ParasolApp')
        .controller('LandingController', LandingController);

    LandingController.$inject = ['UserService', '$rootScope', '$http'];
    function LandingController(UserService, $rootScope, $http) {
        var vm = this;

        vm.buttons = buttonContent;
        vm.user = null;
        vm.modules = [{name:'first',url:'www.google.com'},{name:'second',url:'www.bing.com'},{name:'third',url:'www.random.org'},{name:'fourth',url:'www.elgoog.com'}];
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
            $http.get('/rolesandmodules')
                .then(function (response) {
                    vm.modules = response;
                },
                function errorCallback(response){
                     var eResponse = {success: false, 
                     message: 'Login unsuccessful. Returned status ' + response.status};
               });
        }
    }
})();