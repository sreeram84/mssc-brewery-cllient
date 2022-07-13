package guru.springframework.msscbeerclient.web.client;

import guru.springframework.msscbeerclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient breweryClient;

    @Test
    void getBeerObject() {
        BeerDto beerDto = breweryClient.getBeerObject(UUID.randomUUID());

        assertNotNull(beerDto);

    }

    @Test
    void saveNewBeer(){
        BeerDto beerDto = BeerDto.builder().beerName("Natural Pale Ale").build();
        URI uri = breweryClient.saveNewBeer(beerDto);
        assertNotNull(uri);
        System.out.println(uri.toString());

    }
}