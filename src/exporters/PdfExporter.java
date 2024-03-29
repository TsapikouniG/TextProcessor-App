package exporters;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import datamodel.buildingblocks.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Chunk;
import datamodel.buildingblocks.LineBlock;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;


public class PdfExporter {

	private Document document;
	private String outputFileName;
	private List<LineBlock> lineblocks;
	private ArrayList<String> line= new ArrayList<String>(); 
	private ArrayList<String> newline= new ArrayList<String>(); 


	
	
	public PdfExporter(Document document, String outputFileName ) {
		if(document==null || outputFileName==null)
		{
			throw new NullPointerException();
		}
		this.document=document;
		this.outputFileName=outputFileName;
		lineblocks=document.getLineblocks();
	}
	

	
	
	public int export() {
		 int omit=0;
		 try {
			 	com.itextpdf.text.Document pdfdocument = new com.itextpdf.text.Document () ;
	            PdfWriter.getInstance(pdfdocument, new FileOutputStream(outputFileName));//path h onoma arxeiou
		 		
	            pdfdocument.open();
	          //  addMetaData(pdfdocument);
	          //  addTitlePage(pdfdocument);
	          //  addContent(pdfdocument);
	          
	        
	           Font regular = new Font(FontFamily.COURIER, 10, Font.NORMAL);
	           Font italic = new Font(FontFamily.COURIER, 10, Font.ITALIC);
	           Font bold = new Font(FontFamily.COURIER, 10, Font.BOLD);
	           Font title1 = new Font(FontFamily.COURIER, 20, Font.NORMAL);
	           Font title2 = new Font(FontFamily.COURIER, 15, Font.NORMAL);
	           
	            for(int i=0; i<lineblocks.size(); i++) {
	        
			    	  String style=lineblocks.get(i).getStyle();
			    	  String format=lineblocks.get(i).getFormat();
			    	  
			    	  if(style.equals("H1"))
			    	  {
			    		newline=lineblocks.get(i).getLines(); 
			    		  for(int j =0; j<newline.size(); j++)
			    	  		{
			    			  
			    			  Chunk chunk = new Chunk(newline.get(j), title1);
			    			  pdfdocument.add(chunk);
			    	  			
			    	  		}			    		  
			    	  		 pdfdocument.add(new Paragraph("\n"));
			    	  }
			    	  else if(style.equals("H2"))
			    	  {
			    		  
				    		newline=lineblocks.get(i).getLines(); 
				    		  for(int j =0; j<newline.size(); j++)
				    	  		{
				    			  
				    			  Chunk chunk = new Chunk(newline.get(j), title2);
				    			  pdfdocument.add(chunk); 
				    	  			
				    	  		}
				    	  		 pdfdocument.add(new Paragraph("\n"));
			    		  
			    	  }
			    	  else if(style.equals("OMITTED"))
			    	  {
			    		  omit++;
			    		  continue;
			    	  }
			    	  else if(format.equals("BOLD"))
			    	  {
			    		  
			    		  newline=lineblocks.get(i).getLines(); 
			    		  for(int j =0; j<newline.size(); j++)
			    	  		{
			    			  
			    			  Chunk chunk = new Chunk(newline.get(j), bold);
			    			  pdfdocument.add(chunk); 
			    	  			
			    	  		}
			    	  		 pdfdocument.add(new Paragraph("\n"));
			    		  
			    	  }
			    	  else if(format.equals("ITALICS"))
			    	  {
			    		  newline=lineblocks.get(i).getLines(); 
			    		  for(int j =0; j<newline.size(); j++)
			    	  		{
			    			  
			    			  Chunk chunk = new Chunk(newline.get(j), italic);
			    			  pdfdocument.add(chunk); 
			    	  			
			    	  		}
			    	  		 pdfdocument.add(new Paragraph("\n"));
			    	  }
			    	  else if(format.equals("REGULAR"))
			    	  {
			    		  newline=lineblocks.get(i).getLines(); 
			    		  for(int j =0; j<newline.size(); j++)
			    	  		{
			    			  
			    			  Chunk chunk = new Chunk(newline.get(j), regular);
			    			  pdfdocument.add(chunk); 
			    	  			
			    	  		}
			    	  		 pdfdocument.add(new Paragraph("\n"));
			    	  }
			    	  
			      }
		 
		 
		 
		 
	          pdfdocument.close();
		    
		    } catch(DocumentException e) {
		    	e.printStackTrace();
		    }catch(FileNotFoundException e) {
		    	e.printStackTrace();
		    }catch(IOException e) {
		    	e.printStackTrace();
		    }

		 	int result_num=lineblocks.size() - omit;
		return result_num ;
	}
	
	

	
	
}
