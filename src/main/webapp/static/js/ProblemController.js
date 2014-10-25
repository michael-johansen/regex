angular.module('ProblemControllerModule', ['ProblemServiceModule']).controller(
    'ProblemController',
    ['$scope', 'ProblemService', function ($scope, problemService) {
        $scope.data = problemService.problemPage.get();
    }]);

angular.module('ProblemDetailControllerModule', ['ProblemServiceModule']).controller(
    'ProblemDetailController',
    ['$scope', 'ProblemService', '$routeParams', function ($scope, problemService, $routeParams) {
        $scope.matchTypes =  ['POSITIVE','NEGATIVE'];
        $scope.save = function(){
            $scope.problem.$save()
        }
        $scope.addMatch = function(){
            $scope.problem.matchList.push({});
        }

        $scope.problem = problemService.problem.get({
            problemId: $routeParams.problemId
        });
    }]);

