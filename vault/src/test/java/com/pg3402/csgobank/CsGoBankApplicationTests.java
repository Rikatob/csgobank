package com.pg3402.csgobank;

import static org.assertj.core.api.Assertions.assertThat;

import com.pg3402.csgobank.vault.VaultController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CsGoBankApplicationTests {


	@Autowired
	private VaultController controller;

	/**
	 * Verify controller and services context.
	 * @throws Exception
	 */
	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}


}
