'use strict';

/**
 * @ngdoc function
 * @name prjApp.controller:ProfileCtrl
 * @description
 * # ProfileCtrl
 * Controller of the prjApp
 */
angular.module('prjApp')
  .controller('ProfileCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
