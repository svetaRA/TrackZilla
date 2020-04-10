package com.ms.bugzilla;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertEquals;
//import static org.springframework.ws.test.client.RequestMatchers.*;
//import static org.springframework.ws.test.client.ResponseCreators.*;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
//import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringSource;

import com.ms.bugzilla.loaneligibility.Acknowledgement;
import com.ms.bugzilla.loaneligibility.CustomerRequest;
import com.ms.bugzilla.soapclient.SoapClinet;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SoapIntegrationTest {

	 @Autowired
	 private SoapClinet client;
	 //private MockWebServiceServer mockServer;

    private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    @LocalServerPort private int port = 0;
    
	/*
	 * @Before public void createServer() throws Exception { mockServer =
	 * MockWebServiceServer.createServer(client); }
	 */
  

    @Before
    @Ignore
    public void init() throws Exception {
        marshaller.setPackagesToScan(ClassUtils.getPackageName(CustomerRequest.class));
        marshaller.afterPropertiesSet();
    }

    @Test
    @Ignore
    public void whenSendRequest_thenResponseIsNotNull() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        CustomerRequest request = new CustomerRequest();
        request.setCustomerName("Sam");
        request.setAge(35);
        request.setYearlyIncome(500000);
        request.setCibilScore(700);
        request.setEmploymentMode("govt");
        

        assertThat(ws.marshalSendAndReceive("http://localhost:" + "9090" + "/ws", request)).isNotNull();
        
        
        
    }
    
    
   
    

    
	/*
	 * @Test public void test_getloanStatus() throws Exception {
	 * 
	 * 
	 * WebServiceTemplate ws = new WebServiceTemplate(marshaller); CustomerRequest
	 * request = new CustomerRequest(); request.setCustomerName("Sam");
	 * request.setAge(35); request.setYearlyIncome(500000);
	 * request.setCibilScore(700); request.setEmploymentMode("govt");
	 * 
	 * Source expectedRequestPayload = new
	 * StringSource("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\r\n"
	 * +
	 * "                  xmlns:us=\"http://www.javatechie.com/spring/soap/api/loanEligibility\">\r\n"
	 * + "    <soapenv:Header/>\r\n" + "    <soapenv:Body>\r\n" +
	 * "        <us:CustomerRequest>\r\n" +
	 * "            <us:customerName>Sam</us:customerName>\r\n" +
	 * "            <us:age>35</us:age>\r\n" +
	 * "            <us:yearlyIncome>500000</us:yearlyIncome>\r\n" +
	 * "            <us:cibilScore>500</us:cibilScore>\r\n" +
	 * "            <us:employmentMode>govt</us:employmentMode>\r\n" +
	 * "            \r\n" + "        </us:CustomerRequest>\r\n" +
	 * "    </soapenv:Body>\r\n" + "</soapenv:Envelope>"); Source responsePayload =
	 * new
	 * StringSource("<customerCountResponse xmlns='http://springframework.org/spring-ws/test'>"
	 * + "<customerCount>10</customerCount>" + "</customerCountResponse>");
	 * 
	 * mockServer.expect(payload(expectedRequestPayload)).andRespond(withPayload(
	 * responsePayload));
	 * 
	 * // client.getCustomerCount() uses the WebServiceTemplate Acknowledgement
	 * response = client.getLoanStatus(request); assertEquals(10,
	 * response.getApprovedAmount());
	 * 
	 * mockServer.verify(); }
	 */
}
