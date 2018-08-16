package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.BoardDaoInf;
import kr.or.ddit.model.BoardVo;
import kr.or.ddit.model.PostsVo;

@Service("boardService")
public class BoardService implements BoardServiceInf {
	@Resource(name="boardDao")
	private BoardDaoInf bDao;

	@Override
	public int insertBoard(BoardVo board) {
		return bDao.insertBoard(board);
	}

	@Override
	public int updateBoard(BoardVo board) {
		return bDao.updateBoard(board);
	}

	@Override
	public Map<String, Object> getPostsList(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<PostsVo> postsList = bDao.getPostsList(map);
		resultMap.put("postsList", postsList);
		BoardVo board = (BoardVo) map.get("board");
		resultMap.put("pageNavi", makePageNavi(board.getBoard_id(), (int)map.get("page"), (int)map.get("pageSize"), board.getBoard_title()));
		
		return resultMap;
	}
	
	private String makePageNavi(int board_id, int page, int pageSize, String board_title){
		StringBuffer pageNaviStr = new StringBuffer();
		int count=1;
		int pageCnt = bDao.getPostsCnt(board_id);
		int temp = pageCnt;
		
		pageNaviStr.append("<li><a href=\"/board/viewBoard?board_title="+board_title+"&board_id="+board_id+"&page="+1+"&pageSize="+pageSize+"\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;&laquo;</span></a></li>");
		pageNaviStr.append("<li><a href=\"/board/viewBoard?board_title="+board_title+"&board_id="+board_id+"&page="+(page==1?1:page-1)+"&pageSize="+pageSize+"\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>");
		while(true){
			if(temp>0){
				temp-=pageSize;
				pageNaviStr.append((count==page?"<li class=\"active\">":"<li>")+"<a href=/board/viewBoard?board_title="+board_title+"&board_id="+board_id+"&page="+count+"&pageSize="+pageSize+">"+count+++"</a></li>");
				continue;
			}
			pageNaviStr.append("<li><a href=\"/board/viewBoard?board_title="+board_title+"&board_id="+board_id+"&page="+(page==count-1?page:page+1)+"&pageSize="+pageSize+"\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li>");
			pageNaviStr.append("<li><a href=\"/board/viewBoard?board_title="+board_title+"&board_id="+board_id+"&page="+(count-1)+"&pageSize="+pageSize+"\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;&raquo;</span></a></li>");
			return pageNaviStr.toString();
		}
	}

}
