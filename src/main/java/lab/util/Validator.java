package lab.util;

import lab.util.annotations.NotNull;

import java.lang.reflect.Field;

public class Validator {

    public static boolean validateObject(Object obj) {
        if (obj == null) return false;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);

                if (field.isAnnotationPresent(NotNull.class) && value == null) return false;

            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
