package test;

import static org.junit.Assert.*;  

import java.io.File;
import java.io.IOException;
import java.lang.NullPointerException;

import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import org.junit.BeforeClass;
import org.junit.Test;

import org.apache.commons.io.FileUtils;

import datamodel.buildingblocks.Document;
import datamodel.buildingblocks.Document.DocumentRawType;



public class TestDocument {

	private Document doc;
	private static String path="Resources/SampleDocs/atlantis.txt";
	public static Document.DocumentRawType docType = DocumentRawType.RAW;

	
	@Test
	public final void testConstructor()
	{
		doc= new Document(path,docType);
		assertEquals("Resources/SampleDocs/atlantis.txt", doc.getPath());
		assertEquals("RAW",doc.getInputFileType().toString());
	}
	
	@Test(expected = NullPointerException.class)
	public final void testConstructorNoparameters()
	{
		Document nulldoc= new Document(null,null);
		assertNull("Test if document prevents creation of object with no path or doctype.",nulldoc);
		
	}
	
	
	
}
