package com.handdot.favoactor.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.handdot.favoactor.service.ifs.CommandLineService;
@SpringBootTest
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
public class DummyCommandLineServiceImplTest implements CommandLineService{
	@Autowired
	DummyCommandLineServiceImpl dummyCommandLineService;

	@Test
	public void exceNodeLed() {
		dummyCommandLineService.exceNodeLed();
		dummyCommandLineService.exceNodeLed();
	}

}
