package com.company;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Main {


    public static void main(String[] args) {

        People peoples[] = new People[3];
        for (int i = 0; i < peoples.length; i++) {
            peoples[i] = new People();
        }


        peoples[0].setName("John");
        peoples[0].setAge(24);
        peoples[0].setSalary(50000);

        peoples[1].setName("Anna");
        peoples[1].setAge(30);
        peoples[1].setSalary(20000);

        peoples[2].setName("Kate");
        peoples[2].setAge(34);
        peoples[2].setSalary(100000);

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            ToXML.process(peoples, doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("sample.xml"));
            transformer.transform(source, result);
            System.out.println("Done");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
