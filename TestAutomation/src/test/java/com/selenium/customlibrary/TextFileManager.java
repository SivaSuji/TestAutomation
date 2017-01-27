package com.selenium.customlibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Formatter;

import com.sun.mail.imap.protocol.MailboxInfo;

import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;

public class TextFileManager {

	private String fileName;

	public TextFileManager(String filePathName) {
		fileName = filePathName;
		
	}

// read
	public String readFile() {
		String finalText = null;
		String line = null;

		// Reading the file
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			StringBuffer sb = new StringBuffer();
			
			// use while loop as we do not know how many lines of text
			while ((line = bufferReader.readLine()) != null) {
				sb.append(line);
			}
			finalText = sb.toString();
			bufferReader.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return finalText;
	}

// write
	public void writeFile(String inputData) {

		try{
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			bufferWriter.write(inputData);
			bufferWriter.close();
			System.out.println("File Created : " + fileName);
		}catch(Exception e){
			e.printStackTrace();
		}
	}		
//test data 	
		
	public static void main(String[] args) {
		//TextFileManager txtmanager = new TextFileManager("target/data/testData1.txt");
		    //create file to test data
		//txtmanager.writeFile("I like programming. It makes me think more!");
		
//Read
		TextFileManager txtmanager = new TextFileManager("target/data/testData1.txt");
		String msg = txtmanager.readFile();
		System.out.println("Message: " + msg);
		
		
		
	}

	
}
