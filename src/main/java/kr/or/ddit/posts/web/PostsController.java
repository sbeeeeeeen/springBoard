package kr.or.ddit.posts.web;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.web.BoardController;
import kr.or.ddit.file.service.FileServiceInf;
import kr.or.ddit.file.util.FileUtil;
import kr.or.ddit.model.BoardVo;
import kr.or.ddit.model.FileAttachmentVo;
import kr.or.ddit.model.PostsCommentVo;
import kr.or.ddit.model.PostsVo;
import kr.or.ddit.model.StudentVo;
import kr.or.ddit.posts.service.PostsServiceInf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/posts")
@Controller
public class PostsController {
	
	@Resource(name="postsService")
	private PostsServiceInf postsService;
	
	@Resource(name="fileService")
	private FileServiceInf fileService;
	
	@Resource(name="boardController")
	private BoardController boardController;
	
	@RequestMapping("/postsDetail")
	public String postsDetail(@RequestParam int posts_id, BoardVo board, Model model) throws UnsupportedEncodingException{
		board.setBoard_title(new String(board.getBoard_title().getBytes("8859_1"),"UTF-8"));
		model.addAttribute("board", board);//안하면 안넘어가...
		model.addAttribute("posts", postsService.getPostsInfo(posts_id));
		model.addAttribute("fileList", fileService.fileList(posts_id));
		model.addAttribute("postCommentList", postsService.getPostsCommentInfo(posts_id));
		return "postsDetail";
	}
	
	@RequestMapping("/postsWrite")
	public String postsWrite(BoardVo board, Model model) throws UnsupportedEncodingException{
		board.setBoard_title(new String(board.getBoard_title().getBytes("8859_1"),"UTF-8"));
		model.addAttribute("board", board); //왜 해야하는지?
		return "postsWrite";
	}
	
	@RequestMapping("/postsEdit")
	public String postsEdit(@RequestParam int posts_id, BoardVo board, Model model) throws UnsupportedEncodingException{
		board.setBoard_title(new String(board.getBoard_title().getBytes("8859_1"),"UTF-8"));
		model.addAttribute("fileList", fileService.fileList(posts_id));
		model.addAttribute("posts", postsService.getPostsInfo(posts_id));
		model.addAttribute("board", board);
		return "postsWrite";
	}
	
	@RequestMapping("/postsComment")
	public String postsComment(@RequestParam int posts_id,
							   @RequestParam String division,
							   @RequestParam int com_id,
							   BoardVo board, Model model, PostsCommentVo com,
							   HttpSession session) throws UnsupportedEncodingException{
		StudentVo student = (StudentVo)session.getAttribute("student");
		
		if(division.equals("insertcom")){
			com.setStd_id(student.getStd_id());
			com.setCom_content(new String(com.getCom_content().getBytes("8859_1"),"UTF-8"));
			postsService.insertPostsComment(com);
		}else if(division.equals("deletecom")){
			postsService.updatePostsComment(com_id);
		}else if(division.equals("postsDelete")){
			postsService.deletePosts(posts_id);
			return boardController.viewBoard(0, 0, board, model);
		}else if(division.equals("rePostWrite")){
			model.addAttribute("division", "re");
			board.setBoard_title(new String(board.getBoard_title().getBytes("8859_1"),"UTF-8"));
			model.addAttribute("board", board); //해야돼?
			model.addAttribute("posts", postsService.getPostsInfo(posts_id));
			return "postsWrite";
		}
		model.addAttribute("posts", postsService.getPostsInfo(posts_id));
		return postsDetail(posts_id, board, model);
	}
	
	@RequestMapping(value="/postsUpdate", method=RequestMethod.POST)
	public String postsUpdate(@RequestParam("division") String division, 
							  @RequestPart("uploadFile")MultipartFile file,
							  PostsVo posts, BoardVo board, HttpSession session, Model model){
		StudentVo student = (StudentVo) session.getAttribute("student");
		posts.setStd_id(student.getStd_id());
		posts.setBoard_id(board.getBoard_id());
		
		int posts_id = postsService.getPostsNewId();
		if(division.equals("new")){
			posts.setPosts_id(posts_id);
			posts.setPosts_gno(posts_id);
			postsService.insertPosts(posts);
		}else{
			postsService.updatePosts(posts);
			posts_id=posts.getPosts_id();
		}
		
		if(!file.isEmpty()){
			uploadFile(file, posts_id);
		}
		model.addAttribute("posts", posts); //이거왜해줘야돼??
		model.addAttribute("board", board);
		model.addAttribute("fileList", fileService.fileList(posts_id));
		return "postsDetail";
	}
	
	private void uploadFile(MultipartFile file, int posts_id){
		
		String path = FileUtil.fileUploadPath;
		String fileName = UUID.randomUUID().toString();
		
		File uploadFile = new File(path + File.separator + fileName);
		
		FileAttachmentVo fileVo = new FileAttachmentVo();
		fileVo.setPosts_id(posts_id);
		fileVo.setFile_upload_name(file.getOriginalFilename());
		fileVo.setFile_route(path);
		fileVo.setFile_name(fileName);
		fileService.insertFile(fileVo);
		
		try {
			file.transferTo(uploadFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}
}
