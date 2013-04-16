import org.junit.*;

import java.util.*;

import play.test.*;
import models.*;

public class BlogTest extends UnitTest 
{
  private Blog b1,b2,b3;
  @Before
  public void setup()
  {
    b1 = new Blog("blog1");
    b2 = new Blog("blog2");
    b3 = new Blog("blog3");
    
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
}