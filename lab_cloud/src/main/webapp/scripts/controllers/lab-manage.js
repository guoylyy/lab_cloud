'use strict';

/**
 * @ngdoc function
 * @name prjApp.controller:LabManageCtrl
 * @description
 * # LabManageCtrl
 * Controller of the prjApp
 */
angular.module('prjApp')
  .controller('LabManageCtrl', function($scope, dialogs, SystemManageService, LoginService, $location) {
    //TODO: place it to aop layer
    if (LoginService.getLoginUser() == false) {
      $location.path('/login');
    }
    $scope.tabHref = function(path) {
      $location.path(path);
    };

    /*
      Lab 
    */
    $scope.currentLabList = [];
    loadLabs();

    function loadLabs() {
      var promise = SystemManageService.loadLab(1);
      promise.then(
        function(data) {
          $scope.currentLabList = data.data;
        },
        function(data) {
          alert('获取实验室列表失败！');
        }
      );
    };

    $scope.addLab = function() {
      var dialog = dialogs.create('template/lab-add-lab-dialog.html', 'AddLabCtrl', '', {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function(data) {
        var promise = SystemManageService.addLab({
          "token": "",
          data: data
        });
        promise.then(
          function(data) {
            alert('添加实验室成功！');

          },
          function(data) {
            alert('添加实验室错误！');
          }
        );
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
        var promise = SystemManageService.updateLab(data.id, {
          "token": "",
          "data": data
        });
        promise.then(
          function(data) {
            alert("更新实验室成功！");
          },
          function(data) {
            alert('更新实验室失败！');
          }
        );
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
        var promise = SystemManageService.removeLab(lab.id);
        promise.then(
          function(data) {
            alert('删除成功');
            $scope.currentLabList.splice($scope.currentLabList.indexOf(lab), 1);
          },
          function(data) {
            alert('删除失败！');
          }
        );
      }, function() {});
    };


    /*
      Experiment Management
    */
    $scope.currentExperimentList = SystemManageService.loadExperiment();
    loadExperiments();

    function loadExperiments() {
      var promise = SystemManageService.loadExperiment(1);
      promise.then(
        function(data) {
          $scope.currentExperimentList = data.data;
        },
        function(data) {
          alert('获取实验列表失败！');
        }
      );
    };

    $scope.addExperiment = function() {
      var dialog = dialogs.create('template/lab-add-experiment-dialog.html', 'AddLabCtrl', '', {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function(exp) {
        var promise = SystemManageService.addExperiment({
          "token": '',
          "data": exp
        });
        promise.then(
          function(data) {
            $scope.currentExperimentList.push(exp);
          },
          function(data) {
            alert('添加实验失败！');
          }
        );
      });
    };

    $scope.editExperimentDetails = function(exp) {
      var dialog = dialogs.create('template/lab-experiment-info-dialog.html', 'EditLabCtrl', exp, {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function(data) {
        var promise = SystemManageService.updateExperiment(data.id, {
          "token": "",
          "data": data
        });
        promise.then(
          function(data) {
            alert("更新实验成功");
          },
          function(data) {
            alert('更新实验失败！');
          }
        );
      });
    };

    $scope.removeExperiment = function(exp) {
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
        var promise = SystemManageService.removeExperiment(exp.id);
        promise.then(
          function(data) {
            alert("移除实验成功");
            $scope.currentExperimentList.splice($scope.currentExperimentList.indexOf(exp), 1);
          },
          function(data) {
            alert('移除实验失败！');
          }
        );

      }, function() {});
    };


    /*
      Course Management 
    */
    $scope.currentCourseList = SystemManageService.loadCourse();
    loadCourses();

    function loadCourses() {
      var promise = SystemManageService.loadCourse(1);
      promise.then(
        function(data) {
          $scope.currentCourseList = data.data;
        },
        function(data) {
          alert('获取课程列表失败！');
        }
      );
    };

    $scope.addCourse = function() {
      var dialog = dialogs.create('template/lab-add-course-dialog.html', 'AddLabCtrl', '', {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function(course) {
        var promise = SystemManageService.addCourse({"token":"", "data":course});
        promise.then(
          function(data) {
            alert('添加课程成功！');
            $scope.currentCourseList.push(data);
          },
          function(data) {
            alert('添加课程失败！');
          }
        );
        
      });
    };

    $scope.editCourseDetails = function(course) {
      var dialog = dialogs.create('template/lab-course-info-dialog.html', 'EditLabCtrl', course, {
        size: 'md',
        keyboard: true,
        backdrop: 'static',
        windowClass: 'model-overlay'
      });

      dialog.result.then(function(data) {
        var promise = SystemManageService.updateCourse(data.id, {"token":"", "data":data});
        promise.then(
          function(data) {
            alert('更新课程成功！');
          },
          function(data) {
            alert('更新课程失败！');
          }
        );
      });
    };

    $scope.removeCourse = function(course) {
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
        var promise = SystemManageService.removeCourse(course.id);
        promise.then(
          function(data) {
            alert('移除课程成功！');
            $scope.currentExperimentList.splice($scope.currentCourseList.indexOf(course), 1);
          },
          function(data) {
            alert('移除课程失败！');
          }
        );
        
      }, function() {});
    };

  }).controller('AddLabCtrl', function($scope, $modalInstance, data, DictService) {
    $scope.data = {};
    $scope.labStatus = DictService.getLabStatusDict();
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
  }).controller('EditLabCtrl', function($scope, $modalInstance, data, DictService) {
    $scope.data = data;
    $scope.labStatus = DictService.getLabStatusDict();
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