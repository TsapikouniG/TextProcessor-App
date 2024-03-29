package datamodel.rules;

import java.util.List; 

import datamodel.buildingblocks.LineBlock;

public class RuleInPosition extends AbstractRule {
	private List<LineBlock> lineblocks;
	private List<Integer> positions;
	
	public RuleInPosition(List<LineBlock> lineblocks, List<Integer> positions) 
	{
		if(lineblocks.equals(null)|| positions.equals(null))
		{
			throw new NullPointerException();
		}
		this.lineblocks=lineblocks;
		this.positions= positions;
	}
	

	public boolean isValid(LineBlock paragraph) 
{	
		for(int i=0; i<positions.size(); i++)
		{	
			if(positions.get(i)==paragraph.getPragraph_num())
			{
				return true;
			}
			
			
		}return false;
}
	
	
	
	public String toString() {
		return positions+"";
	}

}
