package com.skyou.config;

import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class GeneralConfig {

	//TODO: Here must be configuration for connection pooling
	@Bean
	public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

		HttpComponentsClientHttpRequestFactory requestFactory =
				new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(buildHttpClient());
		return new RestTemplate(requestFactory);
	}

	private HttpClient buildHttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		SSLContext context = SSLContexts.custom()
				.loadTrustMaterial(TrustSelfSignedStrategy.INSTANCE)
				.build();

		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE))
				.build();

		return HttpClients.custom()
				.setConnectionManager(buildConnectionManager(registry))
				.build();
	}

	private PoolingHttpClientConnectionManager buildConnectionManager(Registry<ConnectionSocketFactory> registry) {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
		connectionManager.setMaxTotal(100);
		connectionManager.setDefaultMaxPerRoute(10);
		return connectionManager;
	}
}