package com.epam.alexandrli.paintballshop.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EntityHandler<T> extends DefaultHandler {


    private Class<T> inputClass;
    private StringBuilder sb = new StringBuilder();
    private T currentObject;

    public EntityHandler(Class<T> clazz) {
        this.inputClass = clazz;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        sb.setLength(0);
        if (qName.equalsIgnoreCase(inputClass.getSimpleName())) {
            try {
                currentObject = inputClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new SAXHandlerException(e);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sb.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

    }

}
