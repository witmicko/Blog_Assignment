import org.junit.*;


import java.util.*;

import play.test.*;
import models.*;

public class CommentTest extends UnitTest 
{
  private User u1;
  private Comment c1,c2,c3;
  private Post p1;
  @Before
  public void setup()
  {
    u1= new User("bob", "lastname", 20, "bob@junit.com", "password");
    c1 = new Comment("content1");
   // c2 = new Comment("content2");
    c3 = new Comment("content3");
    p1 = new Post("title", "content", u1.firstName);
  //  c2.save();
    p1.addComment(c1);
    p1.save();
    c1.save();
    u1.addComment(c3);
    c3.save();
    u1.save();    
  }
  
  @After
  public void teardown()
  {
 //   c2.delete();
    p1.comments.clear();
    c1.delete();
    p1.delete();
    u1.comments.clear();
    c3.delete();
    u1.delete();
  }

  @Test
  public void testCreate()
  {
    assertEquals("content1", c1.content);
    assertEquals("content1", c1.content);
    assertEquals(p1, c2.post);
    assertEquals(u1, c3.author);   
  }
  @Test
  public void testPost(){
    assertEquals(p1, c1.post);
  }
}