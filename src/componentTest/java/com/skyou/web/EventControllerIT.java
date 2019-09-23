package com.skyou.web;

import com.skyou.AbstractBaseIT;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EventControllerIT extends AbstractBaseIT {

	@Resource
	private ApplicationContext applicationContext;

	@Test
	public void testGetEvents() throws Exception {
		String responseAsString = IOUtils.toString(applicationContext.getResource("classpath:github-events.json").getInputStream());
		Long pageSize = 10L;
		Long pageNumber = 1L;
		stubFor(get(urlEqualTo("/events?page=" + pageNumber + "&per_page=" + pageSize))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", APPLICATION_JSON_VALUE)
						.withBody(responseAsString)));
		mockMvc.perform(MockMvcRequestBuilders.get("/github/publicEvents")
				.param("pageNumber", pageNumber.toString())
				.param("pageSize", pageSize.toString()))
				.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk());
	}

}