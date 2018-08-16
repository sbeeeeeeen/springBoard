package kr.or.ddit.posts.dao;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.model.PostsCommentVo;
import kr.or.ddit.model.PostsVo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("postsDao")
public class PostsDao implements PostsDaoInf{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public PostsVo getPostsInfo(int posts_id) {
		return template.selectOne("posts.getPostsInfo", posts_id);
	}

	@Override
	public List<PostsCommentVo> getPostsCommentInfo(int posts_id) {
		return template.selectList("posts.getPostsCommentInfo", posts_id);
	}

	@Override
	public int insertPostsComment(PostsCommentVo com) {
		return template.insert("posts.insertPostsComment", com);
	}

	@Override
	public int updatePostsComment(int com_id) {
		return template.update("posts.deletePostsComment", com_id);
	}

	@Override
	public int deletePosts(int posts_id) {
		return template.update("posts.deletePosts", posts_id);
	}

	@Override
	public int insertPosts(PostsVo posts) {
		return template.insert("posts.insertPosts", posts);
	}

	@Override
	public int updatePosts(PostsVo posts) {
		return template.update("posts.updatePosts", posts);
	}

	@Override
	public int getPostsNewId() {
		return template.selectOne("posts.getPostsNewId");
	}

}
