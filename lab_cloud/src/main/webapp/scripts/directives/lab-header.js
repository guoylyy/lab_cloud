'use strict';

/**
 * @ngdoc directive
 * @name prjApp.directive:labHeader
 * @description
 * # labHeader
 */
angular.module('prjApp')
  .directive('labHeader', function () {
    return {
      templateUrl: 'template/lab-header.html',
      restrict: 'E',
      replace: true,
      link: function postLink(scope, element, attrs) {

      }
    };
  });

