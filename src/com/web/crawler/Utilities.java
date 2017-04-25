package com.web.crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class Utilities {

	// Write list to file
	public void writeToFile(String File_Name, List<String> RList) 
	{
		try {
			FileWriter fstream = new FileWriter(File_Name);
			BufferedWriter out = new BufferedWriter(fstream);
			ListIterator<String> itr = RList.listIterator();
			while (itr.hasNext()) {
				out.write(itr.next() + "\n");
			}
			out.close();
		} catch (Exception e) {
		}
	}

	// Write set to file
	public void writeToFile(String File_Name, Set<String> RSet) 
	{
		try {
			FileWriter fstream = new FileWriter(File_Name);
			BufferedWriter out = new BufferedWriter(fstream);
			Iterator<String> itr = RSet.iterator();
			while (itr.hasNext()) {
				out.write(itr.next() + "\n");
			}
			out.close();
		} catch (Exception e) {
		}
	}

	// Read file into List
	public List<String> readFromFile_list(String File_Name) 
	{
		List<String> ReadList = new LinkedList<String>();

		try {
			File f = new File(File_Name);
			FileReader r = new FileReader(f);
			BufferedReader reader = new BufferedReader(r);

			String line = null;

			while ((line = reader.readLine()) != null) {
				ReadList.add(line);
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ReadList;
	}

	// Read file into Set
	public Set<String> readFromFile_set(String File_Name) 
	{
		Set<String> ReadList = new HashSet<String>();

		try {
			File f = new File(File_Name);
			FileReader r = new FileReader(f);
			BufferedReader reader = new BufferedReader(r);

			String line = null;

			while ((line = reader.readLine()) != null) {
				ReadList.add(line);
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ReadList;
	}

	// Delete file
	public void deleteFiles(String File_Name) throws IOException 
	{
		File file = new File(File_Name);
		if (file.exists()) {
			String path = file.getCanonicalPath();
			File filePath = new File(path);
			filePath.delete();
		}
	}
}
