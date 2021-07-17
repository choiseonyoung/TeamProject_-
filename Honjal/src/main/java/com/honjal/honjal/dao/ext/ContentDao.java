package com.honjal.honjal.dao.ext;

import java.util.List;

import org.springframework.ui.Model;

import com.honjal.honjal.dao.GenericDao;
import com.honjal.honjal.model.ContentDTO;
import com.honjal.honjal.model.ContentFilesDTO;
import com.honjal.honjal.model.ContentListDTO;
import com.honjal.honjal.model.ContentVO;

public interface ContentDao extends GenericDao<ContentVO, Integer>{

	public ContentVO findByIdContent(Integer content_num);
	
	public List<ContentListDTO> contentAll();
	
	public List<ContentListDTO> contentMenu(String menu);
	
	public List<ContentListDTO> contentMenuAllPage(String menu, int pageNum, Model model);
	
	public List<ContentListDTO> searchTitleContent(String menu, String search_word);
	
	public void view_count(int content_num) throws Exception;
	
	public void comment_count(int content_num)throws Exception;
	
	public List<ContentFilesDTO> findByIdGalleryFiles(Long g_seq);
	public ContentDTO  findByIdGalleryFilesResultMap(Long g_seq);
	public void insert(ContentDTO contentDTO);
	
}
