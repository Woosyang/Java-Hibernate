package Chapter11;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Task: SimpleExampleRevise
 * This is just a simple job that says "Hello" to the world.
 * @author Woo
 */
public class HelloJobSim implements Job {
    public HelloJobSim() {}
    public void execute(JobExecutionContext context)
        throws JobExecutionException {
        // Say Hello to the World and display the date/time
    	System.out.println("------Start------");
    	System.out.println("Hello World! - " + new Date());
    	System.out.println("------End------");
    }
}
