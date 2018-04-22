package com.testes;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class P5SAXParser extends DefaultHandler {
	
	private P5 p5;
	
	public P5 parser(String xml) throws Exception {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		
		InputStream stream = new ByteArrayInputStream(xml.getBytes());
		
		saxParser.parse(stream, this);
		
		return p5;
	}
	
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		try {
		
			aberto = true;
			empilhar(qName);
			
			
		} catch (Exception ex){
			
			throw new SAXException(ex);

		}

	}
	
	private char lastCh[];
	private int lastStart;
	private int lastLength;
	
	private boolean aberto = false;
	
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		
		this.lastCh = ch;
		this.lastStart = start;
		this.lastLength = length;

	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		try {

			desempilhar();
			aberto = false;
			
		} catch (Exception ex){
			throw new SAXException(ex);
		}

	}
	
	private ArrayList<ObjetoEmpilhado> pilha = new ArrayList<ObjetoEmpilhado>();
	
	private void empilhar(String nome) throws Exception {
		
		ObjetoEmpilhado objeto = new ObjetoEmpilhado();
		objeto.setNome( nome );
		encontrarClasse(nome, objeto);
		
		pilha.add(objeto);

	}
	
	private ObjetoEmpilhado desempilhar() throws Exception {
		
		if (isLast()){
			this.p5 = (P5) remove().getObjeto();
			return null;
		}
		
		ObjetoEmpilhado objeto = remove();
		ObjetoEmpilhado anterior = ultimo();
		
		if (aberto){
		
			invocarMetodo(
					encontrarMetodo( objeto.getNomeMetodo(), anterior.getClazz() ),
					anterior,
					valor()
			);
			
		} else {
			
			if (anterior.isList()){
				
				atribuirLista( anterior, objeto );
				
			} else {

				invocarMetodo(
						encontrarMetodo( objeto.getNomeMetodo(), anterior.getClazz() ),
						anterior,
						objeto.getObjeto()
				);
				
			}
			
		}


		return objeto;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void atribuirLista(ObjetoEmpilhado anterior, ObjetoEmpilhado atual){
				
		List l = null;
		if (anterior.getObjeto() == null){
			l = new ArrayList();
			anterior.setObjeto(l);
		} else {
			l = (ArrayList<?>) anterior.getObjeto();
		}
		
		l.add(atual.getObjeto());
		
	}
	
	private Object valor(){
		return new String(this.lastCh, this.lastStart, this.lastLength);
	}
	
	private boolean isLast(){
		return pilha.size() == 1;
	}
	
	private ObjetoEmpilhado ultimo(){
		int index = pilha.size() - 1;
		return pilha.get( index );
	}
	
	private ObjetoEmpilhado remove(){
		int index = pilha.size() - 1;
		ObjetoEmpilhado objeto = pilha.get( index );
		pilha.remove(index);
		return objeto;
	}
	
	private void invocarMetodo(Method method, ObjetoEmpilhado objeto, Object valor) throws Exception {
		
		if (method == null)
			return;
		
		if (objeto.getObjeto() == null)
			objeto.setObjeto( objeto.getClazz().newInstance() );
		
		valor = converterValor(method, valor);
		
		method.invoke(objeto.getObjeto(), new Object[] { valor } );		
		
	}
	
	private Object converterValor(Method method, Object valor) throws Exception {
		
		if (method.getParameterTypes().length < 1)
			return valor;
		
		if (valor == null)
			return null;
		
		Class<?> paramType = method.getParameterTypes()[0];
		
		if (paramType.equals(Integer.class))
			valor = Integer.parseInt( valor.toString() );
		
		return valor;
	}

	private Method encontrarMetodo(String nome, Class<?> clazz) throws Exception {

		if (clazz == null)
			return null;
		
		if (nome == null)
			return null;
		
		nome = "set" + nome.substring(0, 1).toUpperCase() + nome.substring(1);		
		Method[] methods = clazz.getMethods();
		
		for(Method method : methods){

			if (method.getName().equalsIgnoreCase(nome))
				return method;
			
		}
		
		return null;
		
	}
	
	private void encontrarClasse(String nome, ObjetoEmpilhado ultimo, ObjetoEmpilhado empilhado) throws Exception {
		
		if (ultimo == null || ultimo.getClazz() == null)
			return;

		if (ultimo.isList()){
			empilhado.setClazz(ultimo.getClazz());
			return;
		}
		
		Field[] fields = ultimo.getClazz().getDeclaredFields();
		for (Field f : fields){
			
			MyList ml = f.getAnnotation(MyList.class);
			if (ml != null && ml.nome().equalsIgnoreCase(nome)){
				
				if (ml.classRetorno() != null)
					empilhado.setClazz(ml.classRetorno());
				else
					empilhado.setClazz(f.getType());
				
				empilhado.setNomeMetodo(f.getName());
				empilhado.setList(true);
				return;				
				
			}
			
			if (f.getName().equalsIgnoreCase(nome)){
				empilhado.setClazz(f.getType());
				empilhado.setNomeMetodo(f.getName());
				return;
			}
			
			MyAnnotation ma = f.getAnnotation(MyAnnotation.class);
			
			if (ma != null && ma.nome().equalsIgnoreCase(nome)){
				
				if (ma.classRetorno() != null)
					empilhado.setClazz(ma.classRetorno());
				else
					empilhado.setClazz(f.getType());
				
				empilhado.setNomeMetodo(f.getName());
				return;

			}
			
		}
		
	}
	
	private void encontrarClasse(String nome, ObjetoEmpilhado empilhado) throws Exception {
		
		if (nome.equalsIgnoreCase("P5")){
			empilhado.setClazz(P5.class);
		} else
			encontrarClasse( nome, ultimo(), empilhado );

	}

}
