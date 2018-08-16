package kr.or.ddit.login.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.model.BoardVo;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("loginDao")
public class LoginDao implements LoginDaoInf{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public boolean getStudent(Map<String, String> map) {
		return (int)template.selectOne("board.getStudent", map)==1?true:false;
	}

	@Override
	public List<BoardVo> getBoardList() {
		return template.selectList("board.getBoardList");
	}

}
