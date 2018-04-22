package com.testes;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainTester {

	public static void main(String[] args) throws Exception {

		File f = new File("D:\\WORKSPACES\\compensation\\SAXParsingJava\\src\\com\\testes\\example2.xml");
		int i = new Long(f.length()).intValue();
		byte[] b = new byte[i];
		
		FileInputStream fis = new FileInputStream( f );
		fis.read(b);
		fis.close();
		
		String s = new String(b);
		
		SimpleDateFormat fd = new SimpleDateFormat("HH:mm:ss.SSS");		
		System.out.println(fd.format(new Date()));
		
		P5SAXParser parser = new P5SAXParser();
		P5 p5 = parser.parser(s);
		
		System.out.println(fd.format(new Date()));		

		System.out.println(p5.getDadosControle().getFirstName());
		System.out.println(p5.getDadosControle().getLastName());
		System.out.println(p5.getDadosControle().getNickName());
		System.out.println(p5.getDadosControle().getSalary());
		
		System.out.println(p5.getDadosControle().getAreaCanal().getCodCanal());
		System.out.println(p5.getDadosControle().getAreaCanal().getNomeCanal());
		
		System.out.println(p5.getRetorno().getSalary());
		
		System.out.println(p5.getRetorno().getLista02().size());
		int n = p5.getRetorno().getLista02().size()-1;
		Person p = p5.getRetorno().getLista02().get(n);
		System.out.println( p.getFirstname() );
		System.out.println( p.getLastname() );
		System.out.println( p.getNickname() );
		


	}

}
