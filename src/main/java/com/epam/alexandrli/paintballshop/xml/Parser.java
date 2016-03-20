package com.epam.alexandrli.paintballshop.xml;

public interface Parser {
    <T> T parse(String filename, Class<T> clazz) throws SAXEntityParserException;
}
