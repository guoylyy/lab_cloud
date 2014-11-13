'use strict';

/**
 * @ngdoc service
 * @name prjApp.DictService
 * @description
 * # DictService
 * Service in the prjApp.
 */
angular.module('prjApp')
  .service('DictService', function DictService() {
    // AngularJS will instantiate a singleton by calling "new" on this function

    this.getRoleDict = function(){
    	var userRoles = [
            {
                name: "学生",
                id: 1
            },
            {
                name: "管理员",
                id: 3
            }, {
                name: "教师",
                id: 2
            } 
        ];
        return userRoles;
    };


  });
