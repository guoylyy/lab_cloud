'use strict';

/**
 * @ngdoc function
 * @name srcApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the srcApp
 */
angular.module('prjApp')
  .controller('LoginCtrl', function ($scope, $rootScope, $location, LoginService) {
        $scope.accountNumber= '';
        $scope.accountPassword = '';
        $scope.isKeepLogin = true;
        $scope.message = '';
        
        $scope.user = LoginService.getLoginUser();

        $scope.login = function(){
        	console.log('login...');
        	if($scope.accountNumber.length > 0 &&  $scope.accountPassword.length >0){
        		var promise = LoginService.login($scope.accountNumber, $scope.accountPassword);
        		promise.then(function(data){
        			LoginService.persitentLogin(data.data, data.token);
        			$location.path('/');
        		},function(data){
        			$scope.message = data.errorCode;
        		});
        	}else{
        		$scope.message = "账号和密码不能为空";
        	}
        };


  }); 
