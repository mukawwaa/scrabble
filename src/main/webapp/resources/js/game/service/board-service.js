angular.module('scrabble').factory('BoardService', ['$http', '$q', function($http, $q) {
    'use strict';
    var BOARD_SERVICE_URI = _contextPath + 'board/';

    var create = function(name, userCount, duration) {
        var deferred = $q.defer();
        var boardParams = {name: name, userCount: userCount, duration: duration};
        $http.post(BOARD_SERVICE_URI + 'create', boardParams).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    };

    var join = function(boardId) {
        var deferred = $q.defer();
        $http.post(BOARD_SERVICE_URI + boardId + '/join', { cache: false }).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    };

    var leave = function(boardId) {
        var deferred = $q.defer();
        $http.post(BOARD_SERVICE_URI + boardId + '/leave', { cache: false }).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    };

    var play = function(boardId, rack) {
        var deferred = $q.defer();
        $http.post(BOARD_SERVICE_URI + boardId + '/play', rack, { cache: false }).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    };

    var getRack = function(boardId) {
        var deferred = $q.defer();
        $http.get(BOARD_SERVICE_URI + boardId + '/rack').then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    };

    var allActiveBoards = function() {
        var deferred = $q.defer();
        $http.get(BOARD_SERVICE_URI + 'all/active').then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    };

    var myActiveBoards = function() {
        var deferred = $q.defer();
        $http.get(BOARD_SERVICE_URI + 'my/active').then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    };

    var factory = {
        create: create,
        join: join,
        leave: leave,
        play: play,
        getRack: getRack,
        allActiveBoards: allActiveBoards,
        myActiveBoards: myActiveBoards
    };

    return factory;
}]);