package TweetWordCount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;


public class TweetWordCount {
	
	
	public Map<String,Integer> wordsCount = new HashMap<String, Integer>();
	
	private BufferedReader readFile(String inputFilePath){
		FileReader inputFileReader = null;
		BufferedReader inputBufferedReader =null;
		try{
			inputFileReader = new FileReader(inputFilePath);
			inputBufferedReader = new BufferedReader(inputFileReader);
			
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
		return inputBufferedReader;
	}

	public void wordCount(String inputFilePath,String outputFilePath) {
		BufferedReader inputBufferedReader = null;
		try{
			inputBufferedReader = readFile(inputFilePath);
			String tweet = inputBufferedReader.readLine();
			while(tweet!=null){
				String[] words = tweet.split(" ");
				for(String word:words){
					if(wordsCount.containsKey(word)){
						wordsCount.put(word,wordsCount.get(word)+1);
					}else{
						wordsCount.put(word,1);
					}
				}
				tweet = inputBufferedReader.readLine();
			}
			inputBufferedReader.close();
			writeFileForCount(outputFilePath);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private void writeFileForCount(String outputFilePath) {
		BufferedWriter out = null;
		try {
			java.util.Iterator<Entry<String,Integer>> wordCountIterator = wordsCount.entrySet().iterator() ;
			out = new BufferedWriter(new FileWriter(outputFilePath));
			while (wordCountIterator.hasNext()) {
				Map.Entry<String,Integer> pairs = wordCountIterator.next();
				String output = " "+pairs.getKey()+"         "+pairs.getValue()+" ";
				out.write(output);
				out.newLine();
			}
			out.close();
		} catch (IOException x) {
			System.out.format("createFile error: %s%n", x);
			System.exit(0);
		}
	}
	
	public static void main(String[] args){
		TweetWordCount tmc = new TweetWordCount();
		String filePath = new File("").getAbsolutePath();
		tmc.wordCount(filePath+args[0], filePath+args[1]);
	}
}
