import org.junit.*;

import java.util.*;

import play.test.*;
import models.*;

public class BlogTest extends UnitTest 
{
  private Blog b1,b2,b3;
  private Post p1,p2,p3;
  @Before
  public void setup()
  {
    p1 = new Post("title1", "content1");
    p2 = new Post("title2", "content2");
    p3 = new Post("title3", "content3");
    b1 = new Blog("blog1");
    b2 = new Blog("blog2");
    b3 = new Blog("blog3");
    
    b1.addPost(p1);
    b1.addPost(p2);
    b1.save();
    b2.save();
    b3.save();
  }
  
  @After
  public void teardown()
  {
    b1.delete();
    b2.delete();
    b3.delete();
  }

  @Test
  public void testCreate()
  {
    Blog a = Blog.findByName("blog1");
    assertNotNull(a);
    assertEquals("blog1", a.name);
    Blog b = Blog.findByName("blog2");
    assertNotNull(b);
    assertEquals("blog2", b.name);
    Blog c = Blog.findByName("blog3");
    assertNotNull(c);
    assertEquals("blog3", c.name);
  }
  @Test
  public void testNotThere()
  {
    Blog a = Blog.findByName("blogNotThere");
    assertNull(a);
  }
  
  @Test
  public void testPosts(){
    Blog blog = Blog.findByName("blog1");
    assertEquals(2, blog.posts.size());
    assertTrue(blog.posts.contains(p1));
    assertTrue(blog.posts.contains(p2));
    assertFalse(blog.posts.contains(p3));
  }
  @Test
  public void testRemovePosts(){
    Blog blog = Blog.findByName("blog1");
    assertTrue(blog.posts.contains(p1));
    blog.posts.remove(p1);
    p1.delete();
    blog.save();    
    assertTrue(blog.posts.contains(p2));
    assertFalse(blog.posts.contains(p3));  
  }
}