package com.aduilio.spring.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.aduilio.spring.entity.Customer;
import com.aduilio.spring.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

	private static final String URL = "/api/v1/customer";

	private MockMvc mockMvc;

	@Mock
	private CustomerService mockCustomerService;

	@InjectMocks
	private CustomerController customerController;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(customerController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setViewResolvers((viewName, locale) -> new MappingJackson2JsonView()).build();
	}

	@Test
	void createWithValidPayloadShouldReturnCustomerWithId() throws Exception {
		Long id = 1L;
		String name = "Name";
		String phone = "11 22334455";

		Customer customer = new Customer();
		customer.setId(id);
		customer.setName(name);
		customer.setPhone(phone);

		when(mockCustomerService.create(customer)).thenReturn(id);

		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(customer)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(id)))
				.andExpect(jsonPath("$.name", is(name))).andExpect(jsonPath("$.name", is(phone)));
	}

	@Test
	void readWithValidIdShouldReturnCustomer() throws Exception {
		Long id = 1L;
		String name = "Name";
		String phone = "11 22334455";

		Customer customer = new Customer();
		customer.setId(id);
		customer.setName(name);
		customer.setPhone(phone);

		when(mockCustomerService.read(id)).thenReturn(customer);

		mockMvc.perform(get(URL + "/" + id).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(id))).andExpect(jsonPath("$.name", is(name)))
				.andExpect(jsonPath("$.name", is(phone)));
	}
}
