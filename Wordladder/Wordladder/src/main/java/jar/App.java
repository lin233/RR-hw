package jar;

import java.io.*;
import java.util.Vector;
//import java.lang.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;	
//import javax.swing.JFrame;

/*
 Word Ladder A word ladder is a connection from one word to another formed 
 by changing one letter at a time with the constraint that at each step the 
 sequence of letters still forms a valid word. For example, here is a word 
 ladder connecting the word "code" to the word "data". Each changed letter 
 is underlined as an illustration:
	code ! cade ! cate ! date ! data
 There are many other word ladders that connect these two words, but this one
 is the shortest. That is, there might be others of the same length, but none 
 with fewer steps than this one.
 In the first part of this assignment,
 write a program that repeatedly prompts the user for two words and finds a
 minimum-length ladder between the words. You may use the stack and queue collections, 
 along with following a particular provided algorithm to find the shortest word ladder. 
*/
/*
Create an empty queue of stacks.
Create/add a stack containing {w1} to the queue.
While the queue is not empty:
Dequeue the partial-ladder stack from the front of the queue.
For each valid English word that is a "neighbor" (differs by 1 letter) of the word on top of the stack:
If that neighbor word has not already been used in a ladder before:
If the neighbor word is w2:
Hooray! we have found a solution(and it is the stack you are working on in the queue).
Else:
Create a copy of the current partial ladder stack.
Put the neighbor word on top of the copy stack.
Add the copy stack to the end of the queue.
*/

/**
 * Hello world!
 *
 */
public class App 
{
	/*find neighbor and return*/
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

	/*check the word1 and word2 */
	
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

	public static void main(String args[])throws IOException,FileNotFoundException{
		
		//拿来更换单词的字母，存在vector里方便调用
		Vector change = new Vector();
		change.add("a");change.add("b");change.add("c");change.add("d");change.add("e");change.add("f");
		change.add("g");change.add("h");change.add("i");change.add("j");change.add("k");change.add("l");
		change.add("m");change.add("n");change.add("o");change.add("p");change.add("q");change.add("r");
		change.add("s");change.add("t");change.add("u");change.add("v");
		change.add("w");change.add("x");change.add("y");change.add("z");
		
		
		/*
		加载dictionary的文件
		*/
		String filename;
		System.out.println("Dictionary file name?");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		filename = br.readLine();
	
		//bigdic存储文件里的所有的word
		Vector bigdic = new Vector();	
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
				br = new BufferedReader(new InputStreamReader(System.in));
				filename = br.readLine();
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
    	
		String word1=new String("");
		String word2=new String("");
		
		while(true){
			
			//处理输入的两个word，直到valid或quit
			do{
				System.out.println("Word #1 (or Enter to quit): ");
				br = new BufferedReader(new InputStreamReader(System.in));

				word1 = br.readLine();
			
				if((word1==null)||(word1.equals("")))
				{
					 System.out.println("Have a nice day.");
					 return;
				}
				
				
				System.out.println("Word #2 (or Enter to quit): ");
				br = new BufferedReader(new InputStreamReader(System.in));
				
				word2 = br.readLine();
				if((word2==null)||(word2.equals("")))
				{
					System.out.println("Have a nice day.");
					 return;
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
							System.out.println( "A ladder from " + word1 + " to " + word2 + ": " );
							output = wordqueue.element();
							output.push(word2);
							break;
						}
						
						else if (!neibor.equals("none_")){
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
					System.out.print(output.pop()+" ");
				}
				System.out.println("");
			}
			
		}
	}
}
