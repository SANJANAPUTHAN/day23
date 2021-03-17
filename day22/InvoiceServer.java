package day22;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class InvoiceServer extends UnicastRemoteObject implements Invoice{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvoiceServer() throws RemoteException {
	
	}

	@Override
	public String initapp(int userchoice) throws Exception
	{
		Object invoiceServer = Proxy.newProxyInstance(InvoiceServer.class.getClassLoader(), new Class[] {CreateInvoice.class,Shipping.class,ItemDescription.class,ExcelConversion.class,PDFconversion.class}, new MyInvocationHandler(new Object[] {new CreationUtil(),  new ShippingUtility(),new ItemEntry(),new ExcelConversionUtility(),new PDFConversionUtility()}));
		String op;
		switch(userchoice)
		{
		case 1:
			CreateInvoice create=(CreateInvoice)invoiceServer;
			op=create.createInvoice();
			break;
		case 2:
			Shipping shipping=(Shipping)invoiceServer;
			op=shipping.getShipmentDetails();
			break;
		case 3:
			
			ExcelConversion excel=(ExcelConversion)invoiceServer;
			excel.convertToExcel();
			op="excel converted";
			break;
		case 4:
			PDFconversion pdf=(PDFconversion)invoiceServer;
			pdf.convertToPDF();
			op="pdf converted";
			break;
		case 5:
			ItemDescription item=(ItemDescription)invoiceServer;
			op=item.insert();
			break;
		default:
			op="Enter proper choice...";
			break;
		}
		return op;
	}
}
class MyInvocationHandler implements InvocationHandler
{
	Object ob[];
	public MyInvocationHandler(Object ob[]) {
		this.ob=ob;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		Object role=null;
		for(Object o:ob)
		{
			Method m[]=o.getClass().getDeclaredMethods();
			for(Method mm:m)
			{
				if(mm.getName().equals(method.getName()))
				{
					mm.setAccessible(true);
					role=mm.invoke(o, args);
				}
			}
		}
		return role;
	}
}

	

	
	