package test;
import static org.junit.Assert.*;  


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import org.junit.Test;

import dataload.RawFileLineLoader;
import datamodel.buildingblocks.LineBlock;
import datamodel.rules.RuleStartWith;



import engine.Engine;
import engine.IPlainTextDocumentEngine;


public class TestRuleStartWith {
	
	private static String filepath;
	private IPlainTextDocumentEngine engine;
	private List<LineBlock> line= new ArrayList<LineBlock>();
	private RawFileLineLoader rawfileload;
	private RuleStartWith startwith;
	
	
	
	@Before
	public void setUp()
	{
		filepath="Resources/SampleDocs/hippocratesOath.txt";
		engine = new Engine(filepath, "RAW", "happyhippo");
		rawfileload=new RawFileLineLoader();
		rawfileload.load(filepath,this.line);
		
		
	}
	
	
	@Test
	public void TestConstructor()
	{
		startwith=new RuleStartWith("OATH");
		assertNotNull("The object is created as it should.",startwith);
	}
	
	
	@Test(expected = NullPointerException.class)
	public void TestConstructorNull()
	{
		startwith=new RuleStartWith(null);
		assertNotNull("The object is created as it should.",startwith);
	}
	
	
	@Test
	public void TestisValidfalse()
	{
		startwith=new RuleStartWith("OATH");
		boolean result =startwith.isValid(line.get(0));
		assertEquals("IsValid works as it should.",result, false);
	}

	
	
	@Test
	public void TestisValidtrue()
	{
		startwith=new RuleStartWith("OATH");
		boolean result =startwith.isValid(line.get(1));
		assertEquals("IsValid works as it should.",result, true);
	}
	
	@Test(expected = NullPointerException.class)
	public void TestisValidNull()
	{
		startwith=new RuleStartWith("OATH");
		boolean result =startwith.isValid(null);
		assertNull("IsValid works as it should.",result);
	}
}
