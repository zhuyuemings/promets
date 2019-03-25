package ltd.jezhu.promets.common.util;

import java.lang.reflect.Field;

/**
 * 属性工具
 * @author ymzhu
 * @date 2019/3/23 13:28
 */
public class PropertyUtils {

    /**
     * 比较两个对象的属性值，如果新对象与旧对象属性值不同，则将新对象的对应属性值赋给旧对象对应属性
     * 警告：如果新值为空，旧值也会被清空，如果不考虑更新为空值请在sql中判断更新
     * @param oldObjet  旧对象
     * @param newObject 新对象
     * @return T 如果有更新则会返回更新后的旧对象，否则返回null，任何一个参数为null都会返回null
     * @author ymzhu
     * @date 2019/3/23 13:26
     */
    public static <T> T compareProperties(T oldObjet, T newObject) {
        if (null == oldObjet || null == newObject) {
            return null;
        }
        // 标记是否变化
        boolean change = false;
        // 获取对象的class
        Class<?> oldObjetClass = oldObjet.getClass();
        Class<?> newObjectClass = newObject.getClass();
        // 获取对象的属性列表
        Field[] oldFields = oldObjetClass.getDeclaredFields();
        Field[] newFields = newObjectClass.getDeclaredFields();
        // 遍历对象的属性列表比较属性值
        try {
            for (Field oldField : oldFields) {
                for (Field newField : newFields) {
                    if (oldField.getName().equals(newField.getName())) {
                        // 设置可访问
                        oldField.setAccessible(true);
                        newField.setAccessible(true);
                        // 属性名相同则比较属性类型，属性类型不同则跳过不做比较
                        if (!oldField.getGenericType().equals(newField.getGenericType())) {
                            continue;
                        }
                        // 属性类型相同则比较属性值，属性值不同则将新值覆盖旧值
                        if (!oldField.get(oldObjet).equals(newField.get(newObject))) {
                            change = true;
                            // 操作旧的对象将新值覆盖旧值
                            oldField.set(oldObjet, newField.get(newObject));
                        }
                        // 已经找到相同属性并比较完成，则直接结束当前循环
                        break;
                    }
                }
            }
            if (change) {
                return oldObjet;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
