package com.belhard.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.tomcat.util.http.fileupload.IOUtils;

public class DocReader {

	public static ArrayList<String> readDocxFile(String fileName, HashMap<String, String> map) {

		ArrayList<String> lines = null;
		try {
			lines = (ArrayList<String>) Files.readAllLines(new File(fileName).toPath(), Charset.forName("UTF-8"));

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

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}

	public static void putFullDocxToStream(ArrayList<String> lines, ZipOutputStream os, String servletContext) {
		try {
			FileInputStream fis = new FileInputStream(servletContext + "recommendation/lettertemplate.zip");
			ZipInputStream zipFile = new ZipInputStream(fis);
			ZipEntry entry;

			while ((entry = zipFile.getNextEntry()) != null) {
				os.putNextEntry(new ZipEntry(entry.getName()));
				byte[] b = new byte[512];
				int len = 0;
				while ((len = zipFile.read(b)) != -1) {
					os.write(b, 0, len);
				}
				os.closeEntry();
			}

			entry = new ZipEntry("word/document.xml");
			os.putNextEntry(entry);
			for (int i = 0; i < lines.size(); i++) {
				os.write(lines.get(i).getBytes());
			}
			os.closeEntry();

			os.close();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	public static void SendFileToStream(ArrayList<String> lines, ServletOutputStream os, String servletContext,
			String arhiveType) {
		ZipOutputStream out;
		try {
			if (arhiveType != null && (arhiveType.equals("zip") || arhiveType.equals("jar"))) {
				int randomNum = (int) (Math.random() * 1000);
				out = new ZipOutputStream(
						new FileOutputStream(servletContext + "recommendation/recommendation" + randomNum + ".zip"));
				putFullDocxToStream(lines, out, servletContext);
				out.close();
				Path path = Paths.get(servletContext + "recommendation/recommendation" + randomNum + ".zip");
				byte[] data = Files.readAllBytes(path);
				if (arhiveType.equals("zip")) {
					out = new ZipOutputStream(os);
					ZipEntry entry = new ZipEntry("letter.docx");
					out.putNextEntry(entry);
					out.write(data);
					out.closeEntry();
					out.close();
				} else 
					if (arhiveType.equals("jar")) {
					out = new JarOutputStream(os);
					JarEntry entry = new JarEntry("letter.docx");
					out.putNextEntry(entry);
					out.write(data);
					out.closeEntry();
					out.close();
				}

			} else {
				out = new ZipOutputStream(os);
				putFullDocxToStream(lines, out, servletContext);
			}
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}
}
