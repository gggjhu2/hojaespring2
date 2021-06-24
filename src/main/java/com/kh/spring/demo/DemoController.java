package com.kh.spring.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.demo.model.service.DemoService;

/*
 * 
 * 사용자 요청 하나당 이를 처리하는 Contoller 메소드가 하나씩존재
 * 						   (매소드이름handler)		
 * 
 */

@Controller
public class DemoController {
	
	
	private static final Logger log=LoggerFactory.getLogger(DemoController.class);
	
	
	
	
	
	@Autowired
	private DemoService demoService;
	
	
	/*
	 * 사용자 요청을 처리하는 handler
	 * @return
	 * 
	 */
	@RequestMapping("/demo/devForm.do")
	public String devForm() {
		System.out.println("/demo/devForm.do요청! ");
		//VIEWrESOLVER 빈에 의해서 /WEB-INF/views+ demo/devForm+.jsp  jsp파일로위임
		return"";
	}
	
}
