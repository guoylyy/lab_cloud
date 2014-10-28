'use strict';

/**
 * @ngdoc service
 * @name prjApp.LoginService
 * @description
 * # LoginService
 * Service in the prjApp.
 */
angular.module('prjApp')
    .service('LoginService', function LoginService($http, $q, $rootScope, $location) {
        // AngularJS will instantiate a singleton by calling "new" on this function
        this.saveCurrentUser = function (user, password, isKeepLogin) {
            //TODO: Save data to $root
            $location.path('/');
        };

        this.login = function (userName, userPWD) {
            var deferred = $q.defer();
            $http.get('api/user/login', {params: {'userName': userName, 'userPWD': userPWD}})
                .success(function (data, status, headers, config) {
                    deferred.resolve(data);
                })
                .error(function (data, status, headers, config) {
                    deferred.reject(data);
                });
            return deferred.promise;
        };
        this.logout = function () {
            var deferred = $q.defer();
            $http.get('api/user/logout', {params: {'sessionKey': $rootScope.sessionKey}})
                .success(function (data, status, headers, config) {
                    deferred.resolve(data);
                })
                .error(function (data, status, headers, config) {
                    deferred.reject(data);
                });
            return deferred.promise;
        };
    });
