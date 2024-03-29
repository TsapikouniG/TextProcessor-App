package test;
import static org.junit.Assert.assertNotNull;  
import org.junit.Test;

import engine.Engine;
import engine.IPlainTextDocumentEngine;

public class TestLoadFile {

	private String filepath;
	private static IPlainTextDocumentEngine engine;
	
	
	
	@Test
	public void LoadPathNotNull()
	{	
		filepath="Resources/SampleDocs/beagle.txt";
		engine=new Engine(filepath, "RAW", "beagle");
		assertNotNull("The engine loaded the file properly", engine);
		
	}
	
	@Test(expected = NullPointerException.class)
	public void LoadPathNull()
	{	
		filepath="Resources/SampleDocs/beagle.txt";
		engine=new Engine(null, null, null);
		assertNotNull("The engine preventned loadig null file", engine);
		
	}
}
