package utils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class fileLogger {
	private static fileLogger logger;
	
	private fileLogger()
	{
		
	}
	
	public fileLogger getLogger()
	{
		if(logger==null){
			logger=new fileLogger();
		}
		return logger;
	}
	
	public boolean log(String msg)
	{
		try{
			PrintWriter writer=new PrintWriter(new FileWriter("log.txt",true));
			writer.println(msg);
			writer.close();
		}catch(FileNotFoundException ex){
			return false;
		}catch(IOException iox){
			return false;
		}
		return true;
	}
}
