package model;

import java.util.Stack;

import customEvents.*;

public class CalculatorModel {
  private String result;
  private String expression;
 
  public void setExpression(String expression)
  {
	  this.expression = expression;
  }
  
//  public void getExpresssion() {
//	   
//	   while(true) {
//
//	  if(v1.isExpressionReady)
//	  {				 
//		  m.setExpression(v1.expression);  //Change model
//		  m.setResult(solve(v1.expression));
//		  System.out.println("getting processed");
//		  v1.isExpressionReady=false;
//		  this.updateView();
//		  
//	  }
//	  if(v2.isExpressionReady)
//	  {
//		  m.setExpression(v2.expression);//Change model
//		  m.setResult(solve(v2.expression));
//		  System.out.println("getting processed");
//		  v2.isExpressionReady=false;
//		  this.updateView();
//		  
//	  }
//	 /* if(v3.isExpressionReady)
//	  {
//		  m.setExpression(v3.expression);
//		  m.setResult(solve(v3.expression));
//		  this.updateView();
//		  v3.isExpressionReady=false;
//	  }*/
//	   }
//	 
//  }
//  private void updateView() {
//	   v1.setResult(m.getResult());
//	   v2.setResult(m.getResult());
//	   //v3.setResult(m.getResult());
//	   v1.getUserInput();
//	   v2.getUserInput();
//	   
//  }
//  
  public void evaluate()
  {  
	  this.result = solve(this.expression);
  }
  
  public String solve(String input){
	  
	  if(input.length() == 0)
		  return "";
		  char[] tokens = input.toCharArray();

	      // Stack for numbers

	         Stack<Double> values = new Stack<Double>();



	         // Stack for Operators

	         Stack<Character> ops = new Stack<Character>();



	         for (int i = 0; i < tokens.length; i++)

	         {
	        	 if (tokens[i] == ' ')
	               continue;
	             // Current token is a number, push it to stack for numbers

	             if (tokens[i] >= '0' && tokens[i] <= '9')

	             {

	                 StringBuffer sbuf = new StringBuffer();

	                 // There may be more than one digits in number

	                 while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')

	                     sbuf.append(tokens[i++]);

	                 values.push(Double.parseDouble(sbuf.toString()));

	             }



	             

	             else if (tokens[i] == '(')

	                 ops.push(tokens[i]);



	             

	             else if (tokens[i] == ')')

	             {

	                 while (ops.peek() != '(')

	                   values.push(applyOp(ops.pop(), values.pop(), values.pop()));

	                 ops.pop();

	             }



	            

	             else if (tokens[i] == '+' || tokens[i] == '-' ||

	                      tokens[i] == '*' || tokens[i] == '/')

	             {

	                 // While top of 'ops' has same or greater precedence to current

	                 // token, which is an operator. Apply operator on top of 'ops'

	                 // to top two elements in values stack

	                 while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))

	                   values.push(applyOp(ops.pop(), values.pop(), values.pop()));



	                 

	                 ops.push(tokens[i]);

	             }

	         }



	         // Entire expression has been parsed at this point, apply remaining ops to remaining values

	         while (!ops.empty())

	             values.push(applyOp(ops.pop(), values.pop(), values.pop()));



	         // Top of 'values' contains result, return it

	         return new Double(values.pop()).toString();

	 

	  }

	  public static boolean hasPrecedence(char op1, char op2)

	  {

	      if (op2 == '(' || op2 == ')')

	          return false;
	      if((op1=='^') && (op2=='*'|| op2=='/' || op2=='+' || op2=='-'))
	    	  return false;

	      if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))

	          return false;

	      else

	          return true;

	  }

	  public static Double applyOp(char op,  Double b, Double a)

	   {

		 
	      switch (op)
	      {
	      case '+':
	          return a+b; 
	      case '-':
	          return a-b;
	      case '*':
	          return a*b;
	      case '/':
	          if (b == 0)
	              throw new UnsupportedOperationException("Cannot divide by zero");
	          return a/b;
	      case '^':
	    	  return Math.pow(a, b);
	      }
	      return 0.00;

	  }

	public String getResult() {
		// TODO Auto-generated method stub
		return result;
	}
  
}
