/**
 * Project: Beer App
 * @Author: Krzysztof Anczyk
 */

var services = angular.module('beerApp');

services.factory('BreweryService', function($resource) {
	return $resource('/BeerApp/rest/brewery/:id');
});