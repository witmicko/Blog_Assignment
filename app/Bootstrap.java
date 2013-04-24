import play.db.jpa.GenericModel;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstrap extends Job

{
  @Override
  public void doJob() {
   
      Fixtures.loadModels("data.yml");
    
  }
}