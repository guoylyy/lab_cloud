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
      var dialog = dialogs.create('template/lab-add-lab-dialog.html', 'AddLabCtrl', '', {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function(data) {
        $scope.currentLabList.push(data);
      });
    };

    $scope.editLab = function(lab) {
      var dialog = dialogs.create('template/lab-lab-info-dialog.html', 'EditLabCtrl', lab, {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function(data) {
      
      });
    };

    $scope.removeLab = function(lab) {
      var dialog = dialogs.create('template/lab-confirm-dialog.html', 'ConfirmCtrl', {
        text: '你确定要移除这个实验室吗？',
        cancelBtnText: '取消',
        confirmBtnText: '确认'
      }, {
        size: 'sm',
        keyboard: true,
        backdrop: true,
        windowClass: 'model-overlay'
      });
      dialog.result.then(function(data) {
        $scope.currentLabList.splice($scope.currentLabList.indexOf(lab),1);
      }, function() {});
    };


    /*
      Experiment Management
    */
    $scope.currentExperimentList = SystemManageService.loadExperiment();
    $scope.addExperiment = function() {
      var dialog = dialogs.create('template/lab-add-experiment-dialog.html', 'AddLabCtrl', '', {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });
      
      dialog.result.then(function(data) {
        $scope.currentExperimentList.push(data);
      });
    };

    $scope.editExperimentDetails = function(lid) {
      var dialog = dialogs.create('template/lab-experiment-info-dialog.html', 'EditLabCtrl', lab, {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function(data) {
      
      });
    };

    $scope.removeExperiment = function(lid) {
       var dialog = dialogs.create('template/lab-confirm-dialog.html', 'ConfirmCtrl', {
        text: '你确定要移除这个实验室吗？',
        cancelBtnText: '取消',
        confirmBtnText: '确认'
      }, {
        size: 'sm',
        keyboard: true,
        backdrop: true,
        windowClass: 'model-overlay'
      });
      dialog.result.then(function(data) {
        $scope.currentExperimentList.splice($scope.currentExperimentList.indexOf(lab),1);
      }, function() {});
    };


    /*
      Course Management 
    */
    $scope.currentCourseList = SystemManageService.loadCourse();
    $scope.addCourse = function() {
       var dialog = dialogs.create('template/lab-add-course-dialog.html', 'AddLabCtrl', '', {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function(data) {
        $scope.currentExperimentList.push(data);
      });
    };

    $scope.editCourseDetails = function(lid) {
      var dialog = dialogs.create('template/lab-course-info-dialog.html', 'EditLabCtrl', lab, {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function(data) {
      
      });
    };

    $scope.removeCourse = function(lid) {
      var dialog = dialogs.create('template/lab-confirm-dialog.html', 'ConfirmCtrl', {
        text: '你确定要移除这个实验室吗？',
        cancelBtnText: '取消',
        confirmBtnText: '确认'
      }, {
        size: 'sm',
        keyboard: true,
        backdrop: true,
        windowClass: 'model-overlay'
      });
      dialog.result.then(function(data) {
        $scope.currentExperimentList.splice($scope.currentExperimentList.indexOf(lab),1);
      }, function() {});
    };

  }).controller('AddLabCtrl', function($scope, $modalInstance, data) {
    $scope.data = {
      
    };
    $scope.cancel = function() {
      $modalInstance.dismiss('canceled');
    }; // end cancel

    $scope.save = function() {
      $modalInstance.close($scope.data);
    }; // end save

    $scope.hitEnter = function(evt) {
      if (angular.equals(evt.keyCode, 13) && !(angular.equals($scope.name, null) || angular.equals($scope.name, '')))
        $scope.save();
    }; // end hitEnter
  }).controller('EditLabCtrl', function($scope, $modalInstance, data) {
    $scope.data = data;
    $scope.cancel = function() {
      $modalInstance.dismiss('canceled');
    }; // end cancel

    $scope.save = function() {
      $modalInstance.close($scope.data);
    }; // end save

    $scope.hitEnter = function(evt) {
      if (angular.equals(evt.keyCode, 13) && !(angular.equals($scope.name, null) || angular.equals($scope.name, '')))
        $scope.save();
    }; // end hitEnter
  });