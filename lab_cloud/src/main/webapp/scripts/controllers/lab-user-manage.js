'use strict';

/**
 * @ngdoc function
 * @name prjApp.controller:LabUserManageCtrl
 * @description
 * # LabUserManageCtrl
 * Controller of the prjApp
 */
angular.module('prjApp')
    .controller('LabUserManageCtrl', function($scope, dialogs, SystemManageService, LoginService, Generalservice, $location) {

        //TODO: place it to aop layer
        if (LoginService.getLoginUser() == false) {
            $location.path('/login');
        };
        /*
          User Management
        */
        $scope.currentUserList = [];
        $scope.currentAdminList = [];
        $scope.currentStudentList = [];
        $scope.currentTeacherList = [];


        loadUser();

        function loadUser() {
            var promise = SystemManageService.loadUser('fdsaf', 1);
            promise.then(
                function(data) {
                    $scope.currentUserList = data.data;
                },
                function(data) {
                    alert(data.errorCode);
                });
        };

        $scope.removeUser = function(user) {
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
            dialog.result.then(function(data) {
                var promise = SystemManageService.removeUser(user.id);
                promise.then(
                    function(data) {
                        $scope.currentUserList.splice($scope.currentUserList.indexOf(user), 1);
                    },
                    function(data) {
                        alert('Fail');
                    });


            }, function() {});
        };

        $scope.addUser = function() {
            var dialog = dialogs.create('template/lab-add-user-dialog.html', 'addUserCtrl', '', {
                size: 'md',
                keyboard: true,
                backdrop: 'static',
                windowClass: 'model-overlay'
            });

            dialog.result.then(function(data) {

                var promise = SystemManageService.addUser({
                    "token": "",
                    "data": data
                });
                promise.then(function(data) {
                        alert('添加用户成功!');
                        $scope.currentUserList.push(data);
                    },
                    function(data) {
                        alert('Error Happen!');
                    });

            });
        };
        $scope.userDetails = function(user) {
            var dialog = dialogs.create('template/lab-user-info-dialog.html', 'UserEditCtrl', user, {
                size: 'md',
                keyboard: true,
                backdrop: 'static',
                windowClass: 'model-overlay'
            });

            dialog.result.then(function(data) {
                var promise = SystemManageService.updateUser(data.id, {
                    "data": data,
                    "token": ""
                });
                promise.then(
                    function(data) {
                        alert('更新用户信息成功！');
                    },
                    function(data) {
                        alert('内部错误，更新失败！');
                    }
                );
                console.log(data);
            });
        };

        $scope.tabHref = function(path) {
            $location.path(path);
        };

    }).controller('addUserCtrl', function($log, $scope, $modalInstance, data, DictService) {
        $scope.userRoles = DictService.getRoleDict();

        $scope.data = {
            accountName: '',
            accountPassword: '',
            accountNumber: '',
            accountEmail: '',
            accountCharacter: 'STUDENT'
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

    }).controller('ConfirmCtrl', function($scope, $modalInstance, data) {
        $scope.data = data;
        if ($scope.data.cancelBtnText == undefined) {
            $scope.data.cancelBtnText = "取消";
        }
        if ($scope.data.confirmBtnText == undefined) {
            $scope.data.confirmBtnText = "确定";
        }
        $scope.cancel = function() {
            $modalInstance.dismiss('Canceled');
        };
        $scope.confirm = function() {
            $modalInstance.close();
        };
    }).controller('UserEditCtrl', function($scope, $modalInstance, data, DictService) {
        $scope.data = data;
        $scope.userRoles = DictService.getRoleDict();

        $scope.cancel = function() {
            $modalInstance.dismiss('Canceled');
        };
        $scope.save = function() {
            $modalInstance.close($scope.data);
        };
    });