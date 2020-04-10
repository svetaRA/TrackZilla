package com.ms.bugzilla;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

//import com.ms.bugzilla.config.WebConfig;
import com.ms.bugzilla.entity.Application;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
/* @ContextConfiguration(classes = {WebConfig.class}) */
public class TzaControllerIntegrationTest {
	

    private static final String BASE_URI = "http://localhost:8080/tza/applications";
    private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    @Autowired
    private RestTemplate template;
    
    // =========================================== Get All Users ==========================================

    @Test
    @Ignore
    public void test_get_all_success(){
        ResponseEntity<Application[]> response = template.getForEntity(BASE_URI, Application[].class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
       // validateCORSHttpHeaders(response.getHeaders());
    }// =========================================== Get User By ID =========================================

    @Test
    @Ignore
    public void test_get_by_id_success(){
        ResponseEntity<Application> response = template.getForEntity(BASE_URI + "/1", Application.class);
        Application user = response.getBody();
        assertThat(user.getId(), is(new Long(1)));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
       // validateCORSHttpHeaders(response.getHeaders());
    }

    @Test
    @Ignore
    public void test_get_by_id_failure_not_found(){
        try {
            ResponseEntity<Application> response = template.getForEntity(BASE_URI + "/" + new Long(1000), Application.class);
            fail("should return 404 not found");
        } catch (HttpClientErrorException e){
        	
            assertThat(e.getStatusCode(), is(HttpStatus.NOT_FOUND));
            //validateCORSHttpHeaders(e.getResponseHeaders()); 
        }
    }
 // =========================================== CORS Headers===========================================

 		public void validateCORSHttpHeaders(HttpHeaders headers) {
 			assertThat(headers.getAccessControlAllowOrigin(), is("*"));
 			assertThat(headers.getAccessControlAllowHeaders(), hasItem("*"));
 			assertThat(headers.getAccessControlMaxAge(), is(3600L));
 			assertThat(headers.getAccessControlAllowMethods(),
 					hasItems(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.OPTIONS, HttpMethod.DELETE));
 		}
}
