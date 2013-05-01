import play.db.jpa.GenericModel;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstrap extends Job

{
  @Override
  public void doJob() {
   
//      Fixtures.loadModels("data.yml");
      Fixtures.loadModels("/data/data1.yml");
//      Fixtures.loadModels("/data/data2.yml");
//      Fixtures.loadModels("/data/data3.yml");
//      Fixtures.loadModels("/data/data4.yml");
//      Fixtures.loadModels("/data/data5.yml");
//      Fixtures.loadModels("/data/data6.yml");
//      Fixtures.loadModels("/data/data7.yml");
    
  }
}