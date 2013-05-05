import org.junit.*;

import java.util.*;

import javax.crypto.AEADBadTagException;

import play.test.*;
import models.*;

public class CommentTest extends UnitTest 
{
  private User u1;
  private Blog b1;
  private Post p1;
  private Comment c1;
  @Before
  public void setup()
  {
    c1 = new Comment("comment");
  }
  
  @After
  public void teardown()
  {
    c1.delete();
  }

  @Test
  public void testCreate()
  {
    assertEquals("comment", c1.content);
    Post p1 = new Post("title", "content", "user");
    p1.addComment(c1);
    assertEquals(c1.post,p1 );
    assertTrue(p1.comments.contains(c1));
    p1.removeComment(c1);
    assertEquals(0, p1.comments.size());
  }
  
  
  
  
  
}