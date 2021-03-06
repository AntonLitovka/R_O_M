package codeCreator;

import java.util.LinkedList;

import exceptions.SameParameterExeption;



public class Method{
	private String methodName;
	private LinkedList<String> methodParameters; 
	private String methodLogic;
	

	public Method(String methodName) 
	{
		this.methodName = methodName;
		this.methodParameters = new LinkedList<>();
		this.methodLogic = "";
	}
	
	
	public void addLogic( String logic )
	{
		this.methodLogic = logic ;
	}
	
	public String generateCode() 
	{
		String returnValue = "\tdef "+this.methodName;
		
		
		if(!methodParameters.isEmpty()){
			boolean firstParameter = true;
			returnValue+="(";
			for(String parameter : methodParameters){
				
				if( !firstParameter ){
					returnValue += ", ";		
				}else{
					firstParameter = !firstParameter;
				}
				returnValue += parameter;
			}
			returnValue+=")\n";
		}else{
			returnValue+="\n";
		}
		returnValue+="\t\t"+this.methodLogic+"\n";
		returnValue+="\tend\n";
		return returnValue; 
	}
	
	@Override
	public String toString(){
		return generateCode();
	}


	public void addParameter(String parameter) throws SameParameterExeption {
		if(this.methodParameters.contains(parameter))
			throw new SameParameterExeption(parameter);
		this.methodParameters.add(parameter.trim());
	}
	
	public void removeParameter(String parameter) {
		 this.methodParameters.remove(parameter.trim()); 
	}
	
	@Override
	public boolean equals(Object other) {
		if( ((Method)other).methodName.equalsIgnoreCase(this.methodName) 
				&& this.methodParameters.size() == ((Method)other).methodParameters.size()){
			return true;
		}
		return false;
	}




	public int getNumberOfParameters() {
		return this.methodParameters.size();
	}
	
	public String getMethodName(){
		return this.methodName;
	}

}
