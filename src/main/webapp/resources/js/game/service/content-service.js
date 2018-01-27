angular.module('scrabble').factory('ContentService', ['$http', '$q', function($http, $q) {
    'use strict';
    var CONTENT_SERVICE_URI = _contextPath + 'content/board/';

    var getContent = function(boardId, orderNo) {
        var deferred = $q.defer();
        $http.get(CONTENT_SERVICE_URI + boardId + '/orderNo/' + orderNo, { cache: false }).then(
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
        getContent: getContent
    };

    return factory;
}]);