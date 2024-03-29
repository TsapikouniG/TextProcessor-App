package datamodel.buildingblocks;

import java.util.ArrayList;   
import java.util.StringTokenizer;

public class LineBlock
{
	private int paragraph_num;
	private ArrayList<String> line= new ArrayList<String>();// Kathe string exei mia grammi
	private String Style;
	private String Format;
	
	public LineBlock(int counter, ArrayList<String> line)
	{	
		if(counter>=0 && !line.equals(null))
		{
			this.line=line;
			this.paragraph_num=counter;
		}
		else
		{
			
	    	throw new NullPointerException();
		}
		
	}


	public int getPragraph_num()
	{
		return paragraph_num;
	}
	
	
	public ArrayList<String> getLines()
	{
		return line;
	}
	
	public int getNumWords()
	{	
		String tok;
		int sum=0;
		
		
	  for(int i=0; i<line.size(); i++)
	  {
		  
		  StringTokenizer st = new StringTokenizer(line.get(i));
		  tok=st.nextToken();
		  
		  if(tok.equals("_"))
		  {	
			 i++;
		  }
		  else
		  {
			  sum++;
			
		  }
		  
		   sum=sum+st.countTokens();
		   
	  }
	 
		return sum;
	
	}
	
	
	
	public String getStatsAsString() {
	  
		String result="Lines: " + line.size()+"         "+"Words: "+getNumWords();
	    return result;
	}
	
	
	public String setStyle(StyleEnum Style){
		this.Style=Style.toString();
		
		return this.Style;
		
	}
	
	public void setFormat(FormatEnum Format){
		
		this.Format=Format.toString();
		
		return;
		
	
	} 
	
	public String getStyle()
	{	
		return this.Style;
	}
	
	public String getFormat()
	{
		return this.Format;
	}
	
	
}
