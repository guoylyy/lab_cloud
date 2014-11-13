'use strict';

/**
 * @ngdoc filter
 * @name prjApp.filter:userRoleFilter
 * @function
 * @description
 * # userRoleFilter
 * Filter in the prjApp.
 */
angular.module('prjApp')
  .filter('userRole', function (DictService) {
    return function (input) {
    	var roles = DictService.getRoleDict();
    	var name = 'ss';
    	for(var i in roles){
    		var role = roles[i];
    		if(role.id == input){
    			name = role.name;
    		}
    	}
    	return name;
    
    };
  });
