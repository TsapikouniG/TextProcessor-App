package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import engine.Engine;


public class Client {
	
	private static String FilePath;
	private static String InputType;
	private static String Alias;
	
	private static List<List<String>>inputSpec;
	private static ArrayList<String> omList;
	private static ArrayList<String> h1List;
	private static ArrayList<String> h2List;
	private static ArrayList<String> italicsList;
	private static ArrayList<String> boldList;
	private static Engine engine;
	
	
	public static void menu()
	{	
		boolean run= true;
		while(run==true) {
			Scanner input= new Scanner(System.in);
			System.out.println("1.Register rules.\n2.Process file.\n3.Export file.\n4.Report With Stats.\n5.Exit.");
			System.out.println("Choose from 1 to 5."); 
			int in=input.nextInt();
			if(in==1) 
			{
				RegisterRules();
			}
			else if(in==2) 
			{
				ProcessFile();
			}
			else if(in==3)
			{
				ExportFile();
			}
			else if(in==4)
			{
				ReportWithStats();
			}
			else if(in==5)
			{
				run=false;
				input.close();
			}
			
			else
			{
				System.out.println("You must choose from 1 to 5.");
				}
			
		}
		
	}
	
	
	
	public static void RegisterRules()
	{
		inputSpec = new ArrayList<List<String>>();
		omList= new ArrayList<String>(); 
		h1List= new ArrayList<String>();
		h2List= new ArrayList<String>();
		italicsList= new ArrayList<String>();
		boldList= new ArrayList<String>();
		
		omList.add("OMIT");
		h1List.add("H1");
		h2List.add("H2");
		boldList.add("<B>");
		italicsList.add("<I>");
		
		String prefix =null;
		int flag=0;
		String answer="NO";
		
		while(answer.equals("NO"))
		{
			System.out.println("Type OMIT or H1 or H2 or <B> or <I> depending on the rule you choose.");
			Scanner input = new Scanner(System.in);
			String rule=input.next();
			
			while(!rule.equals("OMIT") && !rule.equals("H1") && !rule.equals("H2") && !rule.equals("<B>") && !rule.equals("<I>")  )
			{
				System.out.println("You must have typed something wrong. Type again.");
				rule=input.next();

			}
			
			System.out.println("Type STARTS_WITH or POSITIONS or ALL_CAPS.");
			String secondaryRule=input.next();
			while(!secondaryRule.equals("STARTS_WITH") && !secondaryRule.equals("POSITIONS") && !secondaryRule.equals("ALL_CAPS"))
			{
				System.out.println("You must have typed something wrong. Try again.");
				secondaryRule=input.next();
			}
			if(secondaryRule.equals("STARTS_WITH"))
			{	flag=1;
				System.out.println("Give me the prefix");
				prefix=input.next();
				
			}
			else if(secondaryRule.equals("POSITIONS"))
			{	flag=1;
				System.out.println("Give me the positions of paragraphs seperated with a comma(,).");
				prefix=input.next();
				
			}
			
			
			if(rule.equals("OMIT"))
			{
				omList.add(secondaryRule);
				if(flag==1)
				{
					omList.add(prefix);
					
				}
				inputSpec.add(omList);
			}
			else if(rule.equals("H1"))
			{	
				h1List.add(secondaryRule);
				if(flag==1)
				{
					h1List.add(prefix);
					
				}
				inputSpec.add(h1List);
			}
			else if(rule.equals("H2"))
			{
				h2List.add(secondaryRule);
				if(flag==1)
				{
					h2List.add(prefix);
					
				}
				inputSpec.add(h2List);
			}
			else if(rule.equals("<B>"))
			{
				boldList.add(secondaryRule);
				if(flag==1)
				{
					boldList.add(prefix);
					
				}
				inputSpec.add(boldList);
			}
			else if(rule.equals("<I>"))
			{
				italicsList.add(secondaryRule);
				if(flag==1)
				{
					italicsList.add(prefix);
					
				}
				inputSpec.add(italicsList);
			}
			
			System.out.println("If you are done, press YES, else press NO.");
			answer=input.next();
			while(!answer.equals("NO")&&  !answer.equals("YES")) 
			{
				System.out.println("You must have typed something wrong. Try again.");
				answer=input.next();
			}
			
		}
		
	}
	
	
	public static void LoadFile()
	{	
		Scanner input= new Scanner(System.in);
		System.out.println("give me the FilePath. ");
		FilePath=input.next();
		System.out.println("give me the InputType. Type ANNOTATED or RAW accordingly.");
		InputType=input.next();
		while(!InputType.equals("RAW")&& !InputType.equals("ANNOTATED"))
		{
			System.out.println("You typed wrong type. Try again.");
			InputType=input.next();
		}
		System.out.println("give me the Alias.");
		Alias= input.next();
		engine = new Engine(FilePath, InputType, Alias);
		
	}
	
	public static void ProcessFile()
	{
		engine = new Engine(FilePath, InputType, Alias);
		engine.registerInputRuleSetForPlainFiles(inputSpec);
		
	}
	
	public static void ExportFile()
	{	
		Scanner input= new Scanner(System.in);
		System.out.println("Do you want to export into a Markdown or a PDF.\n Press 1 for Markdown or 2 for Pdf");
		int choice=input.nextInt();
		while(choice!=1 && choice!=2)
		{
			System.out.println("You must have typed wrong number. Try again.");
			choice=input.nextInt();
		}
		
		if(choice==1)
		{
			System.out.println("Give the path of the exported file.");
			String outputpath=input.next();
			engine.exportMarkDown(outputpath);
		}
		else if(choice==2)
		{
			System.out.println("Give the path of the exported file.");
			String outputpath=input.next();
			engine.exportPdf(outputpath);
		}
		
		
	}
	
	public static void ReportWithStats()
	{
		List<String> report = engine.reportWithStats();
		System.out.println(report);
		
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println("Let's load the file.");
		LoadFile();
		menu();
		
		
	}
}
