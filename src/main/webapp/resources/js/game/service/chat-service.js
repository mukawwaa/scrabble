angular.module('scrabble').factory('ChatService', ['$http', '$q', function($http, $q) {
    'use strict';
    var CHAT_SERVICE_URI = _contextPath + 'chat/board/';
    var BOARD_SERVICE_URI = _contextPath + 'board/';

    var getMessages = function(boardId, orderNo) {
        var deferred = $q.defer();
        $http.get(CHAT_SERVICE_URI + boardId + '/orderNo/' + orderNo, { cache: false }).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    };

    var send = function(boardId, message) {
        var deferred = $q.defer();
        $http.post(BOARD_SERVICE_URI + boardId + '/sendMessage', message, { cache: false }).then(
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
        getMessages: getMessages,
        send: send
    };

    return factory;
}]);