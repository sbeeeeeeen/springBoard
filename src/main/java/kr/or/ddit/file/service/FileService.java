package kr.or.ddit.file.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.file.dao.FileDao;
import kr.or.ddit.file.dao.FileDaoInf;
import kr.or.ddit.model.FileAttachmentVo;

@Service("fileService")
public class FileService implements FileServiceInf{
	
	@Resource(name="fileDao")
	private FileDaoInf fDao;
	
	@Override
	public int insertFile(FileAttachmentVo file) {
		return fDao.insertFile(file);
	}

	@Override
	public List<FileAttachmentVo> fileList(int posts_id) {
		return fDao.fileList(posts_id);
	}

}
