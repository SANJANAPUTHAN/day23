package day22;

import java.rmi.Remote;

public interface Invoice extends Remote{
	public String initapp(int userchoice) throws Exception; 
}
