package com.skyou.actuator;

import com.skyou.AbstractBaseIT;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ActuatorSecurityTest extends AbstractBaseIT {

	@Test
	public void testHealthIsUnsecured() throws Exception {
		mockMvc.perform(get("/actuator/health"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("UP"))
				.andExpect(jsonPath("$.length()").value(1L));
	}

	@Test
	public void testInfoEndpoint() throws Exception {
		mockMvc.perform(get("/actuator/info"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.git.length()").value(2L))
				.andExpect(jsonPath("$.git.branch").isNotEmpty())
				.andExpect(jsonPath("$.git.commit.id").isNotEmpty())
				.andExpect(jsonPath("$.git.commit.time").isNotEmpty());

	}
}