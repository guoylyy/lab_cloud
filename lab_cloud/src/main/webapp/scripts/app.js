'use strict';

/**
 * @ngdoc overview
 * @description
 *
 * Main module of the application.
 */
angular
    .module('prjApp', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch',
        'ui.sortable',
        'ui.bootstrap',
        'ui.tinymce',
        'ui.select2',
        'angularFileUpload',
        'dialogs.main',
        'textAngular'
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                redirectTo: '/login'
            })
            .when('/login', {
                templateUrl: 'views/login.html',
                controller: 'LoginCtrl'
            })
            .when('/profile', {
                templateUrl: 'views/profile.html',
                controller: 'ProfileCtrl'
            })
            .when('/register', {
                templateUrl: 'views/register.html',
                controller: 'RegisterCtrl'
            })
            .when('/system-manage', {
              templateUrl: 'views/system-manage.html',
              controller: 'SystemManageCtrl'
            })
            .when('/my-experiment', {
              templateUrl: 'views/my-experiment.html',
              controller: 'MyExperimentCtrl'
            })
            .when('/my-class', {
              templateUrl: 'views/my-class.html',
              controller: 'MyClassCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });