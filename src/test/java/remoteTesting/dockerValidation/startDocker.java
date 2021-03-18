package remoteTesting.dockerValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;

public class startDocker {

    public void startFile() throws IOException, InterruptedException {
//        ProcessBuilder p = new ProcessBuilder("open", "./dockerup.command");
//        p.inheritIO();
//        Process pr = p.start();
         boolean flag=false;
          Runtime runtime = Runtime.getRuntime();
          runtime.exec("open ./dockerup.command");

         String file ="output.txt";
         Thread.sleep(5000);

          Calendar cal = Calendar.getInstance();
          cal.add(Calendar.SECOND, 30);
          long stopnow = cal.getTimeInMillis();
          while(System.currentTimeMillis() < stopnow && !flag)
          {
              if(flag) {
                  break;
              }
              BufferedReader reader = new BufferedReader(new FileReader(file));
              String currentLine=reader.readLine();
              while ( currentLine != null )
              {
                  if ( currentLine.contains("registered to the hub and ready to use"))
                  {
                      System.out.println("Found my text");
                      flag = true;
                      break;
                  }
                  currentLine = reader.readLine();

              }
              reader.close();
           }

          Assert.assertTrue(flag);
          runtime.exec("open ./scale.command");
          Thread.sleep(15000);




   }

}
