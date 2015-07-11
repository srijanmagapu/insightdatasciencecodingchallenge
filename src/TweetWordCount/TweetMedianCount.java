package TweetWordCount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;


public class TweetMedianCount {
	
	public HashSet<String> uniqueWord = new HashSet<String>();
	public HashMap<Integer,Integer> uniqueWordPerTweet = new HashMap<Integer,Integer>();
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

	public void wordMedian(String inputFilePath,String outputFilePath) {
		BufferedReader inputBufferedReader = null;
		try{
			inputBufferedReader = readFile(inputFilePath);
			int lineNumber = 0;
			String tweet = inputBufferedReader.readLine();
			while(tweet!=null){
				lineNumber++;
				String[] words = tweet.split(" ");
				int num = 0;
				for(String word:words){
					if(!uniqueWord.contains(word)){
						uniqueWord.add(word);
						num++;
					}
				}
				uniqueWordPerTweet.put(lineNumber, num);
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
			ArrayList<Integer> listForCount = new ArrayList<Integer>();
			out = new BufferedWriter(new FileWriter(outputFilePath));
			for(Integer n: uniqueWordPerTweet.values()){
				listForCount.add(n);
				double median = 0;
				Collections.sort(listForCount);
				if (listForCount.size() % 2 == 0)
					median = ((double) listForCount.get(listForCount.size() / 2) + (double) listForCount.get(listForCount.size() / 2 - 1)) / 2;
				else
					median = (double) listForCount.get(listForCount.size() / 2);

				String output = Double.toString(median);
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
		TweetMedianCount tmc = new TweetMedianCount();
		String filePath = new File("").getAbsolutePath();
		tmc.wordMedian(filePath+args[0], filePath+args[1]);
	}
	
}
