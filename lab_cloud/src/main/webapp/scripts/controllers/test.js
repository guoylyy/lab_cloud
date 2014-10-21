'use strict';

/**
 * @ngdoc function
 * @name srcApp.controller:TestCtrl
 * @description
 * # TestCtrl
 * Controller of the srcApp
 */
angular.module('srcApp')
  .controller('TestCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
