package guru.springframework.msscbeerclient.web.client;

import guru.springframework.msscbeerclient.web.model.BeerDto;
import guru.springframework.msscbeerclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest

class CustomerClientTest {

    @Autowired
    CustomerClient customerClient;

    @Test
    void testGetCustomer() {
        CustomerDto customerDto = customerClient.getCustomer(UUID.randomUUID());

        assertNotNull(customerDto);

    }

    @Test
    void testSaveNewCustomer(){
        CustomerDto customerDto = CustomerDto.builder().name("Pam").build();
        URI uri = customerClient.saveNewCustomer(customerDto);
        assertNotNull(uri);
        System.out.println(uri.toString());

    }

    @Test
    void testUpdateCustomer(){
        CustomerDto customerDto =  CustomerDto.builder().name("Sam").build();
        customerClient.updateCustomer(UUID.randomUUID(),customerDto);

    }

    @Test
    void deleteCustomer() {
        customerClient.deleteById(UUID.randomUUID());
    }



}
