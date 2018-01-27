angular.module('scrabble').controller("MyBoardsController", function ($rootScope, $scope, $location, alertService, BaseService, BoardService) {
    'use strict';
	alertService.clear();
	$rootScope.pageTitle = 'menu_myGames';
    $scope.boards = [];

    BoardService.myActiveBoards().then(
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

    $scope.leaveBoard = function(boardId) {
        BoardService.leave(boardId).then(
            function (response) {
                $location.path('/v/myBoards');
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