package com.example.xmlProcessingExerciseProductsShop.util;

import jakarta.xml.bind.JAXBException;

public interface XmlParser {

    <E> E fromFile(String filePath, Class<E> tClass) throws JAXBException, JAXBException;
}
