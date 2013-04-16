import org.junit.*;

import java.util.*;

import play.test.*;
import models.*;

public class UserTest extends UnitTest 
{
  private User u1,u2,u3;
  @Before
  public void setup()
  {
    u1 = new User("john");
    u2 = new User("ian");
    u3 = new User("dennis");
    u1.save();
    u2.save();
    u3.save();
  }
  
  @After
  public void teardown()
  {
    u1.delete();
    u2.delete();
    u3.delete();
  }

  @Test
  public void testCreate()
  {
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
  public void testNotThere()
  {
    User a = User.findByName("userNotThere");
    assertNull(a);
  }
}