package com.company;

import java.lang.reflect.Field;
import org.w3c.dom.*;

/**
 * Created by Tanusha on 11/02/2017.
 */
public class ToXML {
    public static void process(People[] peoples, Document doc) throws NoSuchMethodException, IllegalAccessException {

        Element rootElement = doc.createElement("root");
        doc.appendChild(rootElement);

        for (People people : peoples) {

            Element objectElement = doc.createElement("object");
            rootElement.appendChild(objectElement);
            objectElement.setAttribute("type", people.getClass().getSimpleName());

            Field[] fields = people.getClass().getDeclaredFields();
                for (Field field : fields) {
                    Element el = doc.createElement("field");
                    objectElement.appendChild(el);
                    el.setAttribute("type", field.getType().getSimpleName());
                    el.setAttribute("id", field.getName());
                    field.setAccessible(true);
                    el.setAttribute("value", field.get(people).toString());
            }
        }
    }
}