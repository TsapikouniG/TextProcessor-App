package datamodel.rules;

import datamodel.buildingblocks.LineBlock;

public class RuleUndefined extends AbstractRule {

	
	public boolean isValid(LineBlock paragraph) {
		return false;
	}

	
	public String toString() {
		return "Undefined.";
	}

}
