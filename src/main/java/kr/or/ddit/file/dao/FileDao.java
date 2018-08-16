package kr.or.ddit.file.dao;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.model.FileAttachmentVo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("fileDao")
public class FileDao implements FileDaoInf{
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public int insertFile(FileAttachmentVo file) {
		return template.insert("file.insertFile", file);
	}

	@Override
	public List<FileAttachmentVo> fileList(int posts_id) {
		return template.selectList("file.getFileList", posts_id);
	}

}
