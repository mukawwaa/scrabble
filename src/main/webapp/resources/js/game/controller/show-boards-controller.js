angular.module('scrabble').controller("ShowBoardController", function ($rootScope, $scope, $routeParams, alertService, BaseService, BoardService, ContentService, ChatService, PlayerService) {
    'use strict';
	alertService.clear();
    $scope.boardId;
    $scope.boardStatus;
    $scope.rack;
    $scope.rule;
    $scope.boardOrderNo;
    $scope.playersOrderNo = 1;
    $scope.selectedRackTile;
    $scope.inputMessage;
    $scope.cells = [];
    $scope.players = [];
    $scope.moves = [];
    $scope.chats = [];

    var load = function() {
        BaseService.get('board', $scope.boardId).then(
            function (board) {
                if (board != null) {
                    $scope.rule = board.rule;
                    $scope.boardStatus = board.status;
                    $scope.boardOrderNo = board.orderNo;
                    getContent();
                    getPlayers();
                    getMessages();
                    if (board.status == 'STARTED') {
                        alertService.success('boards_started');
                    } else if (board.status == 'WAITING_PLAYERS') {
                        alertService.info('boards_waitingUsers');
                    }
                }
            },
            function (errResponse) {
                console.error(errResponse);
            }
        );
    };

    var getRack = function() {
        BoardService.getRack($scope.boardId).then(
            function (response) {
                $scope.rack = response;
            },
            function (errResponse) {
                console.error(errResponse);
            }
        );
    };

    var getContent = function() {
        ContentService.getContent($scope.boardId, $scope.boardOrderNo).then(
            function (content) {
                if (content != null && content.boardId != null && content.boardId == $scope.boardId) {
                    $scope.cells = content.cells;
                    $scope.boardStatus = content.status;
                    $scope.boardOrderNo = $scope.boardOrderNo + 1;
                    if (content.status == 'STARTED') {
                        getRack();
                    }
                }
                getContent();
            },
            function (errResponse) {
                console.error(errResponse);
            }
        );
    };

    var getPlayers = function() {
        PlayerService.getPlayers($scope.boardId, $scope.playersOrderNo).then(
            function (playerContent) {
                if (playerContent != null && playerContent.boardId != null && playerContent.boardId == $scope.boardId) {
                    $scope.players = playerContent.players;
                    $scope.playersOrderNo = $scope.playersOrderNo + 1;
                }
                getPlayers();
            },
            function (errResponse) {
                console.error(errResponse);
            }
        );
    };

    var getMessages = function() {
        ChatService.getMessages($scope.boardId, $scope.chats.length + 1).then(
            function (newChats) {
                if (newChats != null && newChats.length > 0) {
                    $scope.chats = $scope.chats.concat(newChats);
                }
                getMessages();
            },
            function (errResponse) {
                console.error(errResponse);
            }
        );
    };

    $scope.play = function() {
        BoardService.play($scope.boardId, $scope.rack).then(
            function (response) {
                getContent();
            },
            function (errResponse) {
                alertService.gameError(errResponse.data.responseCode);
            }
        );
    };

    $scope.sendMessage = function() {
        ChatService.send($scope.boardId, $scope.inputMessage).then(
            function (response) {
                $scope.inputMessage = null;
            },
            function (errResponse) {
                alertService.gameError(errResponse.data.responseCode);
            }
        );
    };

    $scope.selectRackTile = function(rackTile) {
        if (rackTile.used) {
            $scope.selectedRackTile = null;
        } else if ($scope.selectedRackTile != null && $scope.selectedRackTile.tileNumber == rackTile.tileNumber) {
            $scope.selectedRackTile = null;
        } else {
            $scope.selectedRackTile = rackTile;
        }
    };

    $scope.moveRackTile = function(cell) {
        if ($scope.selectedRackTile != null) {
            if (cell.letter == null) {
                cell.letter = $scope.selectedRackTile.letter;
                cell.tileNumber = $scope.selectedRackTile.tileNumber;
                cell.score = $scope.selectedRackTile.score;
                $scope.selectedRackTile.cellNumber = cell.rule.cellNumber;
                $scope.selectedRackTile.rowNumber = cell.rule.rowNumber;
                $scope.selectedRackTile.columnNumber = cell.rule.columnNumber;
                $scope.selectedRackTile.used = true;
                $scope.selectedRackTile = null;
            } else if (cell.letter != null) {
                alert('Cell is not empty!');
                $scope.selectedRackTile = null;
            }
        } else if (cell.letter != null && cell.tileNumber != null) {
            $scope.rack.tiles[cell.tileNumber - 1].used = false;
            $scope.rack.tiles[cell.tileNumber - 1].cellNumber = null;
            $scope.rack.tiles[cell.tileNumber - 1].rowNumber = null;
            $scope.rack.tiles[cell.tileNumber - 1].columnNumber = null;
            cell.letter = null;
            cell.tileNumber = null;
            cell.score = null;
        }
    };

    if ($routeParams.id != null) {
        $scope.boardId = $routeParams.id;
        load();
    }
});