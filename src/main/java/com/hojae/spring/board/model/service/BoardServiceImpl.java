package com.hojae.spring.board.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hojae.spring.board.model.dao.BoardDao;
import com.hojae.spring.board.model.vo.Attachment;
import com.hojae.spring.board.model.vo.Board;
import com.hojae.spring.board.model.vo.BoardExt;

import jdk.internal.org.jline.utils.Log;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	@Override
	public List<Board> selectBoardList() {

		return boardDao.selectBoardList();
	}

	@Override
	public Board selectOne(int no) {
		return boardDao.selectOne(no);
	}

	

	@Override
	public int updateBoard(Board board) {
		// TODO Auto-generated method stub
		return boardDao.updateBoard(board);
	}

	@Override
	public int deleteBoard(Board board) {
		// TODO Auto-generated method stub
		return boardDao.deleteBoard(board);
	}

	@Override
	public int boardEnroll(Board board) {
		// TODO Auto-generated method stub
		return boardDao.boardEnroll(board);
	}

	@Override
	public int insertBoard(BoardExt board) {
		int result=0;
		
		//보드등록
		//dml실행후 리턴된후 리턴된 정수는 몇행인지 나타내는것이다=>인서트후 1이나오면 1행이들어갔다는뜻
		result=boardDao.insertBoard(board);
		
		Log.debug("board={}",board);
		
		//attachment등록
		if(board.getAttachList().size()>0) {
			for(Attachment attach:board.getAttachList()) {
				attach.setBoardNo(board.getNo()); //board no fk 세팅
				result=insertAttachment(attach);
			}
		}
		
		
		
		return result;
	}

	@Override
	public int insertAttachment(Attachment attach) {
		// TODO Auto-generated method stub
		return boardDao.insertAttachment(attach);
	}

	
	
}
