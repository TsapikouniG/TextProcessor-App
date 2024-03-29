package dataload;

import java.io.*;       
import java.util.*;

import datamodel.buildingblocks.LineBlock;


public class RawFileLineLoader {

	private List<LineBlock> lineblocks= new ArrayList<LineBlock>();
	private int paragraph_num=0;

private ArrayList<String> line= new ArrayList<String>();



	public void load(String filePath, List<LineBlock> lineblocks) {
		this.lineblocks=lineblocks;
	    Scanner x;
	    
	    if(filePath.equals(null))
	    {
	    	System.out.println("No filepath.");
	    	throw new NullPointerException();
	    }

	    try{
		      x= new Scanner(new File(filePath));
		       String newString=x.nextLine();
		        while(x.hasNextLine()){


		            if(newString.isEmpty())
		            {
		             
		              foundEmpty(line);
					  newString=x.nextLine();
					  paragraph_num++;
					 
		            }
		            else{

		              line.add(newString+" ");
					  newString=x.nextLine();

		            }


		        }
		        
		        if(!newString.isBlank()) {
		        	line.clear();
		        	line.add(0,newString);
		        	ArrayList<String>newline= new ArrayList<String>(line);
		    	    
					lineblocks.add(new LineBlock(paragraph_num, newline));
			        
			       
			        paragraph_num++;
		        	
		        }
		        
		        
		        
		        x.close();
	    }
	    catch (Exception e){
	      System.out.println(e);
	    }

	   

		return;


	}
	public void foundEmpty(ArrayList<String> line )
	  {
			if(line.isEmpty())
			{	paragraph_num--;
				return;
			}
			
	    ArrayList<String>newline= new ArrayList<String>(line);
	    
			lineblocks.add(new LineBlock(paragraph_num, newline));
			
			line.clear();
	
	  }


	public List<LineBlock> getLineblocks() {
		return lineblocks;
	}



}
