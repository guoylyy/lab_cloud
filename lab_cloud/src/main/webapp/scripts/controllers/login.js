'use strict';

/**
 * @ngdoc function
 * @name srcApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the srcApp
 */
angular.module('prjApp')
  .controller('LoginCtrl', function ($scope, $rootScope, $location) {
        $scope.userName = '';
        $scope.userPWD = '';
        $scope.isKeepLogin = true;
  });
