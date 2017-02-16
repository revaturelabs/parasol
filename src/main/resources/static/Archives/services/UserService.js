(function () {
    'use strict';

    angular
        .module('ParasolApp')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) 
    {
        var service = {};

        // service.GetByUsername = GetByUsername;
        // service.Create = Create;
        // service.Update = Update;
        // service.Delete = Delete;

        return service;

        //Examples of how to consume REST services
        // function GetByUsername(username) {
        //     return $http.get('/api/users/' + username).then(handleSuccess, handleError('Error getting user by username'));
        // }

        // function Create(user) {
        //     return $http.post('/api/users', user).then(handleSuccess, handleError('Error creating user'));
        // }

        // function Update(user) {
        //     return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        // }

        // function Delete(id) {
        //     return $http.delete('/api/users/' + id).then(handleSuccess, handleError('Error deleting user'));
        // }

        // private functions

        function handleSuccess(res) 
        {
            return res.data;
        }

        function handleError(error) 
        {
            return function () {
                return { success: false, message: error };
            };
        }
    }
})();