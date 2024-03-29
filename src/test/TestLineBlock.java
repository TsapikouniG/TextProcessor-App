package test;
import static org.junit.Assert.*;   


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import org.junit.Test;


import dataload.RawFileLineLoader;
import datamodel.buildingblocks.LineBlock;
import engine.Engine;
import engine.IPlainTextDocumentEngine;

public class TestLineBlock {
	
	
	
	private static  ArrayList<String> parag= new ArrayList<String>();
	private List<LineBlock> line= new ArrayList<LineBlock>();
	private static String filepath;
	private static IPlainTextDocumentEngine engine;
	private RawFileLineLoader rawfileload;
	private LineBlock lineblock;
	
	
	@Before
	public void setUp() throws Exception 
	{
		filepath = "Resources/SampleDocs/hippocratesOath.txt";
		engine= new Engine(filepath, "RAW", "happyhippo");
		rawfileload=new RawFileLineLoader();
		rawfileload.load(filepath,this.line);
		parag=line.get(3).getLines();
		lineblock=new LineBlock(3,parag);
		
	}
	
	
	@Test
	public void testConstructor()
	{	
		assertEquals("The number of paragraph is 3",3, lineblock.getPragraph_num());
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullConstructor()
	{
		LineBlock nulllineblock=new LineBlock(-3,null);
		assertNull("test if construct prevents creation of lineblock with negativ paragraph_num or no paragraph",nulllineblock);
	}
	
	@Test
	public void testGetNumWords()
	{
		int numofwords=lineblock.getNumWords();
		assertEquals("The number of words in paragraph 3 is 10", 10, numofwords);
		
		ArrayList<String> zero= new ArrayList<String>();
		LineBlock zeroLine= new LineBlock(0, zero);
		assertEquals("The number of words in an empty parag is 0.",0, zeroLine.getNumWords());
	}

	
}
