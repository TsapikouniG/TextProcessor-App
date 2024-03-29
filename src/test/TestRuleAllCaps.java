package test;
import static org.junit.Assert.*; 


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import org.junit.Test;

import dataload.RawFileLineLoader;
import datamodel.buildingblocks.LineBlock;
import datamodel.rules.RuleAllCaps;



import engine.Engine;
import engine.IPlainTextDocumentEngine;



public class TestRuleAllCaps {

	private static String filepath;
	private IPlainTextDocumentEngine engine;
	private List<LineBlock> line= new ArrayList<LineBlock>();
	private RawFileLineLoader rawfileload;
	private static  ArrayList<String> parag= new ArrayList<String>();
	private LineBlock lineblock;
	private RuleAllCaps caps;
	
	
	@Before
	public void setUp()
	{
		filepath="Resources/SampleDocs/hippocratesOath.txt";
		engine = new Engine(filepath, "RAW", "happyhippo");
		rawfileload=new RawFileLineLoader();
		rawfileload.load(filepath,this.line);
		parag=line.get(1).getLines();
		caps= new RuleAllCaps();
	}
	
	
	@Test
	public void TestRuleAllCapTrue()
	{	parag=line.get(1).getLines();
		lineblock=new LineBlock(1, parag);
		boolean result =caps.isValid(lineblock);
		assertEquals("The second paragraph of this file is in all capital", true, result);
		
	}
	
	@Test
	public void TestRuleAllCapsFalse()
	{	parag=line.get(0).getLines();
		lineblock=new LineBlock(0, parag);
		boolean result =caps.isValid(lineblock);
		assertEquals("The second paragraph of this file is not in all capital", false, result);
	
	}

	
	@Test(expected = NullPointerException.class)
	public void TestRuleAllCapsEmpty()
	{	parag.clear();
		lineblock=new LineBlock(0, parag);
		boolean result =caps.isValid(lineblock);
		assertNull("Test if isValid will not search inside an empty paragraph", result);
	}
	
	@Test(expected = NullPointerException.class)
	public void TestRullAllCapsNull()
	{
		boolean result=caps.isValid(null);
		assertNull("Test if isValid will not search inside a null Lineblock", result);
	}
}
