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

        $scope.passItem = {
            originPass:'',
            newPass:'',
            retypeNewPass:''
        };

        $scope.userInfo = {
        };

        $scope.editProfile = function(){
            var dialog = dialogs.create('template/lab-user-info-dialog.html','EditProfileCtrl',
                $scope.passItem,{size:'md',keyboard: true,backdrop: 'static',windowClass: 'model-overlay'});
        };

        $scope.resetPasswrod = function(){
            var dialog = dialogs.create('template/lab-reset-password-dialog.html','ResetPasswordCtrl',
                $scope.passItem,{
                    size: 'md',
                    keyboard: true,
                    backdrop: 'static',
                    windowClass: 'model-overlay'
                });
            dialog.result.then(function(data) {
                alert(data);
            });
        };
  }).controller('EditProfileCtrl',function($scope,$modalInstance,data){
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
  }).controller('ResetPasswordCtrl',function($log,$scope,$modalInstance,data){

        $scope.data = data;
        $scope.canSave = false;
        $scope.cancel = function(){
            $modalInstance.dismiss('canceled');
        }; // end cancel
        $scope.$watch('data.retypeNewPass', function(val,old){
            if(val != $scope.data.newPass){
                $scope.message = "两次输入的密码不一致";
            }else{
                clearMessage();
            }
        });
        $scope.$watch('data.newPass', function(val,old){
            if($scope.data.retypeNewPass.length >0 && val != $scope.data.retypeNewPass){
                $scope.message = "两次输入的密码不一致";
            }else{
                clearMessage();
            }
        });
        function clearMessage(){
            $scope.message = "";
        };
        $scope.save = function(){
            //alert($scope.data.newPass);
            if($scope.message.lenth == 0){
                $scope.canSave = true;
            }
            $modalInstance.close($scope.data);
        }; // end save

        $scope.hitEnter = function(evt){
            if(angular.equals(evt.keyCode,13) && !(angular.equals($scope.name,null) || angular.equals($scope.name,'')))
                $scope.save();
        }; // end hitEnter
  });
