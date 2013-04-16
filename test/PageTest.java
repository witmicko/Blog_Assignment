import org.junit.*;

import java.util.*;

import play.test.*;
import models.*;

public class PageTest extends UnitTest 
{
  private Page p1,p2,p3;
  @Before
  public void setup()
  {
    p1 = new Page("google");
    p2 = new Page("wordpress");
    p3 = new Page("twitter");
    
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
    Page a = Page.findByName("google");
    assertNotNull(a);
    assertEquals("google", a.name);
    Page b = Page.findByName("wordpress");
    assertNotNull(b);
    assertEquals("wordpress", b.name);
    Page c = Page.findByName("twitter");
    assertNotNull(c);
    assertEquals("twitter", c.name);
  }
  @Test
  public void testNotThere()
  {
    Page a = Page.findByName("pageNotThere");
    assertNull(a);
  }
}