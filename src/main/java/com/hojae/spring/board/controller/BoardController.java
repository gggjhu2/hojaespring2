package com.hojae.spring.board.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
=======
import org.springframework.validation.BindingResult;
>>>>>>> 4719bc0d0c0f88a77d708112528a8bd5909be1c7
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@Autowired
	private ResourceLoader resourceLoader;

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
	public void selectOne(@RequestParam int no, Model model) {
		log.info("board/selectOne호출!넘어온값=" + no);
		log.info("");
		
		// board 1행 조회
		Board board = boardService.selectOne(no);
		
		// attachment n행 조회
		List<Attachment> attachList = boardService.selectAttachList(no);

		
		log.info("\nboard = {}", board);
		log.info("\nattachList= {}", attachList);
		model.addAttribute("attachList",attachList);
		model.addAttribute("board", board);

	}

<<<<<<< HEAD
	// 인서트 보드

=======
	
	
>>>>>>> 4719bc0d0c0f88a77d708112528a8bd5909be1c7
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
<<<<<<< HEAD

	// 보드인롤

	@PostMapping("/board/updateBoard.do")
	public String updateBoard(Board board, RedirectAttributes redirectAttr) {
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

//	@GetMapping("/selectBoardList.do")
//	public String boardList(
//				@RequestParam(required = true, defaultValue = "1") int cpage,
//				HttpServletRequest request,
//				Model model
//			) {
//		try {
//			log.debug("cpage = {}", cpage);
//			final int limit = 10;
//			final int offset = (cpage - 1) * limit;
//			Map<String, Object> param = new HashMap<>();
//			param.put("limit", limit);
//			param.put("offset", offset);
//			//1.업무로직 : content영역 - Rowbounds
//			List list = (List) boardService.selectBoardList(param);
//			
//			String url = request.getRequestURI();
//			
//			//2. jsp에 위임
//			model.addAttribute("list", list);
//		} catch(Exception e) {
//			log.error("게시글 조회 오류!", e);
//			throw e;
//		}
//		return "board/selectBoardList";
//	}
	
	
	//TODO:1.이거 꼭 다시정리해라
//	@PostMapping("/board/insertBoard.do")
//	public String insertBoard(@ModelAttribute BoardExt board, @RequestParam(name = "upFile") MultipartFile[] upFiles,
//			RedirectAttributes redirecttr) throws Exception
//
//	{
//		log.info("board = {}", board);
//
//
//		// 1. 파일 저장 : 
//		
//		// 절대경로 /resources/upload/board
//	
//		// 반복문을 통해 첨부파일 하나씩 처리
//		
//			// a.서버컴퓨터에 저장
//	
//			// b.저장된 데이터를 Attachment객체에 저장 및 list에 추가
//	
//		
//		// List<Attachment> board객체에 설정
//
//		// 2. 업무로직 : db저장 board, attachment
//		
//
//		// 3. 사용자피드백 & 리다이렉트
//			
//		return "redirect:/board/selectOne.do?no=" + no;
//	}
//
//// 파일이름 다시지어주는 과정?

// <attachment> attach 에 오리지날네임 넣기 리네임파일하고
// 이거를 에드 어태치 해서 attachlist에 넣기.

//
	
	@PostMapping("/board/insertBoard.do")
	public String insertBoard(
			@ModelAttribute BoardExt board,
			@RequestParam(name = "upFile") MultipartFile[] upFiles,
			RedirectAttributes redirectAttr)  throws Exception
	{
		log.info("board = {}", board);
		
		// 0. 업로드한 파일 처리
		for(MultipartFile f : upFiles) {
			if(!f.isEmpty()) {
				log.debug("f = {}", f);}
		}
			
			
		try {
				log.debug("board = {}", board);
				//1. 파일 저장 : 절대경로 /resources/upload/board
				//pageContext:PageContext - request:HttpServletRequest - session:HttpSession - application:ServletContext
				String saveDirectory = application.getRealPath("/resources/upload/board");
				log.debug("saveDirectory = {}", saveDirectory);
				
				//디렉토리 생성
				File dir = new File(saveDirectory);
				if(!dir.exists())
					dir.mkdirs(); // 복수개의 디렉토리를 생성
				
				List<Attachment> attachList = new ArrayList<>();
				
				for(MultipartFile upFile : upFiles) {
					//input[name=upFile]로부터 비어있는 upFile이 넘어온다.
					if(upFile.isEmpty()) continue;
					
					String renamedFilename = 
							HelloSpringUtils.getRenamedFilename(upFile.getOriginalFilename());
					
					//a.서버컴퓨터에 저장
					File dest = new File(saveDirectory, renamedFilename);
					upFile.transferTo(dest); // 파일이동
					
					//b.저장된 데이터를 Attachment객체에 저장 및 list에 추가
					Attachment attach = new Attachment();
					attach.setOriginalFilename(upFile.getOriginalFilename());
					attach.setRenamedFilename(renamedFilename);
					attachList.add(attach);
				}
				
				log.debug("attachList = {}", attachList);
				//board객체에 설정
				board.setAttachList(attachList);
				
				//2. 업무로직 : db저장 board, attachment
				int result = boardService.insertBoard(board);
				
				
				//3. 사용자피드백 &  리다이렉트
				redirectAttr.addFlashAttribute("msg", "게시글등록 성공!");
			} catch(Exception e) {
				log.error("게시글 등록 오류!", e);
				throw e;
			}
		
		return "redirect:/board/selectOne.do?no="+board.getNo();
	}
=======
	
	//보드인롤
>>>>>>> 4719bc0d0c0f88a77d708112528a8bd5909be1c7

	@GetMapping("/board/fileDownload.do")
	@ResponseBody // 리턴객체를 응답메세지 body영역에 작성한다.
	public Resource fileDownloadWithResponseEntity(@RequestParam int no, HttpServletResponse response)
			throws UnsupportedEncodingException {
		
		// 1. 업무로직 : db조회 - renamedFilename 저장된 이름 알아내기
		Attachment attach = boardService.selectOneAttachment(no);

<<<<<<< HEAD
		// 2. Resource객체생성
		String saveDirectory = application.getRealPath("/resources/upload/board");
		File downFile = new File(saveDirectory, attach.getRenamedFilename());
		Resource resource = resourceLoader.getResource("file:" + downFile);
		String filename = new String(attach.getOriginalFilename().getBytes("utf-8"), "iso-8859-1"); // 한글 깨짐 방지용

		// 3.응답헤더 작성
		response.setContentType("application/octet-stream; charset=utf-8"); // 이진데이터 전송 명시
		response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename);
		return resource;
	}
}

=======
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
	
	
		
	
	
	
			
>>>>>>> 4719bc0d0c0f88a77d708112528a8bd5909be1c7
