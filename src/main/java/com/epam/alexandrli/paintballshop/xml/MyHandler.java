package com.epam.alexandrli.paintballshop.xml;

import com.epam.alexandrli.paintballshop.entity.UserProfile;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyHandler extends DefaultHandler {

    UserProfile userFromXML;
    List<UserProfile> users = new ArrayList<>();
    Map<String, Boolean> varMap = new HashMap<>();


    public MyHandler() {
        varMap.put("email", false);
        varMap.put("password", false);
        varMap.put("firstName", false);
        varMap.put("lastName", false);
        varMap.put("phoneNumber", false);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("UserProfile")) {
            String id = attributes.getValue("id");
            userFromXML = new UserProfile();
            userFromXML.setId(Integer.parseInt(id));
        } else if (varMap.containsKey(qName)) {
            varMap.put(qName, true);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (userFromXML.getClass().getSimpleName().equalsIgnoreCase(qName)) {
            users.add(userFromXML);
        }
        System.out.println("Element parsed: " + qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length);
        for (Map.Entry<String, Boolean> varPair : varMap.entrySet()) {
            if (varPair.getValue()) {
                setFieldUsingReflection(value, varPair.getKey());
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
                varMap.put(varName, false);
            }
        }
    }
}
