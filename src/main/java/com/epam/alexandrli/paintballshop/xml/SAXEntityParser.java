package com.epam.alexandrli.paintballshop.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXEntityParser implements Parser {
    private SAXParser saxParser;

    public SAXEntityParser() throws SAXEntityParserException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            throw new SAXEntityParserException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T parse(String filename, Class<T> clazz) throws SAXEntityParserException {
        EntityHandler handler = new EntityHandler(clazz);
        try {
            saxParser.parse(filename, handler);
        } catch (SAXException | IOException e) {
            throw new SAXEntityParserException(e);
        }
        return (T) handler.getClass();
    }
}