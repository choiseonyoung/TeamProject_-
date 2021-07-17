package com.honjal.honjal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.honjal.honjal.dao.ext.ContentDao;
import com.honjal.honjal.model.ContentDTO;
import com.honjal.honjal.model.ContentFilesDTO;
import com.honjal.honjal.model.ContentListDTO;
import com.honjal.honjal.model.ContentVO;
import com.honjal.honjal.model.PageDTO;
import com.honjal.honjal.service.ContentService;
import com.honjal.honjal.service.PageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("contentServiceV1")
public class ContentServiceImplV1 implements ContentService {

	protected final ContentDao contentDao;
	protected final SqlSession sqlSession;
	protected final PageService pageService;
	
	@Override
	public ContentVO findByIdContent(Integer content_num) {
		ContentVO contentVO = contentDao.findByIdContent(content_num);
		return contentVO;
	}
	
	@Override
	public void insert(ContentVO contentVO) throws Exception {
		contentDao.insert(contentVO);
		return;
	}

	@Override
	public void update(ContentVO contentVO) throws Exception {
		contentDao.update(contentVO);
		return;
	}

	@Override
	public void delete(Integer content_num) throws Exception {
		contentDao.delete(content_num);
		return;
	}
	
	@Override
	public List<ContentListDTO> contentAll() {
		List<ContentListDTO> list = contentDao.contentAll();
		return list;
	}

	public List<ContentListDTO> contentMenu(String board_code) {
		List<ContentListDTO> contentList = contentDao.contentMenu(board_code);
		return contentList;
	}
	
	@Override
	public List<ContentListDTO> contentMenuAllPage(String menu, int intPageNum, Model model) {
		
		List<ContentListDTO> contentAll = contentDao.contentMenu(menu);
		int totalContents = contentAll.size();
		
		PageDTO pageDTO = pageService.makePagination(totalContents, intPageNum);
		
		List<ContentListDTO> pageList = new ArrayList<>();
		
		for(int i = pageDTO.getOffset(); i<pageDTO.getLimit(); i++) {
			pageList.add(contentAll.get(i));
		}
		
		model.addAttribute("PAGE_NAV", pageDTO);
		model.addAttribute("CONTENTS", pageList);
		
		return null;
	}

	@Override
	public List<ContentListDTO> searchTitleContent(String menu, String search_word) {
		List<ContentListDTO> list = contentDao.searchTitleContent(menu, search_word);
		return list;
	}

	@Override
	public List<ContentListDTO> searchTextContent(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContentListDTO> searchNameContent(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContentListDTO> MyContent(Integer member_num) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void view_count(int content_num) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("content-mapper.view_count", content_num);
	}
	
	//제목옆댓글수
	@Override
	public void comment_count(int content_view) throws Exception {
		
	}

	@Override
	public List<ContentFilesDTO> findByIdGalleryFiles(Long g_seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContentFilesDTO findByIdGalleryFilesResultMap(Long g_seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void input(ContentDTO ContentDTO, MultipartFile one_file, MultipartHttpServletRequest m_file)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
