package com.epam.alexandrli.paintballshop.xml;

import com.epam.alexandrli.paintballshop.entity.UserProfile;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;

public class MyHandler extends DefaultHandler {

    UserProfile userFromXML;
    boolean id;
    boolean email;
    boolean password;
    boolean firstName;
    boolean lastName;
    boolean phoneNumber;
    String[] varNames = {"id", "email", "password", "firstName", "lastName", "phoneNumber"};

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("UserProfile")) {
            userFromXML = new UserProfile();
        } else if (qName.equalsIgnoreCase("id")) {
            id = true;
        } else if (qName.equalsIgnoreCase("email")) {
            email = true;
        } else if (qName.equalsIgnoreCase("password")) {
            password = true;
        } else if (qName.equalsIgnoreCase("firstName")) {
            firstName = true;
        } else if (qName.equalsIgnoreCase("lastName")) {
            lastName = true;
        } else if (qName.equalsIgnoreCase("phoneNumber")) {
            phoneNumber = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("Element parsed: " + qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        boolean[] variables = {id, email, password, firstName, lastName, phoneNumber};
        String value = new String(ch, start, length);

        for (int i = 0; i < variables.length; i++) {
            if (variables[i]) {
                if (varNames[i].equals("id")) {
                    userFromXML.setId(Integer.parseInt(value));
                    id = false;
                } else {
                    setFieldUsingReflection(value, varNames[i]);
                }

            }
        }
    }

    private void setFieldUsingReflection(String value, String varName) {
        Field[] declaredFields = userFromXML.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.getName().equalsIgnoreCase(varName)) {
                declaredField.setAccessible(true);
                if (declaredField.getType().equals(Integer.class)) {
                    try {
                        declaredField.setInt(userFromXML, Integer.parseInt(value));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        declaredField.set(userFromXML, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
