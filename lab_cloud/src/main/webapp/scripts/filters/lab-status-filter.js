'use strict';

/**
 * @ngdoc filter
 * @name prjApp.filter:labStatusFilter
 * @function
 * @description
 * # labStatusFilter
 * Filter in the prjApp.
 */
angular.module('prjApp')
  .filter('labStatusFilter', function (DictService) {
    return function (input) {
    	var dict = DictService.getLabStatusDict();
    	var name = '';
    	for(var i in dict){
    		var node = dict[i];
    		if(node.id == input){
    			name = node.name;
    		}
    	}
    	return name;
    };
  })
  .filter('labOpenFilter', function(){
    return function (input){
        if(input==false){
            return '关闭';
        }else{
            return '开放';
        }
    };
  });
