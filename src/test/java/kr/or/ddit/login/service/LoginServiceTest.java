package kr.or.ddit.login.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.common.DataInit;
import kr.or.ddit.model.BoardVo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
								 "classpath:kr/or/ddit/config/spring/datasource_dev.xml",
								 "classpath:kr/or/ddit/config/spring/transaction.xml"})
public class LoginServiceTest {
	
	@Resource(name="loginService")
	private LoginServiceInf loginService;
	
	@Before
	public void setup(){
		new DataInit().setup();
	}
	
	@Test
	public void loginServiceBeanTest(){
		assertNotNull(loginService);
	}

	@Test
	public void getStudentTest() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("std_id", "20");
		map.put("pass", "1234");
		
		boolean b = loginService.getStudent(map);
		
		assertTrue(b);
	}
	
	@Test
	public void getBoardListTest(){
		List<BoardVo> boardList = loginService.getBoardList();
		assertEquals(3, boardList.size());
	}

}
