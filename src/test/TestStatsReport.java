package test;
import static org.junit.Assert.*; 

import java.util.List;

import org.junit.Test;

import engine.Engine; 



public class TestStatsReport {
		
	
	
	
	
	
	@Test
	public void testStatsnotNull ()
	{
		String filepath="Resources/SampleDocs/beagle.txt";
		Engine engine =new Engine(filepath, "RAW", "beagle");
		engine.loadFileAndCharacterizeBlocks();
		List<String> stats=engine.reportWithStats();
		assertEquals("Total number of paragraphs: 1147",stats.get(0).strip());
		assertEquals("Total number of words: 207475", stats.get(1).strip());
	}
	
	
	@Test(expected = NullPointerException.class)
	public void testStatsNull ()
	{
		String filepath="";
		Engine engine =new Engine(filepath, null, null);
		engine.loadFileAndCharacterizeBlocks();
		assertNull("Total number of paragraphs: 1147",engine.reportWithStats());
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
