            function postdata 
                (url, moduleName, checkedData ) {
						console.log("called postdata");
                    	var data = {

                    	url: url,

                    	moduleName: moduleName,

						checkedData: checkedData

						


                		};
						console.log(url);
						console.log(moduleName);
						console.log(checkedData);
                	dataLoading = true;
					
					$http({
						method : 'POST',
						data: data,
						url : 'moduleRegistration'
					}).then(function(response) {
					if (response)

                    		self.msg = "Post Data Submitted Successfully!";

                		}, function (response) {

                    		self.msg = "Service not Exists";

                    		self.statusval = response.status;

                    		self.statustext = response.statusText;

                    		self.headers = response.headers();

            			});
                	
    				};