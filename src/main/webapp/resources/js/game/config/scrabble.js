'use strict';
var scrabble = angular.module('scrabble', ['ngRoute', 'pascalprecht.translate']);
scrabble.config(function($routeProvider, $locationProvider) {
    $routeProvider
        .when('/v/createBoard', {
            templateUrl: 'pages/createBoard.html',
            controller: 'CreateBoardController'
        })
        .when('/v/showBoard', {
            templateUrl: 'pages/showBoard.html',
            controller: 'ShowBoardController'
        })
        .when('/v/boards', {
            templateUrl: 'pages/boards.html',
            controller: 'BoardsController'
        })
        .when('/v/myBoards', {
            templateUrl: 'pages/myBoards.html',
            controller: 'MyBoardsController'
        }
    );
    $locationProvider.html5Mode(true);
});