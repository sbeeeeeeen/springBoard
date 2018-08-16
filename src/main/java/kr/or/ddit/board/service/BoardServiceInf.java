package kr.or.ddit.board.service;

import java.util.Map;

import kr.or.ddit.model.BoardVo;

public interface BoardServiceInf {
	/**
	* Method : insertBoard
	* 최초작성일 : 2018. 7. 19.
	* 작성자 : PC16
	* 변경이력 :
	* @param board
	* @return
	* Method 설명 : 게시판 생성
	*/
	int insertBoard(BoardVo board);
	
	/**
	* Method : updateBoard
	* 최초작성일 : 2018. 7. 19.
	* 작성자 : PC16
	* 변경이력 :
	* @param board
	* @return
	* Method 설명 : 게시판 수정
	*/
	int updateBoard(BoardVo board);
	
	/**
	* Method : getPostsList
	* 최초작성일 : 2018. 7. 19.
	* 작성자 : PC16
	* 변경이력 :
	* @param board_id
	* @return
	* Method 설명 : 게시판의 게시글 리스트 가져오기
	*/
	Map<String, Object> getPostsList(Map<String, Object> map);
}
