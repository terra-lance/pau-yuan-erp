package com.terrase.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

public class FileUtil {
	static final Logger LOGGER = Logger.getLogger(FileUtil.class);

	public static void moveFile(File file, String targetDirectory) {
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		byte[] buffer = null;

		try {
			File outputFile = new File(targetDirectory.concat("//").concat(file.getName()));

			inStream = new FileInputStream(file);
			outStream = new FileOutputStream(outputFile);

			buffer = new byte[1024];

			int length;
			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}
		} catch (Throwable t) {
			LOGGER.error("Move file error" + "\n" + t.toString());
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
				if (outStream != null) {
					outStream.close();
				}

				buffer = null;
			} catch (Throwable t) {
				LOGGER.error("Close stream error" + "\n" + t.toString());
			}

			try {
				file.delete();
			} catch (Throwable t) {
				LOGGER.error("Delete file error" + "\n" + t.toString());
			}
		}
	}

	public static void copyFile(File file, String targetDirectory, String targetFileName) {
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		byte[] buffer = null;

		try {
			File outputFile = new File(targetDirectory.concat("//").concat(targetFileName));

			inStream = new FileInputStream(file);
			outStream = new FileOutputStream(outputFile);

			buffer = new byte[1024];

			int length;
			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}
		} catch (Throwable t) {
			LOGGER.error("Copy file error" + "\n" + t.toString());
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
				if (outStream != null) {
					outStream.close();
				}

				buffer = null;
			} catch (Throwable t) {
				LOGGER.error("Close stream error" + "\n" + t.toString());
			}
		}
	}

	public static void writeFile(String string, String filePath) throws FileNotFoundException {
		try (PrintWriter out = new PrintWriter(filePath)) {
			out.println(string);
		}
	}
}
