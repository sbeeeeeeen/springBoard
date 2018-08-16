package kr.or.ddit.login.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.model.BoardVo;

public interface LoginServiceInf {
	/**
	* Method : getStudent
	* 최초작성일 : 2018. 7. 18.
	* 작성자 : PC16
	* 변경이력 :
	* @param std_id
	* @return
	* Method 설명 : 고 아이디랑 비번 해당하는게 있는가 검사
	*/
	boolean getStudent(Map<String, String> map);
	
	
	/**
	* Method : getBoardList
	* 최초작성일 : 2018. 7. 19.
	* 작성자 : PC16
	* 변경이력 :
	* @return
	* Method 설명 : Left에 넣을 리스트요...
	*/
	List<BoardVo> getBoardList();
}
