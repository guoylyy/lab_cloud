'use strict';

/**
 * @ngdoc service
 * @name prjApp.SystemManageService
 * @description
 * # SystemManageService
 * Service in the prjApp.
 */
angular.module('prjApp')
  .service('SystemManageService', function SystemManageService() {
    // AngularJS will instantiate a singleton by calling "new" on this function
    this.loadUser = function(userType, pageNum){
    	var ulist = [
    		{
    			accountNumber:'132',
    			accountName:'12332131',
                accountPassword:'12332131',
    			accountEmail:'fdsf@qq.com',
                desc:'fdsafa',
    			role:1
    		},
    		{
    			accountNumber:'132',
    			accountName:'12332131',
                accountPassword:'12332131',
    			accountEmail:'fdsf@qq.com',
                desc:'fdsafa',
    			role:1
    		},
    		{
    			accountNumber:'132',
    			accountName:'12332131',
                accountPassword:'12332131',
    			accountEmail:'fdsf@qq.com',
                desc:'fdsafa',
    			role:1
    		}
    	];
    	return ulist;
    };

    this.loadLab = function(pageNum){
    	return [];
    };

    this.loadExperiment = function(pageNum){
        return [];
    };

    this.loadCourse = function(pageNum){
        return [];
    };
  });
