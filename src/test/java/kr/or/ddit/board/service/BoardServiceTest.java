package kr.or.ddit.board.service;

import static org.junit.Assert.*;

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
public class BoardServiceTest {
	
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	@Before
	public void setup(){
		new DataInit().setup();
	}

	@Test
	public void boardServiceBeanTest() {
		assertNotNull(boardService);
	}
	
	@Test
	public void insertBoardTest(){
		BoardVo board = new BoardVo();
		board.setBoard_title("test4");
		board.setBoard_status("Y");
		
		int cnt = boardService.insertBoard(board);
		
		assertEquals(1, cnt);
	}
	
	@Test
	public void updateBoardTest(){
		BoardVo board = new BoardVo();
		board.setBoard_title("update");
		board.setBoard_id(3);
		board.setBoard_status("N");
		
		int cnt = boardService.updateBoard(board);
		
		assertEquals(1, cnt);
	}

}
	