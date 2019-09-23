package com.skyou;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.skyou.config.ApplicationConfiguration;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.rules.ExpectedException.none;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = {Application.class},
		value = {"spring.jmx.enabled=true"}
)
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles("componentTest")
public abstract class AbstractBaseIT {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	private ApplicationConfiguration applicationConfiguration;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(wireMockConfig()
			.dynamicPort()
			.dynamicHttpsPort()
	);

	@Rule
	public ExpectedException expectedException = none();

	@Before
	public void beforeEachTest() {
		String httpsUrl = "https://localhost:" + wireMockRule.httpsPort();
		String newUrl = applicationConfiguration.getGithubEventsUrl().replace(applicationConfiguration.getGithubBaseUrl(), httpsUrl);
		applicationConfiguration.setGithubEventsUrl(newUrl);
	}

}