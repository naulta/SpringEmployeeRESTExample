package com.kenzanexample.employeedemo.controller;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.kenzanexample.employeedemo.domain.Employee;
import com.kenzanexample.employeedemo.service.EmployeeService;
import com.kenzanexample.employeedemo.controller.EmployeeController;
import com.kenzanexample.employeedemo.EmployeeApplication;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;
	Employee mockEmployee = new Employee(1001L, "Test", "T", "User", "06/28/1970","09/01/2018","ACTIVE");
	Employee deleteEmployee = new Employee(1001L, "Test", "T", "User", "06/28/1970","09/01/2018","INACTIVE");

	@Test
	public void getEmployeee() throws Exception {
		
		Mockito.when((
				employeeService.findById(Mockito.anyLong()))).thenReturn(Optional.of(mockEmployee));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/employees/1001").accept(
						MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1001,firstName:Test,middleInitial:T,lastName:User,dateOfBirth:'06/28/1970',dateOfEmployment:'09/01/2018',status:ACTIVE}";
	

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void deleteEmployee() throws Exception {
		
		Mockito.when((
				employeeService.findById(Mockito.anyLong()))).thenReturn(Optional.of(deleteEmployee));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
				"/employees/1001").accept(
						MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1001,firstName:Test,middleInitial:T,lastName:User,dateOfBirth:'06/28/1970',dateOfEmployment:'09/01/2018',status:INACTIVE}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}		
		
}