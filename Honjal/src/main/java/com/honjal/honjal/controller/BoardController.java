package com.honjal.honjal.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.honjal.honjal.model.ContentListDTO;
import com.honjal.honjal.model.ContentVO;
import com.honjal.honjal.service.ContentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {
	
	protected final ContentService contentService;
	
	@RequestMapping(value={"/notice",""}, method=RequestMethod.GET)
	public String notice(Model model) {
		List<ContentListDTO> list = contentService.menuContent("NOT");
		model.addAttribute("CONTENTS", list);
		model.addAttribute("BODY", "BOARD_MAIN");
		model.addAttribute("MENU", "NOTICE");
		return "home";
	}
	
	@RequestMapping(value={"/info",""}, method=RequestMethod.GET)
	public String info(Model model) {
		List<ContentListDTO> list = contentService.menuContent("INF");
		model.addAttribute("CONTENTS", list);
		model.addAttribute("BODY", "BOARD_MAIN");
		model.addAttribute("MENU", "INFO");
		return "home";
	}
	
	@RequestMapping(value={"/tip",""}, method=RequestMethod.GET)
	public String tip(Model model) {
		List<ContentListDTO> list = contentService.menuContent("TIP");
		model.addAttribute("CONTENTS", list);
		model.addAttribute("BODY", "BOARD_MAIN");
		model.addAttribute("MENU", "TIP");
		return "home";
	}
	
	@RequestMapping(value={"/interior",""}, method=RequestMethod.GET)
	public String interior(Model model) {
		List<ContentListDTO> list = contentService.menuContent("INT");
		model.addAttribute("CONTENTS", list);
		model.addAttribute("BODY", "BOARD_MAIN");
		model.addAttribute("MENU", "INTERIOR");
		return "home";
	}
	
	@RequestMapping(value={"/talk",""}, method=RequestMethod.GET)
	public String talk(Model model) {
		List<ContentListDTO> list = contentService.menuContent("TAL");
		model.addAttribute("CONTENTS", list);
		model.addAttribute("BODY", "BOARD_MAIN");
		model.addAttribute("MENU", "TALK");
		return "home";
	}
	
	@RequestMapping(value={"/review",""}, method=RequestMethod.GET)
	public String review(Model model) {
		List<ContentListDTO> list = contentService.menuContent("REV");
		model.addAttribute("CONTENTS", list);
		model.addAttribute("BODY", "BOARD_MAIN");
		model.addAttribute("MENU", "REVIEW");
		return "home";
	}
	
	@RequestMapping(value={"/qna",""}, method=RequestMethod.GET)
	public String qna(Model model) {
		List<ContentListDTO> list = contentService.menuContent("QNA");
		model.addAttribute("CONTENTS", list);
		model.addAttribute("BODY", "BOARD_MAIN");
		model.addAttribute("MENU", "QNA");
		return "home";
	}
	
	@RequestMapping(value="/{CAT}/write", method=RequestMethod.GET)
	public String write( @PathVariable("CAT") String cat,  Model model, HttpSession session) {
//		MemberVO memberVO = (MemberVO) session.getAttribute("MEMBER");
//		Integer member_num = memberVO.getMember_num();
		
		ContentVO contentVO = ContentVO.builder().member_num(1).member_nname("csy").build();
		
		model.addAttribute("CONTENT", contentVO);
		model.addAttribute("BODY", "WRITE");
		model.addAttribute("MENU",cat.toUpperCase());
		/*
		if(.equals("TIP")) {
			model.addAttribute("MENU","TIP");
		} else if(.equals("TAL")) {
			model.addAttribute("MENU","TALK");
		} else if(.equals("REV")) {
			model.addAttribute("MENU","REVIEW");
		}
		*/
		return "home";
	}
	
	@RequestMapping(value="/{CAT}/write", method=RequestMethod.POST)
	public String write(String category, HttpSession session, ContentVO contentVO) throws Exception {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("hh:mm:ss");
		String curDate = sd.format(date);
		String curTime = st.format(date);
		
//		contentVO = ContentVO.builder().board_code(category).content_date(curDate).content_time(curTime).content_view(0).content_good(0).build();
		
		contentVO.setBoard_code(category);
		contentVO.setContent_date(curDate);
		contentVO.setContent_time(curTime);
		contentVO.setContent_view(0);
		contentVO.setContent_good(0);
		
		contentService.insert(contentVO);
		return "redirect:/board/{CAT}";
	}
	
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String read(Integer content_num, Model model) {
		ContentVO contentVO = contentService.findByIdContent(content_num);
		model.addAttribute("CONTENT",contentVO);
		model.addAttribute("BODY", "READ");
		return "home";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(Integer content_num, Model model) {
		ContentVO contentVO = contentService.findByIdContent(content_num);
		String bcode = contentVO.getBoard_code().substring(0, 3);
		// board_code 앞 3글자 따오기
		if(bcode.equals("TIP")) {
			model.addAttribute("MENU","TIP");
		} else if(bcode.equals("TAL")) {
			model.addAttribute("MENU","TALK");
		} else if(bcode.equals("REV")) {
			model.addAttribute("MENU","REVIEW");
		}
		model.addAttribute("CONTENT",contentVO);
		model.addAttribute("BODY", "UPDATE");
		return "home";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(ContentVO contentVO, Model model) throws Exception {
		contentService.update(contentVO);
		model.addAttribute("content_num", contentVO.getContent_num());
		return "redirect:/board/read";
	}
	
	@RequestMapping(value="/{MENU}/delete", method=RequestMethod.GET)
	public String delete(Integer content_num, String board_code, Model model) throws Exception {
		contentService.delete(content_num);
		return "redirect:/board/{MENU}";
	}
	
}
