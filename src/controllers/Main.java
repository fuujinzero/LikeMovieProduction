package controllers;


import java.io.File;
import java.io.IOException;
import models.User;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;
import utils.Serializer;
import utils.XMLSerializer;


public class Main implements ShellDependent
{
	
	private static final String ADMIN="admin";
	LikeMovieAPI likeMovies=new LikeMovieAPI();
	private Shell theshell;
	
	public Main()throws Exception
	{
		File data = new File("./lib/data2.0.xml");
		Serializer serializer = new XMLSerializer(data);
		
		likeMovies = new LikeMovieAPI(serializer);
		
		if(data.isFile())
		{
			likeMovies.load();
		}
		
	}
	
		@Command(description="Log in")
		public void login(@Param(name = "User Id")long userId,@Param(name = "password")String password) throws IOException
		{
			if(likeMovies.logIn(userId, password)&& likeMovies.curntUser.isPresent()) {
				User user = likeMovies.curntUser.get();
				System.out.println("You are Logged in as " + user.id+""+user.firstName);
			if(user.role != null && user.role.equals(ADMIN)) {
				Admin adminMenu = new Admin(likeMovies, user.firstName);
				ShellFactory.createSubshell(user.firstName, theshell, "Admin", adminMenu).commandLoop();
			}
			else {
				DefaultMenu defaultMenu = new DefaultMenu(likeMovies, user);
				ShellFactory.createSubshell(user.firstName, theshell, "Default", defaultMenu).commandLoop();
			}
			}else
				System.out.println("Unknown Username/password");
		}
		
		@Override
		public void cliSetShell(Shell shell) {
			this.theshell=shell;
		}	

		
		@Command(description= "Initial CSV Load")
		public void initialLoad() throws IOException 
		{
			likeMovies.initialLoad();
		}
		public static void main(String[] args) throws Exception
		{
			Main main = new Main();
			Shell shell= ShellFactory.createConsoleShell("LikeMovies", "Welcome to LikeMovies Production - ?help  for instructions", main);
			shell.commandLoop();
			main.likeMovies.store();
	}

		
}




