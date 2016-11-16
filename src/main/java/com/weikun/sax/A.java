package com.weikun.sax;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/11/16.
 */
public class A extends DefaultHandler {
    @Test
    public void write(){
        SAXTransformerFactory stf=(SAXTransformerFactory)SAXTransformerFactory.newInstance();

        try {
            TransformerHandler th=stf.newTransformerHandler();
            Transformer tf=th.getTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING,"utf-8");
            tf.setOutputProperty(OutputKeys.INDENT,"yes");//缩进

            String path=A.class.getClassLoader().getResource("").getPath();
            File file=new File(path+"db1.xml");
            FileOutputStream fos=new FileOutputStream(file);

            Result result=new StreamResult(fos);
            th.setResult(result);

            th.startDocument();

            th.startElement(null,null,"db",null);

            AttributesImpl ai=new AttributesImpl();
            ai.addAttribute(null,null,"id",null,"01");
            th.startElement(null,null,"mysql",ai);

            th.startElement(null,null,"name",null);
            th.characters("root".toCharArray(),0,"root".length());
            th.endElement(null,null,"name");

            th.startElement(null,null,"pwd",null);
            th.characters("999".toCharArray(),0,"999".length());
            th.endElement(null,null,"pwd");

            th.endElement(null,null,"mysql");
            th.endElement(null,null,"db");
            th.endDocument();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void read(){

        InputStream is=A.class.getClassLoader().getResourceAsStream("db.xml");

        SAXParserFactory spf=SAXParserFactory.newInstance();

        try {
            SAXParser  sp=spf.newSAXParser();

            try {
                sp.parse(is,new A());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void startDocument() throws SAXException {
        System.out.println("文档开始");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("文档结束");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        System.out.println("元素开始"+qName);
        if(qName.equals("mysql")){

            System.out.println(attributes.getValue(0));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("元素结束"+qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        System.out.println(new String(ch,start,length).trim());
    }
}
