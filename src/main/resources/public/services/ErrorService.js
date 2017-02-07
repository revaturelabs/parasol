(function () {
    'use strict';

    angular
        .module('ParasolApp')
        .factory('ErrorService', ErrorService);

    ErrorService.$inject = ['$rootScope'];
    function ErrorService($rootScope) {
        var service = {};

        service.Success = Success;
        service.Error = Error;

        initService();

        return service;

        function initService() 
        {
            $rootScope.$on('$locationChangeStart', function () 
            {
                clearErrorMessage();
            });

            function clearErrorMessage() 
            {
                //Only clear if there is an error
                var error = $rootScope.error;
                if (error) 
                {
                    //Delete after one change
                    if (!error.keepAfterLocationChange) 
                    {
                        delete $rootScope.error;
                    } 
                    else 
                    {
                        // only keep for a single location change
                        error.keepAfterLocationChange = false;
                    }
                }
            }
        }

        function Success(message, keepAfterLocationChange) 
        {
            $rootScope.error = 
            {
                message: message,
                type: 'success', 
                keepAfterLocationChange: keepAfterLocationChange
            };
        }

        function Error(message, keepAfterLocationChange) 
        {
            $rootScope.error = 
            {
                message: message,
                type: 'error',
                keepAfterLocationChange: keepAfterLocationChange
            };
        }
    }

})();