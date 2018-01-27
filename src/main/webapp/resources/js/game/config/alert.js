angular.module('scrabble').factory('alertService', function($rootScope) {
    'use strict';
    var alertService = {};
    $rootScope.alerts = [];

    alertService.success = function(message) {
        $rootScope.alerts.push({'type': 'success', 'message': message});
        alertService.alertTimeout(4000);
    };

    alertService.warning = function(message) {
        $rootScope.alerts.push({'type': 'warning', 'message': message});
        alertService.alertTimeout(4000);
    };

    alertService.info = function(message) {
        $rootScope.alerts.push({'type': 'info', 'message': message});
        alertService.alertTimeout(4000);
    };

    alertService.error = function(message) {
        $rootScope.alerts.push({'type': 'error', 'message': message});
        alertService.alertTimeout(4000);
    };

    alertService.gameError = function(errorCode) {
        $rootScope.alerts.push({'type': 'error', 'message': 'game_error_' + errorCode});
        alertService.alertTimeout(4000);
    };

    alertService.close = function(index) {
    	$rootScope.alerts.splice(index, 1);
    };

    alertService.clear = function() {
        $rootScope.alerts = [];
    };

    alertService.alertTimeout = function(duration) {
        setTimeout(function() {
            $rootScope.alerts.splice(0, 1);
            $('.alert-row:first-child').remove();
        }, duration);
    };

    return alertService;
});