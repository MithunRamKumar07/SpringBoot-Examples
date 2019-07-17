package com.example.demo;

public class StringGenerator {

	public static void main(String[] args) {
		int A = 5, B = 3; 
		System.out.println(generateString(A, B));;
	}

	public static String generateString(int A,int B){
		String outputString = "";
		while(A>0 || B>0){
			if(A>B){
				if(A-->0)
					outputString = outputString+"a";
				if(0<A--)
					outputString = outputString+"a";
				if(0<B--)
					outputString = outputString+"b";
			}else if(B>A){
				if(0<B--)
					outputString = outputString+"b";
				if(0<B--)
					outputString = outputString+"b";
				if(0<A--)	
					outputString = outputString+"a";
			}else {
				if(0<A--)
					outputString = outputString+"a";
				if(0<B--)
					outputString = outputString+"b";
			}
		}
		return outputString;
	}
}
