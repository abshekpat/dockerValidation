package remoteTesting.dockerValidation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;


public class stopDocker {

    public void stopFile() throws IOException, InterruptedException {
//        ProcessBuilder p = new ProcessBuilder("open", "./dockerup.command");
//        p.inheritIO();
//        Process pr = p.start();
         boolean flag=false;
          Runtime runtime = Runtime.getRuntime();
          runtime.exec("open ./dockerdown.command");

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
                  if ( currentLine.contains("selenium-hub exited"))
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
          File f1 = new File("output.txt");
          if(f1.delete())
          {
              System.out.println("File deleted successfully");
          }
          Thread.sleep(3000);




   }

}
