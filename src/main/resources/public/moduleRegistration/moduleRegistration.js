

    angular
        .module('ParasolApp', [])
        .controller('ModuleRegistrationController', function($scope, $http){

            $scope.url = null;
            $scope.moduleName = null;
            $scope.dataLoading = false;


            $scope.postdata = function (url, moduleName) {
                    var data = {

                    url: url,

                    ModuleName: moduleName

                   

                };

                // assign true to dataLoading variable
                dataLoading = true;
            
                console.log(url, moduleName, dataLoading);
            //Call the services

            $http.post('moduleRegistration', JSON.stringify(data)).then(function (response) {

                if (response.data)

                    $scope.msg = "Post Data Submitted Successfully!";

                }, function (response) {

                    $scope.msg = "Service not Exists";

                    $scope.statusval = response.status;

                    $scope.statustext = response.statusText;

                    $scope.headers = response.headers();

            });

    };

});



        
        
       
     
    
