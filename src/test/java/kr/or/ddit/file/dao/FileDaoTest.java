package kr.or.ddit.file.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import kr.or.ddit.common.DataInit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
		 "classpath:kr/or/ddit/config/spring/datasource_dev.xml",
		 "classpath:kr/or/ddit/config/spring/transaction.xml"})
public class FileDaoTest {
	
	@Resource(name="fileDao")
	private FileDaoInf fileDao;
	
	@Before
	public void setup(){
		new DataInit().setup();
	}

	@Test
	public void fileDaoBeanTest() {
		assertNotNull(fileDao);
	}
	
	

}
