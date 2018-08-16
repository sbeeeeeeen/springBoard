package kr.or.ddit.file.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.file.util.FileUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/file")
@Controller
public class FileController {
	
	@RequestMapping("/fileDownload")
	public void fileDownload(@RequestParam String name, @RequestParam String upload_name, 
							 HttpServletResponse response) throws IOException{
		response.setHeader("Content-Disposition", "attachment; filename=\""+upload_name+"\"");
		response.setContentType("application/octet-stream");
		
		String file = FileUtil.fileUploadPath + File.separator + name;
		
		ServletOutputStream sos = response.getOutputStream();
		File f = new File(file);
		FileInputStream fis = new FileInputStream(f);
		byte[] b = new byte[512];
		int len = 0;
		while((len=fis.read(b))!=-1){
			sos.write(b, 0, len);
		}
		
		sos.close();
		fis.close();
		
		
	}
}
