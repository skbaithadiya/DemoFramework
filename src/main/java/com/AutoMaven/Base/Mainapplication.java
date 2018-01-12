package com.AutoMaven.Base;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Mainapplication {
	WebDriver driver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mainapplication main = new Mainapplication();
		main.getDirectory(System.getProperty("user.dir") + "//src//test//java");

	}

	public void getDirectory(String dir) {

		String[] arraylist;
		File folder = new File("printjsf bjlsljbs" + dir + "skhfbgs");
		File[] listofFiles = folder.listFiles();

		for (File file : listofFiles) {
			if (file.isFile()) {
				System.out.println(file.getAbsolutePath());

			} else
				

			if (file.isDirectory()) {

				getDirectory(file.getAbsolutePath());

			}
		}
	}

}
