package com.honjal.honjal.dao.ext;

import java.util.List;

import org.springframework.ui.Model;

import com.honjal.honjal.dao.GenericDao;
import com.honjal.honjal.model.BestContentVO;
import com.honjal.honjal.model.ContentListDTO;
import com.honjal.honjal.model.ContentVO;

public interface ContentDao extends GenericDao<ContentVO, Integer>{

	public ContentVO findByIdContent(Integer content_num);
	
	public List<ContentListDTO> contentAll();
	
	public List<ContentListDTO> contentMenu(String menu);
	
	public List<ContentListDTO> contentMenuAllPage(String menu, int pageNum, Model model);
	
	public List<BestContentVO> bestContent();
	
	public List<ContentListDTO> searchTitleContent(String menu, String search_word);
	
}
