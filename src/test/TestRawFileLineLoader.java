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


public class TestRawFileLineLoader {

	
	private RawFileLineLoader rawfileload;
	private List<LineBlock> line= new ArrayList<LineBlock>();
	private static IPlainTextDocumentEngine engine;
    private static String filepath;
	
	@Before
	public void setUp() throws Exception 
	{   filepath = "Resources/SampleDocs/hippocratesOath.txt";
		engine= new Engine(filepath, "RAW", "happyhippo");
	}
	
	
	@Test(expected = NullPointerException.class)
	public final void testoParametersLoader()
	{
		
		rawfileload=new RawFileLineLoader();
		rawfileload.load(null,this.line);
		assertNull("test if load prevents creation of null array",line);
	}
	
	@Test
	public final void loader()
	{
		rawfileload=new RawFileLineLoader();
		rawfileload.load(filepath,this.line);
		assertEquals("number of paragraphs is 17",17,line.size());
	}
	
	
	
}
