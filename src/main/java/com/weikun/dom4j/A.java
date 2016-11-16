package com.weikun.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */
public class A {
    @Test
    public void read(){
        InputStream is=A.class.getClassLoader().getResourceAsStream("db.xml");

        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        String s="";
        StringBuffer sb=new StringBuffer();
        try {
            while(  (s=br.readLine())!=null){
                sb.append(s);
            }
            Element root=DocumentHelper.parseText(sb.toString()).getRootElement();

            System.out.println(root.getName());
           // Element mysql=root.element("mysql");
            List<Element> list=root.elements();
            for(Element mysql :list){
                System.out.println(mysql.attributeValue("id"));

                Element username=mysql.element("username");

                System.out.println(username.getTextTrim());
                Element pwd=mysql.element("pwd");
                System.out.println(pwd.getTextTrim());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @Test
    public void write(){
        String path=A.class.getClassLoader().getResource("").getPath();
        File file=new File(path+"db.xml");

        OutputFormat of=OutputFormat.createPrettyPrint();
        of.setEncoding("utf-8");
        of.setIndentSize(4);
        try {
            FileOutputStream fos=new FileOutputStream(file);

            Document doc= DocumentHelper.createDocument();
            XMLWriter writer=new XMLWriter(fos,of);

            Element db=doc.addElement("db");

            Element mysql=db.addElement("mysql");
            mysql.addAttribute("id","01");

            Element username=mysql.addElement("username");
            username.setText("root");;

            Element pwd=mysql.addElement("pwd");
            pwd.setText("root");;

            writer.write(doc);
            System.out.print(doc.asXML());
            writer.flush();
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
