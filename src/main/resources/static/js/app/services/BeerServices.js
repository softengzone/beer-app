/**
 * Project: Beer App
 * @Author: Krzysztof Anczyk
 */

var services = angular.module('beerApp');

services.factory('BeerService', function($resource) {
	return $resource('/BeerApp/rest/beer/:id');
});

services.factory('BeersTotalService', function($resource) {
	return $resource('/BeerApp/rest/beer/total');
})