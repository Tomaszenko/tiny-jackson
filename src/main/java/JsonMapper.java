import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import annotations.Property;

/**
 * Created by tomek on 19.07.17.
 */
public class JsonMapper {

    public JsonMapper() {

    }

    public String toJson(Object object) throws IllegalAccessException {

        Class<?> clazz = object.getClass();

        List<Field> fieldsToSerialize = Arrays.stream(clazz.getDeclaredFields()).
                filter((field)->field.getDeclaredAnnotation(Property.class)!=null).
                collect(Collectors.toList());

        List<Field> fields = Arrays.asList(object.getClass().getDeclaredFields());

        StringBuilder jsonRepresentationBuilder = new StringBuilder();

        jsonRepresentationBuilder.append("{\n");

        for(int i=0; i!=fieldsToSerialize.size(); ++i) {
            Field currentField = fieldsToSerialize.get(i);

            Class<?> fieldType = currentField.getType();

            jsonRepresentationBuilder.append("\t");
            jsonRepresentationBuilder.append(currentField.getName());
            jsonRepresentationBuilder.append(":");

            if(Collection.class.isAssignableFrom(fieldType)) {
                ParameterizedType listType = (ParameterizedType) currentField.getGenericType();
                Class<?> collectionElementClass = (Class<?>) listType.getActualTypeArguments()[0];
                System.out.println(collectionElementClass);
            }

            if(fieldType.isArray()) {
                Class<?> elementClazz = currentField.getType().getComponentType();
                System.out.println(elementClazz);
            }

            currentField.setAccessible(true);

            if(currentField.getType()==String.class) {
                jsonRepresentationBuilder.append("\"");
                jsonRepresentationBuilder.append(currentField.get(object));
                jsonRepresentationBuilder.append("\"");
            } else {
                jsonRepresentationBuilder.append(currentField.get(object));
            }

            if(i!=fields.size()-1)
                jsonRepresentationBuilder.append(",");
            jsonRepresentationBuilder.append("\n");

            currentField.setAccessible(false);
        }
//        for(Field field: fields) {
//            jsonRepresentationBuilder.append(field.getName() + ":" + field.get(object));
//        }

        jsonRepresentationBuilder.append("}");

        return jsonRepresentationBuilder.toString();
    }
}
