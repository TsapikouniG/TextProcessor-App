package test;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import engine.Engine;
import engine.IPlainTextDocumentEngine;

public class TestProcessFile {
	private ArrayList<List<String>> inputSpec;
	private ArrayList<String> omList;
	private ArrayList<String> h2List;
	private ArrayList<String> italicsList;
	private String filepath;
	private static IPlainTextDocumentEngine engine;
	
	@Before
	public void crateRuleSet()
	{
		inputSpec = new ArrayList<List<String>>();
		omList = new ArrayList<String>(); 
		inputSpec.add(omList);
		omList.add("OMIT");
		omList.add("ALL_CAPS");
		h2List = new ArrayList<String>(); 
		inputSpec.add(h2List);
		h2List.add("H2");
		h2List.add("POSITIONS"); 
		h2List.add("1,2,4,5");
		italicsList = new ArrayList<String>(); 
		inputSpec.add(italicsList);
		italicsList.add("<I>");
		italicsList.add("STARTS_WITH");
		italicsList.add("THE VOYAGE");
	}
	
	
	@Before
	public void LoadPath()
	{	
		filepath="Resources/SampleDocs/beagle.txt";
		engine=new Engine(filepath, "RAW", "beagle");
		
		
	}
	
	@Test
	public void TestprocessFileNotNull()
	{
		
		assertNotNull("Register Rules run good.",engine.registerInputRuleSetForPlainFiles(inputSpec));
	}
	
	@Test(expected = NullPointerException.class)
	public void TestPorcessFileNull()
	{
		
		assertNotNull("Register Rules run good.",engine.registerInputRuleSetForPlainFiles(null));
	}
	
}