import org.junit.*;

import com.mchange.v2.c3p0.impl.NewPooledConnection;

import java.util.*;

import play.test.*;
import models.*;

public class CommentTest extends UnitTest 
{
  private Comment c1,c2,c3;
  private Post p1;
  @Before
  public void setup()
  {
    c1 = new Comment("author1", "content1");
    c2 = new Comment("author2", "content2");
    c3 = new Comment("author3", "content3");
    p1 = new Post("title", "content", null);
    p1.addComment(c1);
    p1.save();
    c1.save();
    c2.save();
    c3.save();
  }
  
  @After
  public void teardown()
  {
    c1.delete();
    c2.delete();
    c3.delete();
  }

  @Test
  public void testCreate()
  {
    assertEquals("author1", c1.author);
    assertEquals("content1", c1.content);
    assertEquals("author2", c2.author);
    assertEquals("author3", c3.author);   
  }
  @Test
  public void testPost(){
    //assertEquals(p1, c1.post);
  }
}