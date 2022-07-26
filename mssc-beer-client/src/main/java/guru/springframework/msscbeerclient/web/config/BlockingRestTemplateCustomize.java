package guru.springframework.msscbeerclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component

public class BlockingRestTemplateCustomize implements RestTemplateCustomizer {

    private final Integer maxTotal;
    private final Integer defMaxPerRoute;
    private final Integer connReqTimeOut;
    private final Integer socketTimeout;

    public BlockingRestTemplateCustomize(@Value("${sfg.http.config.maxtotal}") Integer maxTotal,
                                         @Value("${sfg.http.config.defmaxperroute}") Integer defMaxPerRoute,
                                         @Value("${sfg.http.config.connreqtimeout}") Integer connReqTimeOut,
                                         @Value("${sfg.http.config.sockettimeout}") Integer socketTimeout) {
        this.maxTotal = maxTotal;
        this.defMaxPerRoute = defMaxPerRoute;
        this.connReqTimeOut = connReqTimeOut;
        this.socketTimeout = socketTimeout;
    }
    public ClientHttpRequestFactory clientHttpRequestFactory(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(defMaxPerRoute);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(connReqTimeOut)
                .setSocketTimeout(socketTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
