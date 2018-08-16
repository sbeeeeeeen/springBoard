package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.common.DataInit;
import kr.or.ddit.model.BoardVo;
import kr.or.ddit.model.PostsVo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
								 "classpath:kr/or/ddit/config/spring/datasource_dev.xml",
								 "classpath:kr/or/ddit/config/spring/transaction.xml"})
public class BoardDaoTest {
	
	@Resource(name="boardDao")
	private BoardDaoInf boardDao;
	
	@Before
	public void setup(){
		new DataInit().setup();
	}

	@Test
	public void boardDaoBeanTest() {
		assertNotNull(boardDao);
	}
	
	@Test
	public void insertBoardTest(){
		BoardVo board = new BoardVo();
		board.setBoard_title("test4");
		board.setBoard_status("Y");
		
		int cnt = boardDao.insertBoard(board);
		
		assertEquals(1, cnt);
	}
	
	@Test
	public void updateBoardTest(){
		BoardVo board = new BoardVo();
		board.setBoard_title("update");
		board.setBoard_id(3);
		board.setBoard_status("N");
		
		int cnt = boardDao.updateBoard(board);
		
		assertEquals(1, cnt);
	}
	
	@Test
	public void getPostsListTest(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", 1);
		map.put("pageSize", 10);
		
		BoardVo board = new BoardVo();
		board.setBoard_id(1);
		
		map.put("board", board);
		
		List<PostsVo> postsList = boardDao.getPostsList(map);
		
		assertEquals(postsList.size(), 3);
	}

}

