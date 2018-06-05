package com.beerapp.controller.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.beerapp.controller.BreweryController;
import com.beerapp.domain.Brewery;
import com.beerapp.services.BreweryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BreweryControllerTest {

private final static String URL_BASE = "/rest/brewery";
	
	private MockMvc mockMvc;
	
	@Mock
	private BreweryServiceImpl service;
	
	@InjectMocks
	private BreweryController controller;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	// Retrieve all Breweries
	@Test
	public void testGetAllBreweries_success() throws Exception {
		when(service.findAll()).thenReturn(getMockBrewery());
		
		mockMvc.perform(get(URL_BASE))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].name", is("Brewery 1")))
			.andExpect(jsonPath("$[0].email", is("email@email.test")))
			.andExpect(jsonPath("$[0].phone", is("99999")))
			.andExpect(jsonPath("$[0].address1", is("addr1")))
			.andExpect(jsonPath("$[0].address2", is("addr2")))
			.andExpect(jsonPath("$[0].address3", is("addr3")))
			.andExpect(jsonPath("$[0].postCode", is("post code")))
			.andExpect(jsonPath("$[0].country", is("Ireland")))
			.andExpect(jsonPath("$[1].name", is("Brewery 2")))
			.andExpect(jsonPath("$[1].email", is("email1@email1.test")))
			.andExpect(jsonPath("$[1].phone", is("00000")))
			.andExpect(jsonPath("$[1].address1", is("addr1")))
			.andExpect(jsonPath("$[1].address2", is("addr2")))
			.andExpect(jsonPath("$[1].address3", is("addr3")))
			.andExpect(jsonPath("$[1].postCode", is("post code")))
			.andExpect(jsonPath("$[1].country", is("Germany")))
			;
		
		verify(service, times(1)).findAll();
	    verifyNoMoreInteractions(service);
	}
	
	// Retrieve Brewery by ID
	@Test
	public void testGetBeerById_success() throws Exception {
		when(service.findById(100L)).thenReturn(Optional.of(getMockBrewery().get(0)));
		
		mockMvc.perform(get(URL_BASE+"/{id}", 100))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$.name", is("Brewery 1")))
			.andExpect(jsonPath("$.email", is("email@email.test")))
			.andExpect(jsonPath("$.phone", is("99999")))
			.andExpect(jsonPath("$.address1", is("addr1")))
			.andExpect(jsonPath("$.address2", is("addr2")))
			.andExpect(jsonPath("$.address3", is("addr3")))
			.andExpect(jsonPath("$.postCode", is("post code")))
			.andExpect(jsonPath("$.country", is("Ireland")))
			;
		
		verify(service, times(1)).findById(100L);
	    verifyNoMoreInteractions(service);
	}
	
	private List<Brewery> getMockBrewery() {
		Brewery brewery1 = new Brewery("Brewery 1", "email@email.test", "99999", "addr1", "addr2", "addr3", "post code", "Ireland");
		Brewery brewery2 = new Brewery("Brewery 2", "email1@email1.test", "00000", "addr1", "addr2", "addr3", "post code", "Germany");
		return Arrays.asList(brewery1, brewery2);
	}
	
}
