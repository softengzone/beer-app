/**
 * Project: Beer App
 * @Author: Krzysztof Anczyk
 */

var app = angular.module('beerApp');

app.controller('AnotherBeerController',	function (BeersTotalService, BeerService, BreweryService, $scope) {

	// Get a Beer with given id
	$scope.getAnotherBeer = function() {
		
		BeersTotalService.get(
			function(data) {
				var randId = Math.floor(Math.random() * data.value) + 1;
				
				BeerService.get({id: randId}, function(data) {
					$scope.beer = data;
					
					BreweryService.get({id: $scope.beer.breweryId}, function(data) {
						$scope.brewery = data;
					},
					function(err) {
						$scope.brewery = null;
					});
					
				},
				function(err) {
					console.log("Error: ", err);
				});
				
			},
			function(err) {
				console.log("Error: ", err);
			}
		);
				
	}
	
});

app.controller('BeerListController', function (BeerService, $scope) {
	
	// Get all Beers
	BeerService.query(
		function(data) {
			$scope.beers = data;
		},
		function(err) {
			console.log("Error: ", err);
		}
	);
	
});