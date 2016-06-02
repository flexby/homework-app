package com.belhard.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ContentWritter {

	private String path;

	public ContentWritter(String htmlPath) {
		path = htmlPath;
	}

	public void writeContent(PrintWriter out, HashMap<String, String> map) throws IOException {

		ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(new File(path).toPath(),
				Charset.forName("UTF-8"));
		List<String> keysList = new ArrayList<String>(map.keySet().size());
		for (String keys : map.keySet()) {
			keysList.add(keys);
		}
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < keysList.size(); j++) {
				if (lines.get(i).contains(keysList.get(j))) {
					lines.set(i, lines.get(i).replace(keysList.get(j), map.get(keysList.get(j))));
				}
			}
			out.println(lines.get(i));
		}
	}

	public void writeContent(PrintWriter out, String themeFilePath) throws IOException {

		ArrayList<String> themesFileLines = (ArrayList<String>) Files.readAllLines(new File(themeFilePath).toPath(),
				Charset.forName("UTF-8"));
		ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(new File(path).toPath(),
				Charset.forName("UTF-8"));
		
		for (int i = 0; i < lines.size(); i++) {
			out.println(lines.get(i));
			if (lines.get(i).contains("id-themes"))
				for (int j = 0; j < themesFileLines.size(); j++) {
					String option = "<label for=\"checkboxes-" + j
							+ "\"><input type=\"checkbox\" name=\"checkboxes\" id=\"checkboxes-" + j + "\" value=\""
							+ themesFileLines.get(j) + "\">" + themesFileLines.get(j) + "</label>";
					out.println(option);
				}
		}
	}

	public void writeContent(PrintWriter out) throws IOException {

		ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(new File(path).toPath(),
				Charset.forName("UTF-8"));

		for (int i = 0; i < lines.size(); i++) {
			out.println(lines.get(i));
		}
	}

}
