import org.junit.*;

import java.util.*;
import play.test.*;
import models.*;

public class CommentTest extends UnitTest 
{
  private Comment c1,c2,c3;
  @Before
  public void setup()
  {
    c1 = new Comment("hello1");
    c2 = new Comment("hello2");
    c3 = new Comment("hello3");
    
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
    assertEquals("hello1", c1.name);
    assertEquals("hello2", c2.name);
    assertEquals("hello3", c3.name);
    
    
  }
}