package com.light.springboot.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	public static String neighbor(String word1, String word2, HashSet wordset, Vector change){
		int length1 = word1.length();
		for (int i = 0; i < length1; i++) {
			for (int j = 0; j < 26; j++) {	
			String wordword = new String(word1.substring(0,i)+change.get(j)+word1.substring(i+1));
				if (wordword.equals(word2)){
					return word2;
				}
				if (wordset.contains(wordword) == true) {
					wordset.remove(wordword);
					return wordword;
				}
			}
		}	
		return "none_";		
	}


	public static Boolean valid(String word1, String word2){
		if(word1.length() != word2.length()){
			System.out.println("The two words must be the same length.");
			return false;
		}
		
		if(word1.equals(word2)){
			System.out.println("The two words must be different.");
			return false;
		}
		
		return true;
	};

	
	public static String wordladder(String filename, String word1, String word2) throws IOException,FileNotFoundException {
			//拿来更换单词的字母，存在vector里方便调用
		String result = "";
		Vector change = new Vector();
				change.add("a");change.add("b");change.add("c");change.add("d");change.add("e");change.add("f");
				change.add("g");change.add("h");change.add("i");change.add("j");change.add("k");change.add("l");
				change.add("m");change.add("n");change.add("o");change.add("p");change.add("q");change.add("r");
				change.add("s");change.add("t");change.add("u");change.add("v");
				change.add("w");change.add("x");change.add("y");change.add("z");
				
				
				/*
				加载dictionary的文件
				*/
		//		System.out.println("Dictionary file name?");

			
				//bigdic存储文件里的所有的word
				Vector<String> bigdic = new Vector();	
		        int wordcount = 0;
				
				/*
				尝试打开文件，如果文件不存在触发错误就catch然后再次输入文件名字
				*/
				Boolean fileflag = false;
				do{
					try
					{
						Scanner sc=new Scanner(new File(filename));;
						fileflag = false;
					}catch(FileNotFoundException e){
						System.out.println("Unable to open that file.  Try again.");
							System.out.println("Dictionary file name?");
						fileflag = true;
					}
				}while(fileflag);
				
				
				/*全部加载到bigdic*/
				Scanner sc=new Scanner(new File(filename));
		        while(sc.hasNext()) {
		            bigdic.add(sc.next());
		            wordcount++;
		        }
		        sc.close();
		    	
				
				while(true){
					
					//处理输入的两个word，直到valid或quit
					do{
					//	System.out.println("Word #1 (or Enter to quit): ");
					
						if((word1==null)||(word1.equals("")))
						{
					//		 System.out.println("Have a nice day.");
							 return "Have a nice day.";
						}
						
						
					//	System.out.println("Word #2 (or Enter to quit): ");
		
						if((word2==null)||(word2.equals("")))
						{
					//		System.out.println("Have a nice day.");
							 return "Have a nice day.";
						}
						
					word1 = word1.toLowerCase();
					word2 = word2.toLowerCase();
					}while(!valid(word1,word2));
					
					int wlength = word1.length();
					
					HashSet<String> wordset = new HashSet<String>();
					Iterator<String> it = bigdic.iterator();  
					
					//缩小寻找范围
					while (it.hasNext()) {  
						String wd = (String) it.next();
						if(wd.length()==wlength)wordset.add(wd);  
					}  
					
					if(!(wordset.contains(word1)&wordset.contains(word2))){
						System.out.println("The two words must be found in the dictionary.");
					}
					
					else{
						wordset.remove(word1);
						wordset.remove(word2);
						
						Stack<String> wordstack = new Stack<String>();
						wordstack.push(word1);
						
						Stack<String> output = new Stack<String>();
						output.push("No word ladder found from " + word1 + " back to " + word2 + ".");		
						
						Queue<Stack<String>> wordqueue = new LinkedList<Stack<String>>();
						wordqueue.offer(wordstack);
						
						/*
							广度优先搜索
						*/
						while(!wordqueue.isEmpty()){
							
							wordstack =  wordqueue.element();
							
							String neibor = neighbor(wordstack.peek(),word2,wordset,change);
							
							while(!neibor.equals("none_")){
								
								if(neibor.equals(word2)){
									result += "A ladder from " + word1 + " to " + word2 + ": ";
									output = wordqueue.element();
									output.push(word2);
									break;
								}
								
								else if (!neibor.equals("none_")){
									@SuppressWarnings("unchecked")
									Stack<String>wordstack1 = (Stack<String>)wordstack.clone();
					
									wordstack1.push(neibor);				
									
									wordqueue.offer(wordstack1);
									neibor = neighbor(wordstack.peek(),word2,wordset,change);
									
								}
								
							}
							
							if(neibor.equals(word2)){
								break;
							}
						
							wordqueue.poll();
						}
					
						//打印结果
						while(!output.empty()){
							result += output.pop()+" ";
						}
						return result;
					}
					
				}
			}		
	
	
    @GetMapping("/helloworld")
    public String helloworld(@RequestParam("fn")String filename, @RequestParam("w1") String word1,@RequestParam("w2") String word2) throws FileNotFoundException, IOException {
        return wordladder(filename,word1,word2);
    }
}