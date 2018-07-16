package ar.com.javacuriosities.annotations.main;

import ar.com.javacuriosities.annotations.FieldMapping;
import ar.com.javacuriosities.annotations.model.User;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Annotations {

    public static void main(String[] args) {
        Map<Field, Class<?>> fields = getFields(User.class);

        System.out.println("***** Annotations *****");

        for (Map.Entry<Field, Class<?>> entry : fields.entrySet()) {
            Field field = entry.getKey();
            FieldMapping annotation = field.getAnnotation(FieldMapping.class);
            if (annotation != null) {
                try {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), User.class);

                    System.out.println("Attribute: " + propertyDescriptor.getName());
                    System.out.println("Column: " + annotation.name());
                    System.out.println("-----------------------------------");
                } catch (Exception e) {
                    // Log and Handle exception
                    e.printStackTrace();
                }
            }
        }
    }

    private static Map<Field, Class<?>> getFields(Class<?> clazz) {
        Map<Field, Class<?>> fields = new HashMap<Field, Class<?>>();
        for (Field field : clazz.getDeclaredFields()) {
            fields.put(field, clazz);
        }
        return fields;
    }
}