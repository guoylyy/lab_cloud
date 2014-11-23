'use strict';

/**
 * @ngdoc service
 * @name prjApp.SystemManageService
 * @description
 * # SystemManageService
 * Service in the prjApp.
 */
angular.module('prjApp')
    .service('SystemManageService', function SystemManageService($http, $q, $rootScope, $location, $localStorage, Generalservice) {
        this.loadUser = function(userType, pageNum) {
            return Generalservice.generalPost('Account/all', {
                'token': $rootScope.token
            });
        };

        this.removeUser = function(uid) {
            return Generalservice.generalDelete('Account/delete/' + uid);
        };

        this.addUser = function(data) {
            data.token = $rootScope.token;
            return Generalservice.generalPost('Account/add', data);
        };

        this.updateUser = function(uid, data) {
            data.token = $rootScope.token;
            return Generalservice.generalPost('Account/update/' + uid, data);
        };


        this.loadLab = function(pageNum) {
            return Generalservice.generalPost('Lab/all', {
                'token': $rootScope.token
            });
        };

        this.removeLab = function(uid) {
            return Generalservice.generalDelete('Lab/delete/' + uid);
        };

        this.addLab = function(data) {
            data.token = $rootScope.token;
            return Generalservice.generalPost('Lab/add', data);
        };

        this.updateLab = function(uid, data) {
            data.token = $rootScope.token;
            return Generalservice.generalPost('Lab/update/' + uid, data);
        };



        this.loadExperiment = function(pageNum) {
            return Generalservice.generalPost('Experiment/all', {
                'token': $rootScope.token
            });
        };

        this.removeExperiment = function(uid) {
            return Generalservice.generalDelete('Experiment/delete/' + uid);
        };

        this.addExperiment = function(data) {
            data.token = $rootScope.token;
            return Generalservice.generalPost('Experiment/add', data);
        };

        this.updateExperiment = function(uid, data) {
            data.token = $rootScope.token;
            return Generalservice.generalPost('Experiment/update/' + uid, data);
        };

        this.loadCourse = function(pageNum) {
            return Generalservice.generalPost('Course/all', {
                'token': $rootScope.token
            });
        };

        this.removeCourse = function(uid) {
            return Generalservice.generalDelete('Course/delete/' + uid);
        };

        this.addCourse = function(data) {
            data.token = $rootScope.token;
            return Generalservice.generalPost('Course/add', data);
        };

        this.updateCourse = function(uid, data) {
            data.token = $rootScope.token;
            return Generalservice.generalPost('Course/update/' + uid, data);
        };

    });