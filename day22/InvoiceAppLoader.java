package day22;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class InvoiceAppLoader 
{
	public static void main(String[] args) throws Exception {
		InvoiceServer server=new InvoiceServer();
		LocateRegistry.createRegistry(1099);
		System.out.println("Server ready");
		Naming.bind("rmi://localhost:1099/invoiceapp", server);
	}
	
}
