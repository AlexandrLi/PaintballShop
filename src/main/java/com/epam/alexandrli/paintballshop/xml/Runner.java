package com.epam.alexandrli.paintballshop.xml;

import com.epam.alexandrli.paintballshop.entity.UserProfile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        SAXParser saxParser = factory.newSAXParser();
        MyHandler handler = new MyHandler();
        saxParser.parse(Runner.class.getClassLoader().getResourceAsStream("test.xml"), handler);

        UserProfile userFromXML = handler.userFromXML;
        System.out.println(userFromXML);
    }
}
