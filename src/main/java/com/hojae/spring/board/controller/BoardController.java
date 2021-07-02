package com.hojae.spring.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hojae.spring.board.model.service.BoardService;
import com.hojae.spring.board.model.vo.Attachment;
import com.hojae.spring.board.model.vo.Board;
import com.hojae.spring.board.model.vo.BoardExt;
import com.hojae.spring.common.util.HelloSpringUtils;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

	@Autowired
	private ServletContext application;
	
	@Autowired
	private BoardService boardService;

	@RequestMapping("/board/selectBoardList.do")
	public String board(Model model) {

		log.info("board/selectBoardList.do !호출성공");
		List<Board> list = boardService.selectBoardList();
		model.addAttribute("list", list);

		return "board/selectBoardList";

	}

//	@Autowired
//	private BoardService boardService;
//
//	@RequestMapping("/board/selectBoardList.do")
//	public String board(Model model) {
//		
//		log.info("board/selectBoardList.do!!호출!");
//		List<Board> list = boardService.selectBoardList();
//		
//		model.addAttribute("list", list);
//		return "board/selectBoardList";
//	}

//
//	@RequestMapping("/board/selectOne.do")
//	public void selectOne(@RequestParam int no) {
//		log.info("board/selectOne호출!넘어온값=" + no);
//		log.info("");
//		Board board = boardService.selectOne(no);
//		log.info("\nboard = {}", board);

	@RequestMapping("/board/boardForm.do")
	public String boardForm(Model model) {

		log.info("board/selectBoardList.do !호출성공");
		List<Board> list = boardService.selectBoardList();
		model.addAttribute("list", list);

		return "board/boardForm";

	}
	
	
	@RequestMapping("/board/selectOne.do")
	public void selectOne(@RequestParam int no ,Model model) {
		log.info("board/selectOne호출!넘어온값=" + no);
		log.info("");
		Board board = boardService.selectOne(no);
		log.info("\nboard = {}", board);
		
		model.addAttribute("board", board);
	
	}
		

	
	
//	@RequestMapping("/board/boardEnroll.do")
//	public String boardEnroll(Board board, RedirectAttributes redirectAttr) {
//		log.info("board = {}", board);
//
//		// 1. db에 insert
//		int result = boardService.boardEnroll(board);
//		// 2. 새로 발급된 pk 출력
//		log.info("=======================");
//		log.info("insert 이후 board = {}", board);
//		log.info("insert 이후 result = {}", result);
//		return "redirect:/board/selectBoardList.do";
//	}
	
	//보드인롤

	@PostMapping("/board/updateBoard.do")
	public String updateBoard(Board board,RedirectAttributes redirectAttr) {
		log.info("board = {}", board);

		int result = boardService.updateBoard(board);

		log.info("=======================");
		log.info("update 이후 board = {}", board);

		return "redirect:/board/selectOne.do?no=" + board.getNo();
	}
	// 업데이트보드

	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(Board board, RedirectAttributes redirectAttr) {
		log.info("board = {}", board);
		
		int result = boardService.deleteBoard(board);

		log.info("=======================");
		log.info("delete 이후 board = {}", board);
		log.info("delete 이후 result = {}", result);
		
		redirectAttr.addFlashAttribute("msg", "게시글 삭제 성공");
		return "redirect:/board/selectBoardList.do";

	}
	// 딜리트완성

	
	@PostMapping("/board/insertBoard")
	public String insertBoard(@ModelAttribute BoardExt board,
			@RequestParam (name="upFile")MultipartFile[]upFiles,
			RedirectAttributes redirectAttr) throws Exception
	{
		log.info("board={}",board);
		//보드잘들어왔는지 로그출력
		
		//업로드한 파일처리
		for(MultipartFile f : upFiles) {
			if(!f.isEmpty()) {
				log.debug("f={}",f);}
			}
		
		try {
			log.debug("board={}",board);
			//파일저장 :절대경로 /resources/upload/board
			String saveDirectory=application.getRealPath("/resources/upload/board");
			
			log.debug("saveDirectory={}",saveDirectory);
			
			//디렉토리생성
			File dir=new File(saveDirectory);
			if(dir.exists())
				dir.mkdirs();//복수개의 디렉토리를 생성
			
			
			List<Attachment> attachList=new ArrayList<>();
			
			
			
			for(MultipartFile upFile:upFiles) {
				//input[name=upFile]로부터 비어있는 upFile 이 넘어온다.
				if(upFile.isEmpty()) continue;
				
				String renamedFilename=HelloSpringUtils.getRenamedFilename(upFile.getOriginalFilename());
				
			//서버컴퓨터에 저장
				File dest= new File(saveDirectory,renamedFilename);
			upFile.transferTo(dest);//파일이동
			
			
			//저장된 데이터를 어태치먼트 객체에 저장 및 list에 추가
			Attachment attach =new Attachment();
			attach.setOriginalFilename(upFile.getOriginalFilename());
			attach.setRenamedFilename(renamedFilename);
			attachList.add(attach);
			}
			
			
			log.debug("attachList={}",attachList);
			//board객체에 설정
			board.setAttachList(attachList);
			
			//업무로직 =>db저장 board, attachment
			int result= boardService.insertBoard(board);
			
			//사용자 피드백 &리다이렉트
			redirectAttr.addFlashAttribute("msg","게시글등록완료!");
		}catch (Exception e) {
			log.error("게시글등록실패!",e);
			throw e;
		}
		return "redirect:/board/selectBoardList.do?no=";
		}
	}
	
	
		
	
	
	
			
