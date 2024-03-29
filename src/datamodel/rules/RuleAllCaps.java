package datamodel.rules;

import datamodel.buildingblocks.LineBlock; 

import java.lang.Character;
import java.util.ArrayList;

public class RuleAllCaps extends AbstractRule {
	private ArrayList<String> line= new ArrayList<String>();
	
	

	public boolean isValid(LineBlock paragraph) {
		this.line = paragraph.getLines();
		if(paragraph==null || line.isEmpty()) 
		{
			throw new NullPointerException();
		}
		
		for (int i=0; i<line.size(); i++)
		    {for(int j=0; j<line.get(i).length(); j++){
		        if (Character.isLowerCase(line.get(i).charAt(j)))
		        {
		        	return false; 
		        }
		       }
		    }
		
		return true; 
	}

	
	public String toString() {
		
		return "The rule starts when it finds a paragraph with all capital letters";
	}
	
	
}
