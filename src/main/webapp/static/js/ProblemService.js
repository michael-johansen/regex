angular
    .module('ProblemServiceModule', ['ngResource'])
    .factory('ProblemService', ['$resource', '$http', function ($resource, $http) {
        $http.defaults.headers.common.Accept = "application/json";
        var url = '/api/problem';
        var problemPage = $resource(url);
        var problem = $resource(url + "/:problemId")

        return  {
            problemPage: problemPage,
            problem: problem
        };
    }]);