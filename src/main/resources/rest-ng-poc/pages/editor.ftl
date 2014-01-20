<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <!--we can access static resources as illustrated below. The styles.css file resides at /src/main/resources/mgnl-resources/rest-ng-poc/css/styles.css -->
        <link media="screen" href="${contextPath}/.resources/rest-ng-poc/css/styles.css" type="text/css" rel="stylesheet">
        <!-- like css, jQuery or angularJs could be also served e.g. via /.resources/rest-ng-poc/js/jquery.min.js-->
        <!--script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script-->
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.6/angular.min.js"></script>
        <script src="${contextPath}/.resources/rest-ng-poc/js/controllers.js"></script>
        <title>Title</title>
    </head>

    <body>
        <div id="wrapper" ng-app="editorApp">
            <!--To access Magnolia REST API you need two basic information, path and workspace
             here we initialize the ng ctrl with those info exposed as freemarker template variables -->
            <div ng-controller="PageCtrl" ng-init="init('${contextPath}', '${workspace}','${path}')">
            <ul>
              <li ng-repeat="props in page.properties | filter:{type:'String'}">
              <label for="text">{{props.name}}</label>
              <p>
              <textarea id="text" cols="100" rows="5" ng-model="props.values[0]"></textarea>
              </li>
            </ul>
            <p>
            <button ng-click="save()">SAVE</button>
            <button ng-click="preview()">PREVIEW</button>
            <button ng-click="publish()">PUBLISH</button>
            </div>
        </div>
    </body>
</html>
