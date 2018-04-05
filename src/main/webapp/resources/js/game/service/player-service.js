angular.module('scrabble').factory('PlayerService', ['$http', '$q', function($http, $q) {
    'use strict';
    var PLAYER_SERVICE_URI = _contextPath + 'players/board/';

    var getPlayers = function(boardId, orderNo) {
        var deferred = $q.defer();
        $http.get(PLAYER_SERVICE_URI + boardId + '/orderNo/' + orderNo, { cache: false }).then(
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
        getPlayers: getPlayers
    };

    return factory;
}]);