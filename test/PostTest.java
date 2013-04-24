import org.junit.*;

import java.util.*;
import play.test.*;
import models.*;

public class PostTest extends UnitTest 
{
  private Post p1,p2,p3;
  private Comment c1,c2,c3;
  @Before
  public void setup()
  {
    c1 = new Comment("comment1", null);
    c2 = new Comment("comment2", null);
    c3 = new Comment("comment3", null);
    p1 = new Post("test1", null);
    p2 = new Post("test2", null);
    p3 = new Post("test3", null);
    p2.addComment(c1);
    p2.addComment(c2);
    p1.save();
    p2.save();
    p3.save();
  }
  
  @After
  public void teardown()
  {
    p1.delete();
    p2.delete();
    p3.delete();
  }

  @Test
  public void testCreate()
  {
    assertEquals("test1", p1.title);
    assertEquals("test2", p2.title);
    assertEquals("test3", p3.title);  
  }
  @Test
  public void testComments(){
    
  }
}