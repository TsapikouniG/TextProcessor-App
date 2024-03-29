package test;
import static org.junit.Assert.*;   


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import org.junit.Test;

import datamodel.buildingblocks.Document;
import datamodel.buildingblocks.Document.DocumentRawType;
import engine.Engine;
import engine.IPlainTextDocumentEngine;
import exporters.PdfExporter;


public class TestPdfExporter {

	private static String filepath;
	private IPlainTextDocumentEngine engine;
	private PdfExporter pdf;
	private Document doc;
	private static Document.DocumentRawType docType = DocumentRawType.RAW;
	private ArrayList<List<String>> inputSpec;
	private ArrayList<String> omList;
	private ArrayList<String> h2List;
	private ArrayList<String> italicsList;
	private String outputFileName;
	
	
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
	public void setUp()
	{
		filepath="Resources/SampleDocs/beagle.txt";
		engine = new Engine(filepath, "RAW", "beagle");
		doc=new Document(filepath, docType);
	}
	
	
	@Test
	public void testConstructor()
	{
		pdf= new PdfExporter(doc, "name");
		assertNotNull("The new object has been created properly.",pdf);
	}
	
	@Test(expected = NullPointerException.class)
	public void testConstructorNull()
	{
		pdf= new PdfExporter(null, null);
		assertNotNull("est if constructor prevents creation of null objects.",pdf);
	}
	
	
	@Test
	public void testPdfExport()
	{	engine.registerInputRuleSetForPlainFiles(inputSpec);
		String outputFileName = "Resources//Outputs//beagle.pdf";
	    engine.loadFileAndCharacterizeBlocks();
		int outputParagraphs = engine.exportPdf(outputFileName);
		assertEquals("The number of paragraphs in the outputfile is 1100", 1100,outputParagraphs );
	}

	
	@Test(expected = NullPointerException.class)
	public void testPdfExportNull()
	{	engine.registerInputRuleSetForPlainFiles(inputSpec);
		engine.loadFileAndCharacterizeBlocks();
		int outputParagraphs = engine.exportPdf(outputFileName);
		assertNull("The export wont create a file with no outputfile", outputParagraphs );
	}
	
}
