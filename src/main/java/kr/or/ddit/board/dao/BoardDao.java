package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.model.BoardVo;
import kr.or.ddit.model.PostsVo;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("boardDao")
public class BoardDao implements BoardDaoInf {
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;

	@Override
	public int insertBoard(BoardVo board) {
		return template.insert("board.insertBoard", board);
	}

	@Override
	public int updateBoard(BoardVo board) {
		return template.update("board.updateBoard", board);
	}

	@Override
	public List<PostsVo> getPostsList(Map<String, Object> map) {
		return template.selectList("board.getPostsList", map);
	}

	@Override
	public int getPostsCnt(int board_id) {
		return template.selectOne("board.getPostsCnt", board_id);
	}

}
