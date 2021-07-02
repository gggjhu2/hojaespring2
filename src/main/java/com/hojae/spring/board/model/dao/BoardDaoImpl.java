package com.hojae.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hojae.spring.board.model.vo.Attachment;
import com.hojae.spring.board.model.vo.Board;
import com.hojae.spring.board.model.vo.BoardExt;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<Board> selectBoardList() {
		return session.selectList("board.selectBoardList");
	}

	@Override
	public Board selectOne(int no) {
		return session.selectOne("board.selectOne", no);

	}

	@Override
	public int updateBoard(Board board) {
		return session.update("board.updateBoard", board);
	}

	@Override
	public int deleteBoard(Board board) {
		// TODO Auto-generated method stub
		return session.delete("board.deleteBoard", board);
	}

	@Override
	public int boardEnroll(Board board) {
		// TODO Auto-generated method stub
		return session.insert("board.boardEnroll", board);
	}

	@Override
	public int insertAttachment(Attachment attach) {
		// TODO Auto-generated method stub
		return session.insert("board.insertAttachment", attach);
	}

	@Override
	public int insertBoard(Board board) {
		// TODO Auto-generated method stub
		return session.insert("board.insertBoard",board);
	}

	

}
