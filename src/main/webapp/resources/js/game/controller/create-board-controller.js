angular.module('scrabble').controller("CreateBoardController", function ($rootScope, $scope, $location, alertService, BoardService) {
	'use strict';
	alertService.clear();
	$rootScope.pageTitle = 'menu_newGame';
	$scope.name = null;
	$scope.userCount = null;
	$scope.duration = null;

    $scope.create = function() {
    	if ($scope.name == null || $scope.name == '') {
    		alertService.warning('create_validation_name');
    		return;
    	}
    	else if ($scope.userCount == null) {
    		alertService.warning('create_validation_userCount');
    		return;
    	}
    	else if ($scope.duration == null) {
    		alertService.warning('create_validation_duration');
    		return;
    	}
        BoardService.create($scope.name, $scope.userCount, $scope.duration).then(
            function (response) {
                $location.path('/v/showBoard').search({id: response.id});
            },
            function (errResponse) {
                console.error('Error while creating Board');
            }
        );
    };
});