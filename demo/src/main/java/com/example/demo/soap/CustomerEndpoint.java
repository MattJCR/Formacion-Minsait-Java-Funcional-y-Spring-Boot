package com.example.demo.soap;

import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetCustomerSoapResponse;
import io.spring.guides.gs_producing_web_service.PostCustomerSoapRequest;
import io.spring.guides.gs_producing_web_service.PostCustomerSoapResponse;
import io.spring.guides.gs_producing_web_service.CustomerSoap;
import io.spring.guides.gs_producing_web_service.DeleteCustomerSoapRequest;
import io.spring.guides.gs_producing_web_service.DeleteCustomerSoapResponse;
import io.spring.guides.gs_producing_web_service.GetCustomerSoapRequest;


@Endpoint
public class CustomerEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    
    @Autowired
    private CustomerRepository customerRepository;
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCustomerSoapRequest")
    @ResponsePayload
    public GetCustomerSoapResponse getCustomerSoap(@RequestPayload GetCustomerSoapRequest request) {
        System.out.println("CustomerEndpoint.getCustomer()");
        GetCustomerSoapResponse response = new GetCustomerSoapResponse();
        Customer bdCustomer = customerRepository.findById(request.getId());
        CustomerSoap customerSoap = new CustomerSoap();
        customerSoap.setFirstName(bdCustomer.getFirstName());
        customerSoap.setLastName(bdCustomer.getLastName());
        customerSoap.setEmail(bdCustomer.getEmail());
        customerSoap.setId(bdCustomer.getId());
        response.setCustomerSoap(customerSoap);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCustomerSoapRequest")
    @ResponsePayload
    public DeleteCustomerSoapResponse deleteCustomerSoap(@RequestPayload DeleteCustomerSoapRequest request) {
        System.out.println("CustomerEndpoint.deleteCustomer()");
        DeleteCustomerSoapResponse response = new DeleteCustomerSoapResponse();
        try {
            customerRepository.deleteById(request.getId());
            response.setId(request.getId());
        } catch (Exception e) {
            //TODO: handle exception
            response.setId(-1L);
        }
        
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postCustomerSoapRequest")
    @ResponsePayload
    public PostCustomerSoapResponse postCustomerSoap(@RequestPayload PostCustomerSoapRequest request) {
        System.out.println("CustomerEndpoint.postCustomer()");
        PostCustomerSoapResponse response = new PostCustomerSoapResponse();
        Customer customer = customerRepository.save(new Customer(request.getFirstName(), request.getLastName(), request.getEmail()));
        CustomerSoap customerSoap = new CustomerSoap(); 
        customerSoap.setId(customer.getId());
        customerSoap.setFirstName(customer.getFirstName());
        customerSoap.setLastName(customer.getLastName());
        customerSoap.setEmail(customer.getEmail());
        response.setCustomerSoap(customerSoap);
        return response;
    }
    
}
