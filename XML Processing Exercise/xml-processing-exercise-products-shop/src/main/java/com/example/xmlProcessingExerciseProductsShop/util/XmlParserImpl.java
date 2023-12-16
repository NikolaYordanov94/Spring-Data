package com.example.xmlProcessingExerciseProductsShop.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;


import java.io.File;

@Component
public class XmlParserImpl implements XmlParser{

    @Override
    @SuppressWarnings("unchecked")
    public <E> E fromFile(String filePath, Class<E> tClass) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (E) unmarshaller.unmarshal(new File(filePath));
    }
}
