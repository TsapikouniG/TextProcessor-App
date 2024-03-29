package exporters;

import java.util.ArrayList;    
import java.util.List;
import java.io.FileWriter;   
import java.io.IOException;

import datamodel.buildingblocks.Document;
import datamodel.buildingblocks.LineBlock;


public class MarkdownExporter {

	private Document document;
	private String outputFileName;
	private List<LineBlock> lineblocks;
	private ArrayList<String> newline= new ArrayList<String>(); 
	
	
	public MarkdownExporter(Document document, String outputFileName ) {
		if(document==null || outputFileName==null)
		{
			throw new NullPointerException();
		}
		
		
		this.document=document;
		this.outputFileName=outputFileName;
		lineblocks=document.getLineblocks();
	}
	
	
	public int export(){
		
		int omit=0;
		
		String style;
  	  	String format;
		 try {
		      FileWriter myWriter = new FileWriter(outputFileName);
		      for(int i=0; i<lineblocks.size(); i++) {
		    	  
		    	  style=lineblocks.get(i).getStyle();
		    	  format=lineblocks.get(i).getFormat();
		    	  if(style.equals(null) || format.equals(null))
		    	  {
		    		  throw new NullPointerException();
		    	  }
		    	  
		    	  if(style.equals("H1"))
		    	  {
		    	  		newline=H1(format, i);
		    	  		for(int j =0; j<newline.size(); j++)
		    	  		{
		    	  			 myWriter.write(newline.get(j));
		    	  			
		    	  		}
		    	  		 myWriter.write("\n\n");
		    		  
		    	  }
		    	  else if(style.equals("H2"))
		    	  {
		    		  newline=H2(format, i);
		    		 
		    		  for(int j =0; j<newline.size(); j++)
		    	  		{
		    	  			 myWriter.write(newline.get(j));
		    	  			
		    	  		}
		    	  		 myWriter.write("\n\n");
		    		  
		    	  }
		    	  else if(style.equals("OMITTED"))
		    	  { 
		    		  omit++;
		    		  continue;
		    	  }
		    	  else
		    	  {
		    		  newline=NORMAL(format, i);
		    		  for(int j=0; j<newline.size(); j++)
		    	  		{
		    	  			 myWriter.write(newline.get(j));
		    	  			
		    	  		}
		    		  
		    	  		 myWriter.write("\n\n");
		    	  		
		    		  
		    	  }  
		    	  
		      }
		 
		      
		     
		      myWriter.close();
		 
		 	} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		
		
		 
		
		return lineblocks.size()-omit;
	}
	
	
	public ArrayList<String> H1(String format, int i)
	{
		ArrayList<String> paragraph= lineblocks.get(i).getLines();
		String newLine= "#"+paragraph.get(0);
		paragraph.remove(0);
		paragraph.add(0, newLine );
		
		
		return paragraph;
	}
	
	public ArrayList<String> H2(String format, int i)
	{
		ArrayList<String> paragraph= lineblocks.get(i).getLines();
		String newLine="##"+paragraph.get(0);
		paragraph.remove(0);
		paragraph.add(0, newLine );
		
		return paragraph;
	}
	
	public ArrayList<String> NORMAL(String format, int i)
	{
		
		ArrayList<String> paragraph= lineblocks.get(i).getLines();
		
		
		if(format.equals("BOLD"))
		  {
			String newLine= "**"+paragraph.get(0);
			paragraph.remove(0);
			paragraph.add(0, newLine );
			
			
			
			paragraph.add("**");
			
		  }
		  else if(format.equals("ITALICS"))
		  {
			  String newLine="_"+paragraph.get(0);
			  paragraph.remove(0);
				paragraph.add(0, newLine );
				paragraph.add(" _");
				
			
		  }
		 
		
		  return paragraph;
	}
}
