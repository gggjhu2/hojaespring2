package com.hojae.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.hojae.spring.board.model.vo.Attachment;
import com.hojae.spring.board.model.vo.Board;
import com.hojae.spring.board.model.vo.BoardExt;


@Repository
public interface BoardDao {

	List<Board> selectBoardList();
	
	Board selectOne(int no);

	int updateBoard(Board board);

	int deleteBoard(Board board);

	int boardEnroll(Board board);

	int insertBoard(BoardExt board);
	int insertAttachment(Attachment attach);

<<<<<<< HEAD
	List<Attachment> selectAttachList(int no);
=======
	int insertBoard(Board board);

>>>>>>> 4719bc0d0c0f88a77d708112528a8bd5909be1c7

	Attachment selectOneAttachment(int no);
	

}
