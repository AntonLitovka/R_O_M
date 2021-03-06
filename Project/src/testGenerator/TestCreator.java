package testGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;



import static main.Steps.*;
import static common.CommonFields.*;


public class TestCreator 
{	
	
	private LinkedList<String> testSteps;
	
	private TestCreator()
	{
		this.testSteps = new LinkedList<>();
	}
	
	private static class SingletonHolder
	{
		private static final TestCreator HOLDER_INSTANCE = new TestCreator();
	}
	
	public static TestCreator getInstance()
	{
		return SingletonHolder.HOLDER_INSTANCE;
	}
	
	public void addStep(String string)
	{
		this.testSteps.addLast(string);
	}
	
	public String findStep(String test)
	{
		for( String s: this.testSteps ){
			if( s.startsWith( test ) ){
				return s;
			}
		}
		return null;
	}

	public void generateTests() {
		File file = new File( PATH + "step_definitions/" + PROJECT_NAME + "_steps" + ".rb" );
		try 
		{
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter stream = new BufferedWriter(fileWriter);
			stream.write(STUB_REQUIRE);
			stream.write(ASSERT_REQUIRE);
			stream.write("\n");
			for( String s : this.testSteps )
			{	
				stream.write( s + "\n" );
			}
			stream.flush();
			stream.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	

}
