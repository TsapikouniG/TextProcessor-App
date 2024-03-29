package test;
import static org.junit.Assert.*; 


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import org.junit.Test;

import dataload.RawFileLineLoader;
import datamodel.buildingblocks.LineBlock;
import datamodel.rules.RuleAllCaps;
import datamodel.rules.RuleInPosition;
import engine.Engine;
import engine.IPlainTextDocumentEngine;


public class TestRuleInPosition {

	private static String filepath;
	private IPlainTextDocumentEngine engine;
	private List<LineBlock> line= new ArrayList<LineBlock>();
	private RawFileLineLoader rawfileload;
	private static List<Integer> positions=new ArrayList<Integer>();
	private RuleInPosition pos;

	
	
	
	@Before
	public void setUp()
	{
		filepath="Resources/SampleDocs/hippocratesOath.txt";
		engine = new Engine(filepath, "RAW", "happyhippo");
		rawfileload=new RawFileLineLoader();
		rawfileload.load(filepath,this.line);
		
	
	}
	
	public void TestConstructorTrue()
	{
		positions.add(1);
		positions.add(4);
		
		pos=new RuleInPosition(line, positions);
		assertNotNull("Test if RuleInPosition will create object correctly",pos);
	}
	
	
	
	@Test(expected = NullPointerException.class)
	public void TestNullConstructor()
	{
		pos=new RuleInPosition(null, null);
		assertNull("Test if RuleInPosition will not create null object.",pos);
	}
	
	

	@Test
	public void TestisValidTrue()
	{	
		positions.clear();
		positions.add(1);
		positions.add(4);
		pos= new RuleInPosition(line , positions);
		boolean result =pos.isValid(line.get(1));
		assertEquals("IsValid must return true.",true,result);
	}
	
	
	@Test
	public void TestisValidFalse()
	{	
		positions.clear();
		positions.add(1);
		positions.add(4);
		pos= new RuleInPosition(line , positions);
		boolean result =pos.isValid(line.get(0));
		assertEquals("IsValid must return false.",false,result);
	}
	
}
