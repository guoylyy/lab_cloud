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
    			accountEmail:'fdsf@qq.com',
    			role:'fdsfa'
    		},
    		{
    			accountNumber:'132',
    			accountName:'12332131',
    			accountEmail:'fdsf@qq.com',
    			role:'fdsfa'
    		},
    		{
    			accountNumber:'132',
    			accountName:'12332131',
    			accountEmail:'fdsf@qq.com',
    			role:'fdsfa'
    		}
    	];
    	return ulist;
    };

    this.loadLab = function(pageNum){
    	return ['',''];
    };
  });
