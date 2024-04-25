package src;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            // reflection을 이용한 객체생성
//            Class userClass = Class.forName(src.User.class.getName());
//            Constructor constructor = userClass.getConstructor();
//            src.User user = (src.User) constructor.newInstance();
//            System.out.println(user);

            // reflection을 이용한 메소드 호출
//            Class clazz = Class.forName(src.User.class.getName());
//            Object user = clazz.getDeclaredConstructor().newInstance();
//            Method setUserNameMethod = clazz.getDeclaredMethod("setUserName", String.class);
//            setUserNameMethod.invoke(user, "NHN 아카데미");
//            Method getUserNameMethod = clazz.getDeclaredMethod("getUserName");
//            String userName = (String) getUserNameMethod.invoke(user);
//            Method setUserAgeMethod = clazz.getDeclaredMethod("setUserAge", Integer.TYPE);
//            setUserAgeMethod.invoke(user, 20);
//            Method getUserAgeMethod = clazz.getDeclaredMethod("getUserAge");
//            int userAge = (int) getUserAgeMethod.invoke(user);
//            System.out.println("userName : " + userName);
//            System.out.println("userAge : " + userAge);

            // reflection API를 이용한 필드 접근
            Class clazz = Class.forName(User.class.getName());
            Object user = clazz.getDeclaredConstructor().newInstance();
            Field userNameField = clazz.getDeclaredField("userName");
            userNameField.setAccessible(true);
            userNameField.set(user, "marco");
            String userName = (String) userNameField.get(user);
            Field userAgeField = clazz.getDeclaredField("userAge");
            userAgeField.setAccessible(true);
            userAgeField.set(user, 30);
            int userAge = (int) userAgeField.get(user);
            System.out.println("userName : " + userName);
            System.out.println("userAge : " + userAge);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // method list 출력 예제
        Class c = ArrayList.class;
        Method m[] = c.getDeclaredMethods();
        for (int i = 0; i < m.length; i++) {
            System.out.println(m[i].toString());
        }
    }
}
