import models.User;
import play.db.jpa.GenericModel;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstrap extends Job

{
//  @Override
//  public void doJob() {
//   if(User.count()==0){
//      Fixtures.loadModels("/data/users.yml");
//      Fixtures.loadModels("/data/blogs.yml");
//      Fixtures.loadModels("/data/posts.yml");
//      Fixtures.loadModels("/data/comments.yml");
// 
//   }
//  }
}