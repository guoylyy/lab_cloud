'use strict';

/**
 * @ngdoc function
 * @name prjApp.controller:LabManageCtrl
 * @description
 * # LabManageCtrl
 * Controller of the prjApp
 */
angular.module('prjApp')
  .controller('LabManageCtrl', function($scope, dialogs, SystemManageService, $location) {
    $scope.tabHref = function(path) {
      $location.path(path);
    };

    /*
      Lab 
    */
    $scope.currentLabList = SystemManageService.loadLab(1);
    $scope.addLab = function() {

    };

    $scope.editLabDetails = function(lid) {

    };

    $scope.removeLab = function(lid) {

    };


    /*
      Experiment Management
    */
    $scope.addExperiment = function() {

    };

    $scope.editExperimentDetails = function(lid) {

    };

    $scope.removeExperiment = function(lid) {

    };


    /*
      Course Management 
    */
    $scope.addCourse = function() {

    };

    $scope.editCourseDetails = function(lid) {

    };

    $scope.removeCourse = function(lid) {

    };

  }).controller('whatsYourNameCtrl', function($scope, $modalInstance, data) {
    $scope.user = {
      name: ''
    };
    $scope.cancel = function() {
      $modalInstance.dismiss('canceled');
    }; // end cancel

    $scope.save = function() {
      $modalInstance.close($scope.user.name);
    }; // end save

    $scope.hitEnter = function(evt) {
      if (angular.equals(evt.keyCode, 13) && !(angular.equals($scope.name, null) || angular.equals($scope.name, '')))
        $scope.save();
    }; // end hitEnter
  });