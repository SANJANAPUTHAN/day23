package day22;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

interface CreateInvoice
{
	public String createInvoice();
}
public class CreationUtility implements CreateInvoice {
	public String createInvoice() 
	{
            String output="item";
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter invoice number");
		int invno=sc.nextInt();
		System.out.println("Enter invoice date");
		String date=sc.next();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate invdate=LocalDate.parse(date,formatter);
		System.out.println("Enter itemnumber");
		int itemnumber=sc.nextInt();
		System.out.println("Enter itemname");
		String itemname=sc.next();
		System.out.println("Enter itemprice");
		float itemprice=sc.nextFloat();
		System.out.println("Enter itemunit");
		String itemunit=sc.next();
		System.out.println("Enter itemquantity");
		int itemquantity=sc.nextInt();
		System.out.println("Enter customer number");
		int customerno=sc.nextInt();
		System.out.println("Enter customer name");
		String customername=sc.next();
		System.out.println("Enter customer address");
		String customeraddress=sc.next();
		System.out.println("Enter customer email");
		String customeremail=sc.next();
		System.out.println("Enter customer phone");
		String customerphone=sc.next();
		
		InvoiceMasterImpl invoiceobj=new InvoiceMasterImpl();
		InvoiceMasterDTO invoice=new InvoiceMasterDTO();
		invoice.setInvno(invno);
		invoice.setInvdate(invdate);
		invoice.setCustomerno(customerno);
		invoiceobj.insertInvoice(invoice);
		
		BillMasterDTO bill=new BillMasterDTO();
		BillMasterImpl billobj=new BillMasterImpl();
		bill.setInvno(invno);
		bill.setItemno(itemnumber);
		bill.setItemquantity(itemquantity);
		billobj.insertBill(bill);
		output=output+" "+bill.toString();
		
		ItemMasterDTO item=new ItemMasterDTO();
		ItemMasterImpl itemobj=new ItemMasterImpl();
		item.setItemname(itemname);
		item.setItemno(itemnumber);
		item.setItemprice(itemprice);
		item.setItemunit(itemunit);
		itemobj.insertItem(item);
		output=output+" \n\nItem["+item.toString()+"]\n\n";
		
		CustomerMasterDTO customer=new CustomerMasterDTO();
		CustomerMasterImpl customerobj = new CustomerMasterImpl();
		customer.setCustomerAddress(customeraddress);
		customer.setCustomerEmail(customeremail);
		customer.setCustomername(customername);
		customer.setCustomerno(customerno);
		customer.setCustomerphone(customerphone);
		customerobj.insertCustomer(customer);
		output=output+" "+customer.toString();
		
		return output;
		
	}
	

}

class CreationUtil implements Serializable, CreateInvoice
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String createInvoice() {
		 String output="item";
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter invoice number");
			int invno=sc.nextInt();
			System.out.println("Enter invoice date");
			String date=sc.next();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			LocalDate invdate=LocalDate.parse(date,formatter);
			System.out.println("Enter itemnumber");
			int itemnumber=sc.nextInt();
			System.out.println("Enter itemname");
			String itemname=sc.next();
			System.out.println("Enter itemprice");
			float itemprice=sc.nextFloat();
			System.out.println("Enter itemunit");
			String itemunit=sc.next();
			System.out.println("Enter itemquantity");
			int itemquantity=sc.nextInt();
			System.out.println("Enter customer number");
			int customerno=sc.nextInt();
			System.out.println("Enter customer name");
			String customername=sc.next();
			System.out.println("Enter customer address");
			String customeraddress=sc.next();
			System.out.println("Enter customer email");
			String customeremail=sc.next();
			System.out.println("Enter customer phone");
			String customerphone=sc.next();
			
			try
			{
				InvoiceMasterImpl invoiceobj=new InvoiceMasterImpl();
				InvoiceMasterDTO invoice=new InvoiceMasterDTO();
				invoice.setInvno(invno);
				invoice.setInvdate(invdate);
				invoice.setCustomerno(customerno);
				invoiceobj.insertInvoice(invoice);
				String filename="Invoice"+String.valueOf(invoice.getInvno())+".txt";
				ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename));
				oos.writeObject(invoice);
				oos.close();
				
				BillMasterDTO bill=new BillMasterDTO();
				BillMasterImpl billobj=new BillMasterImpl();
				bill.setInvno(invno);
				bill.setItemno(itemnumber);
				bill.setItemquantity(itemquantity);
				billobj.insertBill(bill);
				output=output+" "+bill.toString();
				String filename2="Bill"+String.valueOf(bill.getInvno())+".dat";
				ObjectOutputStream oosbill=new ObjectOutputStream(new FileOutputStream(filename2));
				oosbill.writeObject(bill);
				oosbill.close();
				
				ItemMasterDTO item=new ItemMasterDTO();
				ItemMasterImpl itemobj=new ItemMasterImpl();
				item.setItemname(itemname);
				item.setItemno(itemnumber);
				item.setItemprice(itemprice);
				item.setItemunit(itemunit);
				itemobj.insertItem(item);
				output=output+" \n\nItem["+item.toString()+"]\n\n";
				String filename3="Item"+String.valueOf(item.getItemno())+".dat";
				ObjectOutputStream oositem=new ObjectOutputStream(new FileOutputStream(filename3));
				oositem.writeObject(item);
				oositem.close();
				
				CustomerMasterDTO customer=new CustomerMasterDTO();
				CustomerMasterImpl customerobj = new CustomerMasterImpl();
				customer.setCustomerAddress(customeraddress);
				customer.setCustomerEmail(customeremail);
				customer.setCustomername(customername);
				customer.setCustomerno(customerno);
				customer.setCustomerphone(customerphone);
				customerobj.insertCustomer(customer);
				output=output+" "+customer.toString();
				String filename4="Customer"+String.valueOf(customer.getCustomerno())+".txt";
				ObjectOutputStream ooscustomer=new ObjectOutputStream(new FileOutputStream(filename4));
				ooscustomer.writeObject(customer);
				ooscustomer.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return output;
			
	}
}
