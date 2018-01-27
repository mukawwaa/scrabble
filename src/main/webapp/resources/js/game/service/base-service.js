angular.module('scrabble').factory('BaseService', ['$http', '$q', function($http, $q) {
    'use strict';

    var REST_SERVICE_URI = _contextPath + 'rest/';

    var list = function(alias) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + alias + '/list').then(function (response) {
            deferred.resolve(response.data);
        },
        function(errResponse){
            console.error('Error while fetching Objects');
            deferred.reject(errResponse);
        });
        return deferred.promise;
    };
 
    var get = function(alias, id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + alias + '/get/' + id).then(function (response) {
            deferred.resolve(response.data);
        },
        function(errResponse){
            console.error('Error while fetching Object');
            deferred.reject(errResponse);
        });
        return deferred.promise;
    };
    
    var save = function(alias, entity) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + alias + '/', entity).then(function (response) {
            deferred.resolve(response.data);
        },
        function(errResponse){
            console.error('Error while saving Object');
            deferred.reject(errResponse);
        });
        return deferred.promise;
    };
    
    var post = function(alias, entity) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + alias, entity).then(function (response) {
            deferred.resolve(response.data);
        },
        function(errResponse){
            console.error('Error while posting Object');
            deferred.reject(errResponse);
        });
        return deferred.promise;
    };
 
    var factory = {
        list: list,
        get: get,
        save: save,
        post: post
    };
 
    return factory;
}]);