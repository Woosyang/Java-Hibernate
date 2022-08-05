/* 
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 * 
 */
 
package Chapter11;
import static org.quartz.DateBuilder.*;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.SimpleScheduleBuilder.*;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import java.util.Date;

/**
 * This Example will demonstrate how to start and shutdown the Quartz scheduler and how to schedule a job to run in
 * Quartz.
 * 
 * @author Woo
 */
public class SimpleExampleRevise {

  public void run() throws Exception {
    // First we must get a reference to a scheduler
	// create a factory
    SchedulerFactory sf = new StdSchedulerFactory();
    // get the scheduler from the factory
    Scheduler sched = sf.getScheduler();

    // computer a time that is on the next round minute
    // Date runTime = evenMinuteDate(new Date());
    // next second
    Date runTime = evenSecondDateAfterNow();
    
    // define the job and tie it to our HelloJob class
    JobDetail job = newJob(HelloJobSim.class).withIdentity("job1", "group1").build();

    // Trigger the job to run on the next round minute
    // execute only one time
    // Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
    // execute multiple times
    Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime)
            .withSchedule(simpleSchedule().withIntervalInSeconds(5).withRepeatCount(10)).build();

    // Tell quartz to schedule the job using our trigger
    sched.scheduleJob(job, trigger);
  
    // Start up the scheduler (nothing can actually run until the
    // scheduler has been started)
    sched.start();

    // wait long enough so that the scheduler as an opportunity to
    // run the job!
 
    try {
      // wait 5 seconds to show job
      Thread.sleep(100L * 1000L);
      // executing...
    } catch (Exception e) {}
    // shut down the scheduler
    sched.shutdown(true);
  }

  public static void main(String[] args) throws Exception {
    SimpleExampleRevise example = new SimpleExampleRevise();
    example.run();
  }
}
