package datamodel.rules;

import datamodel.buildingblocks.LineBlock; 



public class RuleStartWith extends AbstractRule{

	private String prefix;
	
	private boolean return_isValid;
	
	
	public  RuleStartWith(String prefix)
	{	
		if(prefix.equals(null))
		{
			throw new NullPointerException();
		}
		this.prefix=prefix;
	}
	
	
	public boolean isValid(LineBlock paragraph) {
		
		if(paragraph.equals(null))
		{
			throw new NullPointerException();
		}
			this.return_isValid=paragraph.getLines().get(0).matches("(?i)"+prefix+(".*"));
			if(return_isValid==true) {
				return true;
			}
		return false;
	}
			
	

	public String toString() {
		
		return prefix ;
	}


	
}
