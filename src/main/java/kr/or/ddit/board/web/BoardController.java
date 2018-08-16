package kr.or.ddit.board.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.login.service.LoginServiceInf;
import kr.or.ddit.model.BoardVo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	@Resource(name="loginService")
	private LoginServiceInf loginService;
	
	@RequestMapping("/createBoard")
	public String createBoard(){
		return "createBoard";
	}
	
	@RequestMapping("/insertBoard")
	public String insertBoard(@RequestParam("buttonType") String buttonType, BoardVo board, HttpSession session) throws UnsupportedEncodingException{
		board.setBoard_title(new String(board.getBoard_title().getBytes("8859_1"),"UTF-8"));
		if(buttonType.equals("insert")){
			boardService.insertBoard(board);
		}else if(buttonType.equals("update")){
			boardService.updateBoard(board);
		}
		session.setAttribute("boardList", loginService.getBoardList());
		return "createBoard";
	}
	
	@RequestMapping("/viewBoard")
	public String viewBoard(@RequestParam(value = "page", defaultValue = "1") int page,
							@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, 
							BoardVo board, Model model) throws UnsupportedEncodingException{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page==0?1:page);
		map.put("pageSize", pageSize==0?10:pageSize);
		board.setBoard_title(new String(board.getBoard_title().getBytes("8859_1"),"UTF-8"));
		map.put("board", board);
		System.out.println(boardService.getPostsList(map));
		model.addAllAttributes(boardService.getPostsList(map));
		model.addAttribute("board", board); //안하면 안넘어감..값이...
		return "viewBoard";
	}
	
}
