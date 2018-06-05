package com.beerapp.controller.unit;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import com.beerapp.controller.BeerController;
import com.beerapp.controller.utils.TestUtils;
import com.beerapp.domain.Beer;
import com.beerapp.services.BeerServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class BeerControllerTest {
	
	private final static String URL_BASE = "/rest/beer";
	
	private MockMvc mockMvc;
	
	@Mock
	private BeerServiceImpl service;
	
	@InjectMocks
	private BeerController controller;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	// Retrieve all Beers
	@Test
	public void testGetAllBeers_success() throws Exception {
		when(service.findAll()).thenReturn(getMockBeer());
		
		mockMvc.perform(get(URL_BASE))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].name", is("Beer 1")))
			.andExpect(jsonPath("$[0].description", is("Beer description 1")))
			.andExpect(jsonPath("$[0].abv", is(4.5)))
			.andExpect(jsonPath("$[0].breweryId", is(100)))
			.andExpect(jsonPath("$[1].name", is("Beer 2")))
			.andExpect(jsonPath("$[1].description", is("Beer description 2")))
			.andExpect(jsonPath("$[1].abv", is(5.0)))
			.andExpect(jsonPath("$[1].breweryId", is(101)))
			;
		
		verify(service, times(1)).findAll();
	    verifyNoMoreInteractions(service);
	}
	
	// Retrieve Beer by ID
	@Test
	public void testGetBeerById_success() throws Exception {
		when(service.findById(100L)).thenReturn(Optional.of(getMockBeer().get(0)));
		
		mockMvc.perform(get(URL_BASE+"/{id}", 100))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$.name", is("Beer 1")))
			.andExpect(jsonPath("$.description", is("Beer description 1")))
			.andExpect(jsonPath("$.abv", is(4.5)))
			.andExpect(jsonPath("$.breweryId", is(100)))
			;
		
		verify(service, times(1)).findById(100L);
	    verifyNoMoreInteractions(service);
	}
			
	// Create new beer
	@Test
	public void testCreateBeer_success() throws Exception {
		Beer beer = getMockBeer().get(0);
		when(service.doesBeerExist(beer.getName())).thenReturn(false);
		when(service.save(beer)).thenReturn(beer);
		
		performPostAction(beer, status().isCreated(), "http://localhost/rest/beer/");	
		
		verify(service, times(1)).doesBeerExist(beer.getName());
	}
	
	// Create new beer with conflict
	@Test
	public void testCreateBeer_conflict() throws Exception {
		Beer beer = getMockBeer().get(0);
		when(service.doesBeerExist(beer.getName())).thenReturn(true);
		
		performPostAction(beer, status().isConflict(), null);	
		
		verify(service, times(1)).doesBeerExist(beer.getName());
	}
	
	// Update a beer
	@Test
	public void testUpdateBeer_success() throws Exception {
		Beer beer = getMockBeer().get(0);
		beer.setId(1L);
		Optional<Beer> _beer = Optional.of(beer);
		when(service.findById(_beer.get().getId())).thenReturn(_beer);
		when(service.save(beer)).thenReturn(beer);
		
		mockMvc.perform(put(URL_BASE+"/{id}", beer.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.asJsonString(beer)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$.name", is("Beer 1")))
			.andExpect(jsonPath("$.description", is("Beer description 1")))
			.andExpect(jsonPath("$.abv", is(4.5)))
			.andExpect(jsonPath("$.breweryId", is(100)));
		
		verify(service, times(1)).save(beer);
	}
	
	// Delete beer - success
	@Test
	public void testDeleteBeer_success() throws Exception {
		Beer beer = getMockBeer().get(0);
		beer.setId(1L);
		Optional<Beer> _beer = Optional.of(beer);
		when(service.findById(_beer.get().getId())).thenReturn(_beer);
		doNothing().when(service).delete(beer);
		
		mockMvc.perform(delete(URL_BASE+"/{id}", beer.getId()))				
			.andExpect(status().isNoContent());
		
		verify(service, times(1)).findById(beer.getId());
		verify(service, times(1)).delete(beer);
	}
	
	// Delete user - failed
	@Test
	public void testDeleteBeer_fail() throws Exception {
		Beer beer = getMockBeer().get(0);
		beer.setId(1L);
		Optional<Beer> _beer = Optional.of(beer);
		when(service.findById(_beer.get().getId())).thenReturn(Optional.empty());
		
		mockMvc.perform(delete(URL_BASE+"/{id}", beer.getId()))
			.andExpect(status().isNotFound());
		
		verify(service, times(1)).findById(beer.getId());
	}
	
	@Test
	public void testDeleteAll_beersDeleted() throws Exception {		
		mockMvc.perform(delete(URL_BASE))
			.andExpect(status().isNoContent());
		
		verify(service, times(1)).deleteAll();
	}
	
	// Total number of Beers
	public void testGetTotalNumberOfBeers_valueReturned() throws Exception {
		when(service.getTotalNumberOfBeers()).thenReturn(5L);
		
		mockMvc.perform(get("/rest/total"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$.value", is(5)));
	}
	
	private void performPostAction(Beer beer, ResultMatcher expectedStatus, String location) throws Exception {
		mockMvc.perform(post("/rest/beer")
				.content(TestUtils.asJsonString(beer))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(expectedStatus)
			.andExpect(header().string("location", location == null ? nullValue() : containsString("http://localhost/rest/beer/")));
	}
	
	private List<Beer> getMockBeer() {
		Beer beer1 = new Beer("Beer 1", "Beer description 1", 4.5, 100L);
		Beer beer2 = new Beer("Beer 2", "Beer description 2", 5.0, 101L);
		return Arrays.asList(beer1, beer2);
	}

	
	
}
