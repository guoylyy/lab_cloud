'use strict';

/**
 * @ngdoc function
 * @name prjApp.controller:MyClassCtrl
 * @description
 * # MyClassCtrl
 * Controller of the prjApp
 */
angular.module('prjApp')
  .controller('MyClassCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
