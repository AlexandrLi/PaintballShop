package com.epam.alexandrli.paintballshop.xml;

import com.epam.alexandrli.paintballshop.entity.Product;

public class Runner {
    public static void main(String[] args) throws SAXEntityParserException {
        SAXEntityParser saxEntityParser = new SAXEntityParser();
        Product parsedProduct = saxEntityParser.parse("products.xml", Product.class);

    }
}
