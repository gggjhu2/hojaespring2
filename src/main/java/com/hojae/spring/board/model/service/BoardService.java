package com.hojae.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.hojae.spring.board.model.dao.BoardDao;
import com.hojae.spring.board.model.vo.Attachment;
import com.hojae.spring.board.model.vo.Board;
import com.hojae.spring.board.model.vo.BoardExt;



@Service
public interface BoardService {
	
	
	
	List<Board> selectBoardList();
	
	Board selectOne(int no);

	
	int updateBoard(Board board);

	int deleteBoard(Board board);

	int boardEnroll(Board board);

<<<<<<< HEAD
	List<Attachment> selectAttachList(int no);

	int insertBoard(BoardExt board);
	int insertAttachment(Attachment attach);

	Attachment selectOneAttachment(int no);
	


=======
	int insertBoard(BoardExt board);
	int insertAttachment(Attachment attach);
>>>>>>> 4719bc0d0c0f88a77d708112528a8bd5909be1c7
	
}
