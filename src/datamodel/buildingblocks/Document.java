package datamodel.buildingblocks;

import java.util.ArrayList;   
import java.util.List; 
import java.io.File;

import dataload.RawFileLineLoader;
import engine.Engine;

public class Document {

	public enum DocumentRawType {
		ANNOTATED,RAW

	}
	
	private String filePath;
	private DocumentRawType docType;
	private List<LineBlock> lineblocks=new ArrayList<LineBlock>();
	
	
	public Document(String filePath, DocumentRawType docType)
	{
		if(!filePath.equals(null) && !docType.equals(null))
		{	
			this.filePath=filePath;
			this.docType=docType;
		}
		else if (filePath.equals(null) || docType.equals(null))
		{
			
	    	throw new NullPointerException();
		}
	}
	
	public List<LineBlock>  getLineblocks(){
		
		return lineblocks; 
	}
	
	public DocumentRawType getInputFileType(){
		
		if(docType==DocumentRawType.RAW)
		{
			return DocumentRawType.RAW;
		}
		else 
		{
			return DocumentRawType.ANNOTATED;
		}
		
	}
	
	public String getName() {
		File f = new File(filePath);
	    String name= f.getName();
	    
	    return name;
	}
	
	public String getPath() {
		return filePath;
	}
	
	
}
