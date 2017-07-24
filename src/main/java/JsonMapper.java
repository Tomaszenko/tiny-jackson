import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import annotations.Ignore;
import annotations.Property;

/**
 * Created by tomek on 19.07.17.
 */
public class JsonMapper {

    private StringBuilder stringBuilder;

    public JsonMapper() {
        this.stringBuilder = new StringBuilder();
    }

    public String toJson(Object object) throws IllegalAccessException, NoSuchMethodException {

        Class<?> clazz = object.getClass();

        List<Field> fieldsToSerialize = Arrays.stream(clazz.getDeclaredFields()).
                filter((field)->(field.getDeclaredAnnotation(Ignore.class)==null)).
                collect(Collectors.toList());

        StringBuilder jsonRepresentationBuilder = new StringBuilder();

        jsonRepresentationBuilder.append("{\n");

        for(int i=0; i!=fieldsToSerialize.size(); ++i) {
            Field currentField = fieldsToSerialize.get(i);

            currentField.setAccessible(true);

            Class<?> fieldType = currentField.getType();

            jsonRepresentationBuilder.append("\t");
            jsonRepresentationBuilder.append(currentField.getName());
            jsonRepresentationBuilder.append(":");

//            boolean res1 = !currentField.get(object).getClass().isPrimitive();
//            boolean res2 = currentField.get(object).getClass().getDeclaredMethod("toString") != null;
//            if (res1 && res2) {
//                toJson(currentField.get(object));
//            } else {
//                System.out.println(currentField.get(object));
//            }

            if(fieldType.isPrimitive()) {
                jsonRepresentationBuilder.append(currentField.get(object));
            } else {

                if (fieldType == String.class) {
                    jsonRepresentationBuilder.append(currentField.get(object));
                }
                else {

                    if (fieldType == Integer.class) {
                        jsonRepresentationBuilder.append(currentField.get(object));
                    }
                    else {

                        if (fieldType == Double.class) {
                            jsonRepresentationBuilder.append(currentField.get(object));
                        }
                        else {

                            if (fieldType == Float.class) {
                                jsonRepresentationBuilder.append(currentField.get(object));
                            }
                            else {

                                if (fieldType == Boolean.class) {
                                    jsonRepresentationBuilder.append(currentField.get(object));
                                }
                                else {
                                    toJson(currentField.get(object));
                                }
                            }
                        }
                    }
                }

            }
            currentField.setAccessible(false);
        }


//
//            System.out.println("ZAGNIEŻDŻONE");
//            for(Field f: currentField.getType().getDeclaredFields()) {
//                f.setAccessible(true);
//                System.out.println(f.get(currentField.get(object)));
//                f.setAccessible(false);
//            }

//            if(currentField.get(object)!=null)
//                toJson(currentField);

//            else {

//            if(Collection.class.isAssignableFrom(fieldType)) {
//                ParameterizedType listType = (ParameterizedType) currentField.getGenericType();
//                Class<?> collectionElementClass = (Class<?>) listType.getActualTypeArguments()[0];
//                for(collectionElementClass.: currentField.get(object)) {
//
//                }
//            }
//
//            if(fieldType.isArray()) {
//                Class<?> elementClazz = currentField.getType().getComponentType();
//                System.out.println(elementClazz);
//                currentField.get(object);
//                Object[] arr = currentField.get(object);
//                Object[] arr = Array.newInstance(elementClazz, currentField.get(object));
//                for(Object o: collection)
//                    toJson(o);
//                String[] array = Array.newInstance(currentField.get(0).getClass());
//            }

//                if(currentField.getType()==String.class) {
//                    jsonRepresentationBuilder.append("\"");
//                    jsonRepresentationBuilder.append(currentField.get(object));
//                    jsonRepresentationBuilder.append("\"");
//                }
//                else {
//                    jsonRepresentationBuilder.append(currentField.get(object));
//                }
//            }

//            if(i!=fieldsToSerialize.size()-1)
//                jsonRepresentationBuilder.append(",");
//
//            jsonRepresentationBuilder.append("\n");
//
//            currentField.setAccessible(false);

//        for(Field field: fields) {
//            jsonRepresentationBuilder.append(field.getName() + ":" + field.get(object));
//        }

        jsonRepresentationBuilder.append("}");

        return jsonRepresentationBuilder.toString();
    }
}
