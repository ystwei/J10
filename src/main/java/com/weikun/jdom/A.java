package com.weikun.jdom;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */
public class A {
    public static void main(String[] args) {

    }
    @Test
    public void read(){
        SAXBuilder sax=new SAXBuilder();
        Document doc=null;
        try {
            doc=sax.build(A.class.getClassLoader().getResourceAsStream("yst.xml"));
            Element root=doc.getRootElement();
            System.out.print(root.getName());

            List<Element> list=root.getChildren("student");


            for(Element e :list){

                System.out.println(e.getName());
                System.out.println(e.getAttributeValue("id"));

                Element name=e.getChild("name");
                System.out.println(name.getTextTrim());
                Element pwd=e.getChild("pwd");
                System.out.println(pwd.getTextTrim());
            }


        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void write(){
        Element root=new Element("YST");

        for(int i=0;i<2;i++){
            Element stu=new Element("student");
            stu.setAttribute("id","0"+i);
            Element name=new Element("name");
            name.setText("张三"+i);
            Element pwd=new Element("pwd");
            pwd.setText("999"+i);

            //演示其关系
            stu.addContent(name);
            stu.addContent(pwd);
            root.addContent(stu);
        }

        Document doc=new Document(root);

        XMLOutputter out=new XMLOutputter();
        String path=A.class.getClassLoader().getResource("").getPath();
        File file=new File(path+"yst.xml");
        FileOutputStream fos= null;
        try {
            fos = new FileOutputStream(file);
            //out.output(doc,fos);
            System.out.print(out.outputString(doc));
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }










    }
}
