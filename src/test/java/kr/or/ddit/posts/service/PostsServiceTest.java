package kr.or.ddit.posts.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.common.DataInit;
import kr.or.ddit.model.PostsCommentVo;
import kr.or.ddit.model.PostsVo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
		 "classpath:kr/or/ddit/config/spring/datasource_dev.xml",
		 "classpath:kr/or/ddit/config/spring/transaction.xml"})
public class PostsServiceTest {
	
	@Resource(name="postsService")
	private PostsServiceInf postsService;
	
	@Before
	public void setup(){
		new DataInit().setup();
	}

	@Test
	public void postsServiceBeanTest() {
		assertNotNull(postsService);
	}
	
	@Test
	public void getPostsInfoTest(){
		PostsVo posts = postsService.getPostsInfo(1);
		assertNotNull(posts);
	}
	
	@Test
	public void getPostsCommentInfoTest(){
		List<PostsCommentVo> comList = postsService.getPostsCommentInfo(1);
		assertEquals(2, comList.size());
	}
	
	@Test
	public void insertPostsCommentTest(){
		PostsCommentVo com = new PostsCommentVo();
		com.setPosts_id(1);
		com.setStd_id("20");
		com.setCom_content("test");
		int cnt = postsService.insertPostsComment(com);
		assertEquals(1, cnt);
	}
	
	@Test
	public void updatePostsCommentTest(){
		int cnt = postsService.updatePostsComment(2);
		assertEquals(1, cnt);
	}
	
	@Test
	public void deletePostsTest(){
		int cnt = postsService.deletePosts(2);
		PostsVo posts = postsService.getPostsInfo(2);
		assertEquals(cnt, 1);
		assertEquals(posts.getPosts_status(), "N");
	}
	
	@Test
	public void insertPostsTest(){
		PostsVo posts = new PostsVo();
		posts.setBoard_id(1);
		posts.setPosts_content("test");
		int id=postsService.getPostsNewId();
		posts.setPosts_id(id);
		posts.setPosts_gno(id);
		posts.setPosts_title("test");
		posts.setStd_id("20");
		int cnt = postsService.insertPosts(posts);
		assertEquals(cnt, 1);
	}
	
	@Test
	public void updatePostsTest(){
		PostsVo posts = new PostsVo();
		posts.setPosts_title("updateTest");
		posts.setPosts_content("updateTest");
		posts.setPosts_id(1);
		int cnt = postsService.updatePosts(posts);
		assertEquals(1, cnt);
	}

}
