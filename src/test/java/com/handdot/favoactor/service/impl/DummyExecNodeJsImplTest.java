package com.handdot.favoactor.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.handdot.favoactor.service.ifs.ExecNodeJsService;
@SpringBootTest
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
public class DummyExecNodeJsImplTest {

	@Autowired
	ExecNodeJsService execNodeJs;
	@Test
	public void test() {
		execNodeJs.setUp();
		execNodeJs.execute();
	}

}
