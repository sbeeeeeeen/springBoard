package kr.or.ddit.posts.service;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.model.PostsCommentVo;
import kr.or.ddit.model.PostsVo;
import kr.or.ddit.posts.dao.PostsDaoInf;

import org.springframework.stereotype.Service;

@Service("postsService")
public class PostsService implements PostsServiceInf {
	@Resource(name="postsDao")
	private PostsDaoInf pDao;

	@Override
	public PostsVo getPostsInfo(int posts_id) {
		return pDao.getPostsInfo(posts_id);
	}

	@Override
	public List<PostsCommentVo> getPostsCommentInfo(int posts_id) {
		return pDao.getPostsCommentInfo(posts_id);
	}

	@Override
	public int insertPostsComment(PostsCommentVo com) {
		return pDao.insertPostsComment(com);
	}

	@Override
	public int updatePostsComment(int com_id) {
		return pDao.updatePostsComment(com_id);
	}

	@Override
	public int deletePosts(int posts_id) {
		return pDao.deletePosts(posts_id);
	}

	@Override
	public int insertPosts(PostsVo posts) {
		return pDao.insertPosts(posts);
	}

	@Override
	public int updatePosts(PostsVo posts) {
		return pDao.updatePosts(posts);
	}

	@Override
	public int getPostsNewId() {
		return pDao.getPostsNewId();
	}


}
