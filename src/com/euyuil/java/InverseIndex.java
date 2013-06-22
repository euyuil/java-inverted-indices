package com.euyuil.java;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class InverseIndex {

	private File root;
	private HashMap<String, HashSet<String>> index;

	public InverseIndex(File root) {

		if (!root.isDirectory())
			throw new RuntimeException("The path " + root.toString()
					+ " is not a directory.");
		this.root = root;
		this.index = new HashMap<String, HashSet<String>>();

		traversel(this.root);
	}

	private void traversel(File root) {
		for (File file : root.listFiles()) {
			if (file.isDirectory()) {
				traversel(file);
			} else if (file.isFile()) {
				extract(file);
			}
		}
	}

	private void extract(File file) {
		try {

			Scanner scanner = new Scanner(new BufferedInputStream(
					new FileInputStream(file)));
			while (scanner.hasNext()) {
				String[] newKeywords = scanner.next().toLowerCase()
						.split("[^a-z0-9]");
				for (String keyword : newKeywords) {
					if (keyword.length() == 0)
						continue;
					insert(keyword, file);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void insert(String key, File file) {
		HashSet<String> s = index.get(key);
		if (s == null) {
			s = new HashSet<String>();
			index.put(key, s);
		}
		s.add(file.toString());
	}

	public List<String> search(String keywords) {

		ArrayList<String> results = new ArrayList<String>();

		final HashMap<String, Integer> raw = new HashMap<String, Integer>();

		String[] newKeywords = keywords.toLowerCase().split("[^a-z0-9]");
		for (String keyword : newKeywords) {
			if (keyword.length() == 0)
				continue;

			HashSet<String> keywordInfo = index.get(keyword);
			if (keywordInfo == null)
				continue;

			for (String path : keywordInfo) {
				Integer i = raw.get(path);
				if (i == null)
					i = 0;
				raw.put(path, i + 1);
			}
		}

		for (Entry<String, Integer> entry : raw.entrySet()) {
			results.add(entry.getKey());
		}

		Collections.sort(results, new Comparator<String>() {
			public int compare(String a, String b) {
				return raw.get(b) - raw.get(a);
			}
		});

		return results;
	}

	public static void main(String[] args) {
		// testing
		File root = new File("C:\\Users\\Fei\\Documents\\trial");
		String keyword = "lorem";

		InverseIndex index = new InverseIndex(root);
		List<String> results = index.search(keyword);

		for (String result : results) {
			System.out.println(result);
		}
	}
}
