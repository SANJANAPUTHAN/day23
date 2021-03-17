package day22;

import java.util.Scanner;

interface ItemDescription
{
	public String insert();
	
}
public class ItemEntry implements ItemDescription{

	@Override
	public String insert() {
		Scanner sc=new Scanner(System.in);
		String output="";
		System.out.println("Enter itemnumber");
		int itemnumber=sc.nextInt();
		System.out.println("Enter itemname");
		String itemname=sc.next();
		System.out.println("Enter itemprice");
		float itemprice=sc.nextFloat();
		System.out.println("Enter itemunit");
		String itemunit=sc.next();
		ItemMasterDTO item=new ItemMasterDTO();
		ItemMasterImpl itemobj=new ItemMasterImpl();
		item.setItemname(itemname);
		item.setItemno(itemnumber);
		item.setItemprice(itemprice);
		item.setItemunit(itemunit);
		itemobj.insertItem(item);
		output=output+" Item["+item.toString()+"]\n\n";
		return output;
		
	}

	
}
