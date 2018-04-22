package com.testes;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXMLFile {
	
	public static void log(Object s){
		
		System.out.println(s);
		
	}

	public static void main(String argv[]) {

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				boolean bfname = false;
				boolean blname = false;
				boolean bnname = false;
				boolean bsalary = false;

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {

					log("Start Element :" + qName);

					if (qName.equalsIgnoreCase("FIRSTNAME")) {
						bfname = true;
					}

					if (qName.equalsIgnoreCase("LASTNAME")) {
						blname = true;
					}

					if (qName.equalsIgnoreCase("NICKNAME")) {
						bnname = true;
					}

					if (qName.equalsIgnoreCase("SALARY")) {
						bsalary = true;
					}

				}

				public void endElement(String uri, String localName, String qName) throws SAXException {

					log("End Element :" + qName);

				}

				public void characters(char ch[], int start, int length) throws SAXException {

					if (bfname) {
						log("First Name : " + new String(ch, start, length));
						bfname = false;
					}

					if (blname) {
						log("Last Name : " + new String(ch, start, length));
						blname = false;
					}

					if (bnname) {
						log("Nick Name : " + new String(ch, start, length));
						bnname = false;
					}

					if (bsalary) {
						log("Salary : " + new String(ch, start, length));
						bsalary = false;
					}

				}

			};

			SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss.SSS");
			
			System.out.println(f.format(new Date()));
			saxParser.parse("D:\\WORKSPACES\\compensation\\SAXParsingJava\\src\\com\\testes\\example2.xml", handler);
			System.out.println(f.format(new Date()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}