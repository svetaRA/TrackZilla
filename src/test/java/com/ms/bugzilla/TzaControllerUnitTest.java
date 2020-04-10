package com.ms.bugzilla;


import static org.hamcrest.Matchers.hasSize;


import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.*;

import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.bugzilla.entity.Application;
import com.ms.bugzilla.service.ApplicationService;
import com.ms.bugzilla.util.Constants;
import com.ms.bugzilla.web.TzaController;


@RunWith(SpringRunner.class)
@WebMvcTest(TzaController.class)
public class TzaControllerUnitTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ApplicationService applicationService;
	 // =========================================== Get All Applications API Specs ==========================================
	@Test
	@Ignore
	public void test_getApplications_spec_success() throws Exception {
		mockMvc.perform(get(Constants.GET_APPLICATIONS_URL)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("[]"));
		verify(applicationService, times(1)).listApplication();

	}

	 // =========================================== Get All Applications ==========================================

    @Test
    @Ignore
    public void test_get_allApplications_success() throws Exception {
    	//setUp
        List<Application> apps = Arrays.asList(
                new Application(1,"Trackzilla","A bug tracking application", "Shweta Rautela"),
                new Application(2,"Expenses","An application used to submit expenses", "Srini"),
                new Application(3,"Bookings","An application used to book tickets", "Abhinav")
                );
        
        //execution
        when(applicationService.listApplication()).thenReturn(apps);
        
        //verify
        mockMvc.perform(get(Constants.GET_APPLICATIONS_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Trackzilla")))
				.andExpect(jsonPath("$[0].description", is("A bug tracking application")))
                .andExpect(jsonPath("$[0].owner", is("Shweta Rautela")))
                
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Expenses")))
				.andExpect(jsonPath("$[1].description", is("An application used to submit expenses")))
                .andExpect(jsonPath("$[1].owner", is("Srini")))
                
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].name", is("Bookings")))
				.andExpect(jsonPath("$[2].description", is("An application used to book tickets")))
                .andExpect(jsonPath("$[2].owner", is("Abhinav")));

        verify(applicationService, times(1)).listApplication();
        verifyNoMoreInteractions(applicationService);
    }

	
    // =========================================== Get Application by ID ==========================================
	
	@Test
	@Ignore
	public void test_getApplicationById_success() throws Exception {
		// SetUp
		Application app = new Application("Trackzilla", "A bug tracking application", "Shweta Rautela");
		// execution
		when(applicationService.findApplicationbyId(3)).thenReturn(app);
		
		
		// verify
		mockMvc.perform(get("/tza/applications/{id}", 3)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Trackzilla")))
				.andExpect(jsonPath("$.description", is("A bug tracking application")))
				.andExpect(jsonPath("$.owner", is("Shweta Rautela")));
		
		verify(applicationService, times(1)).findApplicationbyId(4);
		 verifyNoMoreInteractions(applicationService);

	}
	
	 @Test
	 @Ignore
	    public void test_getApplicationById_fail_404_not_found() throws Exception {
		
	        when(applicationService.findApplicationbyId(4)).thenReturn(null);
	         
	         mockMvc.perform(get("/tza/applications/{id}", 4))
            .andExpect(status().is(Constants.NOT_FOUND));
	        
	       // System.out.println("****"+result.getResponse().getContentAsString());
	     //   assertEquals(result.getResponse(), actual);
	        verify(applicationService, times(1)).findApplicationbyId(4);
	        verifyNoMoreInteractions(applicationService);
	    } 
	 
	 
	 public static String asJsonString(final Object obj) {
	        try {
	            final ObjectMapper mapper = new ObjectMapper();
	            return mapper.writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}

