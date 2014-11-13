'use strict';

/**
 * @ngdoc service
 * @name prjApp.LoginService
 * @description
 * # LoginService
 * Service in the prjApp.
 */
angular.module('prjApp')
    .service('LoginService', function LoginService($http, $q, $rootScope, $location, $localStorage, $sessionStorage) {
        // AngularJS will instantiate a singleton by calling "new" on this function
        this.saveCurrentUser = function (user, password, isKeepLogin) {
            //TODO: Save data to $root
            $location.path('/');
        };

        this.login = function (userName, userPWD) {
            var deferred = $q.defer();
            $http.post('Account/login', {data:{ accountNumber:userName, accountPassword: userPWD}})
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
            $http.post('Account/logout', {'token': $rootScope.token})
                .success(function (data, status, headers, config) {
                    deferred.resolve(data);
                })
                .error(function (data, status, headers, config) {
                    deferred.reject(data);
                });
            return deferred.promise;
        };

        this.clearSessionStorage = function(){
            delete $rootScope.loginUser;
            delete $sessionStorage.loginUser;
            delete $rootScope.token;
            delete $sessionStorage.token;
            $location.path('/');
        }

        this.persitentLogin = function(user, token){
            $rootScope.loginUser = user;
            $rootScope.token = token;
            $sessionStorage.loginUser = user;
            $sessionStorage.token = token;
        };

        this.getLoginUser = function(){
            if($rootScope.loginUser != undefined){
                return $rootScope.loginUser;
            }else if($sessionStorage.loginUser != undefined){
                $rootScope.loginUser = $sessionStorage.loginUser;
                return $sessionStorage.loginUser;
            }else{
                return false;
            }
        };
    });
