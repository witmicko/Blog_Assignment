import org.junit.*;

import java.util.*;
import play.test.*;
import models.*;

public class PostTest extends UnitTest 
{
  private Post p1,p2,p3;
  @Before
  public void setup()
  {
    p1 = new Post("test1");
    p2 = new Post("test2");
    p3 = new Post("test3");
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
    assertEquals("test1", p1.name);
    assertEquals("test2", p2.name);
    assertEquals("test3", p3.name);
    
  }
}