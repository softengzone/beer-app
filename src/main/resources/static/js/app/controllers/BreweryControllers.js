/**
 * Project: Beer App
 * @author: Krzysztof Anczyk
 */

var app = angular.module('beerApp');

app.controller('BreweryLocationListController', function (BreweryService, $scope) {
	
	// Get all Brewery Locations
	BreweryService.query(
		function(data) {
			$scope.breweries = data;
		},
		function(err) {
			console.log("Error: ", err);
		}
	);
	
});

