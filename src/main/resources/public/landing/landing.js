(function () {
    'use strict';

    angular
        .module('ParasolApp')
        .controller('LandingController', LandingController);

    LandingController.$inject = ['UserService', '$rootScope'];
    function LandingController(UserService, $rootScope) {
        var vm = this;

        vm.buttons = buttonContent;
        vm.user = null;
        vm.modules = ['first','second','third','fourth'];

        initController();

        function initController()
        {
            getUser();
            getModules();
        }

        function buttonContent(tab)
        {
        	$("li").each(function(){        		
        		$(this).removeClass("active");
        		if($(this) == tab)
        		{
        			$(this).addClass("active");
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
        }
    }
})();