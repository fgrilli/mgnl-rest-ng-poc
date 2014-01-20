var editorApp = angular.module('editorApp', []);

editorApp.controller('PageCtrl', ['$scope', '$http', function ($scope, $http) {

  $scope.init = function(ctx, workspace, path) {
    $scope.path = path
    $scope.ctx = ctx
    $scope.workspace = workspace
    $scope.url = ctx + '/.rest/nodes/v1/'+ workspace + path

      $http.get($scope.url).
      success(function(data) {
        //console.log(data)
        $scope.page = data;
      }).
      error(function(data) {
        alert('ERROR: ' + data)
      });
    };

   $scope.save = function() {
     $http.post($scope.url, $scope.page).
     success(function(data) {
        alert('Page saved')
      }).
      error(function(data) {
        alert('ERROR: ' + data)
      });
   }

   $scope.preview = function() {
     window.open($scope.ctx + $scope.path)
   }

   $scope.publish = function() {
     // In order for this to work you need to grant "rest" role get&post for "/.rest/commands*"
     $http.post($scope.ctx + '/.rest/commands/v1/website/activate', {"path":$scope.page.path, "uuid": $scope.page.identifier, "repository":$scope.workspace}).
     success(function(data) {
       alert('Publication workflow started')
     }).
     error(function(data) {
       alert('ERROR: ' + data)
     });
   }

}]);
