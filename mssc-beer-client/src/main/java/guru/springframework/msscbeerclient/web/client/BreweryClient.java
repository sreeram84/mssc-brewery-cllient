package guru.springframework.msscbeerclient.web.client;

import guru.springframework.msscbeerclient.web.model.BeerDto;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery",ignoreUnknownFields = false)
public class BreweryClient {

    public final  String BEER_PATH_V1 = "/api/v1/beer/";
    private String apihost;

    private RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder.build();
    }

    public void setApihost(String apihost) {

        this.apihost = apihost;
    }

    public BeerDto getBeerObject(UUID beerId){
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + beerId.toString(),BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto){
        return restTemplate.postForLocation(apihost + BEER_PATH_V1,beerDto);
    }
}
