angular.module('ProblemControllerModule', ['ProblemServiceModule']).controller(
    'ProblemController',
    ['$scope', 'ProblemService', function ($scope, problemService) {
        $scope.data = problemService.problemPage.get();
    }]);

angular.module('ProblemDetailControllerModule', ['ProblemServiceModule']).controller(
    'ProblemDetailController',
    ['$scope', 'ProblemService', '$routeParams', function ($scope, problemService, $routeParams) {
        $scope.matchTypes =  ['POSITIVE','NEGATIVE'];
        $scope.problem = {};
        $scope.problem.matchList = [];

        $scope.save = function(){
            $scope.problem.$save()
        }
        $scope.addMatch = function(){
            $scope.problem
            $scope.problem.matchList.push({});
        }

        if("new" !== $routeParams.problemId){
            $scope.problem = problemService.problem.get({
                problemId: $routeParams.problemId
            });
        }
    }]);

