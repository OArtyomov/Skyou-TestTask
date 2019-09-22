package com.skyou;

import com.skyou.Application;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

	@Rule
	public ExpectedException expectedException = none();


}