import org.junit.*;

import controllers.Blogs;

import java.util.*;

import play.test.*;
import models.*;

public class UserTest extends UnitTest
{
  private User u1, u2, u3;
  private Blog b1, b2, b3;

  @Before
  public void setup() {
    b1 = new Blog("blog1");
    b2 = new Blog("blog2");
    b3 = new Blog("blog3");
    u1 = new User("john");
    u2 = new User("ian");
    u3 = new User("dennis");

    // some blogs
    u1.addBlog(b1);
    u2.addBlog(b2);
    u2.addBlog(b3);
    u1.save();
    u2.save();
    u3.save();
  }

  @After
  public void teardown() {
    u1.delete();
    u2.delete();
    u3.delete();
  
  }

  @Test
  public void testCreate() {
    User a = User.findByName("john");
    assertNotNull(a);
    assertEquals("john", a.name);
    User b = User.findByName("ian");
    assertNotNull(b);
    assertEquals("ian", b.name);
    User c = User.findByName("dennis");
    assertNotNull(c);
    assertEquals("dennis", c.name);
  }

  @Test
  public void testNotThere() {
    User a = User.findByName("userNotThere");
    assertNull(a);
  }

  @Test
  public void testBlogs() {
    User john = User.findByName("john");
    assertEquals(1, john.blogs.size());

    Blog blog01 = Blog.findByName("blog1");
    Blog blog02 = Blog.findByName("blog2");
    Blog blog03 = new Blog("blog3");
    
    User userTest = User.findByName("ian");
    assertEquals(2, userTest.blogs.size());
    assertFalse(userTest.blogs.contains(blog01));   
    assertTrue(userTest.blogs.contains(blog02));
    assertTrue(userTest.blogs.contains(blog03));
  }

  @Test
  public void testRemoveBlog() {
    User user1 = User.findByName("ian"); //ian has blog2 and blog3
    assertEquals(2, user1.blogs.size());
    Blog blog1 = Blog.findByName("blog1");
    Blog blog2 = Blog.findByName("blog2");
    Blog blog3 = Blog.findByName("blog3");

    assertTrue(user1.blogs.contains(blog2));
    user1.blogs.remove(blog2);
    user1.save();
    User user = User.findByName("ian");
    assertEquals(1, user.blogs.size());
    assertFalse(user.blogs.contains(blog2));
    assertTrue(user.blogs.contains(blog3));
   user1.blogs.remove(blog3);

    blog2.delete();
    //blog3.delete();
  }
  @Test
  public void testBlogUser()
  {
    Blog blog  = Blog.findByName("blog1");
    assertNotNull (blog.user);
    assertEquals ("john", blog.user.name);
  }

}