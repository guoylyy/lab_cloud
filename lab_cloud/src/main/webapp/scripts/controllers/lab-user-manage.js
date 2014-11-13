'use strict';

/**
 * @ngdoc function
 * @name prjApp.controller:LabUserManageCtrl
 * @description
 * # LabUserManageCtrl
 * Controller of the prjApp
 */
angular.module('prjApp')
    .controller('LabUserManageCtrl', function($scope, dialogs, SystemManageService, $location) {

        /*
          User Management
        */
        $scope.currentUserList = SystemManageService.loadUser('fdsaf', 1);
        $scope.currentAdminList = [];
        $scope.currentStudentList = [];
        $scope.currentTeacherList = [];

        $scope.removeUser = function(user){
            var dialog = dialogs.create('template/lab-confirm-dialog.html', 'ConfirmCtrl', {
                text: '你确定要移除这个用户吗？',
                cancelBtnText: '取消',
                confirmBtnText: '确认'
            }, {
                size: 'sm',
                keyboard: true,
                backdrop: true,
                windowClass: 'model-overlay'
            });
            dialog.result.then(function(data){
                
                $scope.currentUserList.splice($scope.currentUserList.indexOf(user),1);
            },function(){
            });
        };

        $scope.addUser = function() {
            var dialog = dialogs.create('template/lab-add-user-dialog.html', 'addUserCtrl', '', {
                size: 'md',
                keyboard: true,
                backdrop: 'static',
                windowClass: 'model-overlay'
            });

            dialog.result.then(function(data) {
                $scope.currentUserList.push(data);
            });
        };
        $scope.userDetails = function(uid) {
            var dialog = dialogs.create('template/lab-add-user-dialog.html', 'userEditCtrl', {
                item: ''
            }, {
                size: 'md',
                keyboard: true,
                backdrop: 'static',
                windowClass: 'model-overlay'
            });
        };

        
        //TO-DO: Make sure redirect to right way
        $scope.tabHref = function(path) {
            $location.path(path);
        };

    }).controller('addUserCtrl', function($log, $scope, $modalInstance, data) {
        $scope.userRoles = [
            {
                name: "学生",
                value: 1
            },
            {
                name: "管理员",
                value: 3
            }, {
                name: "教师",
                value: 2
            } 
        ];

        $scope.data = {
            accountName: '',
            accountPassword: '',
            accountNumber: '',
            role: '',
            desc: ''
        };
        $scope.aa = 'sdfasf';
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


    }).controller('ConfirmCtrl', function ($scope,$modalInstance,data) {
        $scope.data = data;
        if($scope.data.cancelBtnText == undefined){
            $scope.data.cancelBtnText = "取消";
        }
        if($scope.data.confirmBtnText == undefined){
            $scope.data.confirmBtnText = "确定";
        }
        $scope.cancel = function(){
          $modalInstance.dismiss('Canceled');
        };
        $scope.confirm = function(){
          $modalInstance.close();
        };
   });