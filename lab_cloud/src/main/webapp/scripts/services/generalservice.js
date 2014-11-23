'use strict';

/**
 * @ngdoc service
 * @name prjApp.Generalservice
 * @description
 * # Generalservice
 * Service in the prjApp.
 */
angular.module('prjApp')
	.service('Generalservice', function Generalservice($http, $q, $rootScope, $location, $localStorage, $sessionStorage) {

		this.getDataWrapper = function() {
			return {
				"callStatus": "",
				"errorCode": "",
				"token": "",
				"accountId": "",
				"data": {},
				"numPerPage": 0,
				"currPageNum": 0,
				"totalItemNum": 0,
				"totalPageNum": 0
			};
		};

		this.generalPost = function(url, data) {
			var deferred = $q.defer();
			$http.post(url, data)
				.success(function(data, status, headers, config) {
					deferred.resolve(data);
				})
				.error(function(data, status, headers, config) {
					deferred.reject(data);
				});
			return deferred.promise;
		};

		this.generalGet = function(url, data) {
			var deferred = $q.defer();
			$http.get(url, data)
				.success(function(data, status, headers, config) {
					deferred.resolve(data);
				})
				.error(function(data, status, headers, config) {
					deferred.reject(data);
				});
			return deferred.promise;
		};

		this.generalDelete = function(url, data) {
			var deferred = $q.defer();
			$http.delete(url)
				.success(function(data, status, headers, config) {
					deferred.resolve(data);
				})
				.error(function(data, status, headers, config) {
					deferred.reject(data);
				});
			return deferred.promise;
		};

		this.generalUpdate = function(url, data) {
			var deferred = $q.defer();
			$http.update(url, data)
				.success(function(data, status, headers, config) {
					deferred.resolve(data);
				})
				.error(function(data, status, headers, config) {
					deferred.reject(data);
				});
			return deferred.promise;
		};

	});