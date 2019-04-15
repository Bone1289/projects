package com.head.first.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputTest {
	private static final Logger LOGGER = LogManager.getLogger(InputTest.class.getName());

	public static void main(String[] args) {
		int c;
		StringBuilder textBuilder = new StringBuilder();
		try (InputStream in = new LowerCaseInputStream(
				new BufferedInputStream(new FileInputStream("resource/text.txt")))) {
			while ((c = in.read()) > 0) {
				textBuilder.append((char) c);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		textBuilder.append(String.format("%n"));
		try (InputStream in = new UpperCaseInputStream(
				new BufferedInputStream(new FileInputStream("resource/text.txt")))) {
			while ((c = in.read()) > 0) {
				textBuilder.append((char) c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOGGER.info(textBuilder.toString());
	}
}
