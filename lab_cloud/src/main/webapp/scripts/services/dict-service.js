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

    this.getRoleDict = function(){
    	var userRoles = [
            {
                name: "学生",
                id: 'STUDENT'
            },
            {
                name: "管理员",
                id: 'ADMINISTRATOR'
            }, {
                name: "教师",
                id: 'TEACHER'
            } 
        ];
        return userRoles;
    };

    this.getLabStatusDict = function(){
        var labStatuses = [
            {
                name:"开放",
                id:"OPEN"
            },
            {
                name:"关闭",
                id:"CLOSED"
            }
        ];
        return labStatuses;
    };


  });
