package com.honjal.honjal.dao.ext;

import java.util.List;

import com.honjal.honjal.dao.GenericDao;
import com.honjal.honjal.model.ContentDTO;
import com.honjal.honjal.model.ContentListDTO;
import com.honjal.honjal.model.ContentVO;

public interface ContentDao extends GenericDao<ContentVO, Integer>{

	public ContentDTO findByIdContent(String content_num);
	
	public List<ContentListDTO> allContent();
	public List<ContentListDTO> menuContent(String board_code);
	
	public int insert(ContentVO contentVO);
	public int update(ContentVO contentVO);
	public int delete(String content_num);
	
}
