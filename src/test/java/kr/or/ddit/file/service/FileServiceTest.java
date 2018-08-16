package kr.or.ddit.file.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import kr.or.ddit.common.DataInit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
		 "classpath:kr/or/ddit/config/spring/datasource_dev.xml",
		 "classpath:kr/or/ddit/config/spring/transaction.xml"})
public class FileServiceTest {
	
	@Resource(name="fileService")
	private FileServiceInf fileService;
	
	@Before
	public void setup(){
		new DataInit().setup();
	}

	@Test
	public void fileServiceBeanTest() {
		assertNotNull(fileService);
	}

}
