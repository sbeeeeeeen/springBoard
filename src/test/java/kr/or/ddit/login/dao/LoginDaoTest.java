package kr.or.ddit.login.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

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
public class LoginDaoTest {
	
	@Resource
	private LoginDaoInf loginDao;
	
	@Before
	public void setup(){
		new DataInit().setup();
	}
	
	@Test
	public void loginDaoBeanTest(){
		assertNotNull(loginDao);
	}

	@Test
	public void getStudentTest() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("std_id", "20");
		map.put("pass", "880371bc2f67b8c3e32fe54b9515ece8");
		
		boolean b = loginDao.getStudent(map);
		
		assertTrue(b);
	}
	
	@Test
	public void getBoardListTest(){
		List<BoardVo> boardList = loginDao.getBoardList();
		assertEquals(3, boardList.size());
	}

}
