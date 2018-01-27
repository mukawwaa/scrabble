angular.module('scrabble').controller("BoardsController", function ($rootScope, $scope, $location, alertService, BaseService, BoardService) {
    'use strict';
	alertService.clear();
	$rootScope.pageTitle = 'menu_activeGames'; 
    $scope.boards = [];

    BoardService.allActiveBoards().then(
        function (response) {
            $scope.boards = response;
            if (response.length == 0) {
            	alertService.warning('boards_empty');
            }
        },
        function (errResponse) {
            console.error('Error while loading Board');
        }
    );

    $scope.joinBoard = function(boardId) {
        BoardService.join(boardId).then(
            function (response) {
                $location.path('/v/showBoard').search({id: boardId});
            },
            function (errResponse) {
            	alertService.gameError(errResponse.data.responseCode);
            }
        );
    };

    $scope.watchBoard = function(boardId) {
        $location.path('/v/showBoard').search({id: boardId});
    };
});