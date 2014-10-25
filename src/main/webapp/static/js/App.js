angular.module('problem-app', [
    'ngRoute',
    'ProblemControllerModule',
    'ProblemDetailControllerModule'
]).config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/problem', {
                templateUrl: '/static/view/problem-list.html',
                controller: 'ProblemController'
            })
            .when('/problem/:problemId', {
                templateUrl: '/static/view/problem-detail.html',
                controller: 'ProblemDetailController'
            })
            .otherwise({redirectTo: '/problem'});
    }]);

