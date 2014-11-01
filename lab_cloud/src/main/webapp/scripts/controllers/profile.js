'use strict';

/**
 * @ngdoc function
 * @name prjApp.controller:ProfileCtrl
 * @description
 * # ProfileCtrl
 * Controller of the prjApp
 */
angular.module('prjApp')
  .controller('ProfileCtrl', function ($scope, dialogs) {
        $scope.editProfile = function(){
            var dialog = dialogs.create('template/lab-user-info-dialog.html','whatsYourNameCtrl',
                {item:''},{size:'md',keyboard: true,backdrop: 'static',windowClass: 'model-overlay'});
        };

        $scope.resetPasswrod = function(){
            var dialog = dialogs.create('template/lab-reset-password-dialog.html','whatsYourNameCtrl',
                {item:''},{size:'md',keyboard: true,backdrop: 'static',windowClass: 'model-overlay'});
        };
  }).controller('whatsYourNameCtrl',function($scope,$modalInstance,data){
        $scope.user = {name : ''};
        $scope.cancel = function(){
            $modalInstance.dismiss('canceled');
        }; // end cancel

        $scope.save = function(){
            $modalInstance.close($scope.user.name);
        }; // end save

        $scope.hitEnter = function(evt){
            if(angular.equals(evt.keyCode,13) && !(angular.equals($scope.name,null) || angular.equals($scope.name,'')))
                $scope.save();
        }; // end hitEnter
    });
