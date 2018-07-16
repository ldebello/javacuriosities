package ar.com.javacuriosities.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {

    public static void main(String[] args) {
        try {
            // Obtengo la Class por medio del paquete + nombre de la clase
            Class clazzPerson = Class.forName("ar.com.javacuriosities.reflection.Person");

            // Uso el método "newInstance" para crear una instancia o sea Java hace el new por mi
            Person personInstance = (Person) clazzPerson.newInstance();

            System.out.println("***** Printing declared methods *****");

            // Pido los métodos de la clase
            Method[] clazzMethods = clazzPerson.getDeclaredMethods();

            for (Method method : clazzMethods) {
                System.out.println("Method name: " + method.getName());
                System.out.println("Method parameter count: " + method.getParameterCount());
                System.out.println("Is method public? " + Modifier.isPublic(method.getModifiers()));
                System.out.println("Is method private? " + Modifier.isPrivate(method.getModifiers()));
                System.out.println("Return type: " + method.getReturnType());
                System.out.println("--------------------------------");
            }

            System.out.println("***** Printing all methods *****");
            // Pido los métodos de mi clase y los de mi padre
            Method[] allMethods = clazzPerson.getMethods();

            for (Method method : allMethods) {
                System.out.println("Method name: " + method.getName());
                System.out.println("Method parameter count: " + method.getParameterCount());
                System.out.println("Is method public? " + Modifier.isPublic(method.getModifiers()));
                System.out.println("Is method private? " + Modifier.isPrivate(method.getModifiers()));
                System.out.println("Return type: " + method.getReturnType());
                System.out.println("--------------------------------");
            }

            System.out.println("***** Properties *****");
            Field[] fields = clazzPerson.getDeclaredFields();

            for (Field field : fields) {
                System.out.println("Property name: " + field.getName());
                System.out.println("Property type: " + field.getType());
                System.out.println("--------------------------------");
            }

            System.out.println("***** Getter/Setter *****");
            PropertyDescriptor descriptorNombre = new PropertyDescriptor("name", clazzPerson);

            // Si los métodos no existen arroja Exception
            Method readMethod = descriptorNombre.getReadMethod();
            Method writeMethod = descriptorNombre.getWriteMethod();
            System.out.println("Getter name: " + readMethod.getName());
            System.out.println("Setter name: " + writeMethod.getName());

            System.out.println("***** Invoke method over instance *****");
            // Pido los métodos con sus parámetros si no existe arroja Exception
            Method personGetter = clazzPerson.getMethod(readMethod.getName());
            Method personSetter = clazzPerson.getMethod(writeMethod.getName(), String.class);

            // Invoco el método sobre un objeto
            String nameValue = (String) personGetter.invoke(personInstance);

            System.out.println("Person name is: " + nameValue);

            personSetter.invoke(personInstance, "Cosme Fulanito");

            nameValue = (String) personGetter.invoke(personInstance);

            System.out.println("New person name is: " + nameValue);

            System.out.println("***** Getting value from private field *****");
            Field isYoungProperty = clazzPerson.getDeclaredField("isYoung");

            // Marcamos la propiedad como accesible
            isYoungProperty.setAccessible(true);

            System.out.println("Property isYoung is: " + isYoungProperty.get(personInstance));

            // Usamos Integer.TYPE porque el parámetro es un tipo primitivo
            Method restMethod = clazzPerson.getDeclaredMethod("rest", Integer.TYPE);
            restMethod.invoke(personInstance, Integer.valueOf(10));

            isYoungProperty.set(personInstance, Boolean.FALSE);

            restMethod.invoke(personInstance, Integer.valueOf(10));
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}
