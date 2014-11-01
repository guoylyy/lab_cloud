'use strict';

/**
 * @ngdoc function
 * @name prjApp.controller:MyExperimentCtrl
 * @description
 * # MyExperimentCtrl
 * Controller of the prjApp
 */
angular.module('prjApp')
  .controller('MyExperimentCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
