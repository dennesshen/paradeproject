package com.parade.paradeproject.util;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 * @author christinehsieh
 *
 */
public class EntityBuilder2{


    public static <T> Builder<T> init(T entity) {
        
        return new Builder<>(entity);
    }

    
    public static class Builder<T>{
        
        private T entity;
        
        private List<Field> entityFields;
        
        public Builder(T entity) {
            this.entity = entity;
            this.entityFields = getAllFields(entity.getClass());
        }
        
        public <K> Builder<T> convertAllDtoToEntity(K dto) {

            Field[] fields =  
               MyReflectionUtil.getAllFields(dto.getClass()).toArray(new Field[0]);

            for(Field field : fields) {
                  String fieldName = field.getName();
                  try {
                        field.setAccessible(true);
                        Object fieldValue = field.get(dto);
                        injectFieldToEntity(fieldName, fieldValue);
                  } catch (Exception e) {
                  }
            }
            return this;
        }
        
        public Builder<T> convertMapToEntity(Map<String, Object> map) {

            for(Entry<String, Object> entry : map.entrySet()) {
                  try {
                        String fieldName = entry.getKey();
                        Object fieldValue = entry.getValue();
                        injectFieldToEntity(fieldName, fieldValue);
                  } catch (Exception e) {
                  }
            }
            return this;
        }   
        
        public Builder<T> injectFieldToEntity(String fieldName, Object fieldValue) {
            if (fieldValue == null) return this;

            this.entityFields
                .stream()
                .filter(f -> f.getName().toLowerCase().equals(fieldName.toLowerCase()))
                .forEach(f -> {
                    
                        try {
                            f.setAccessible(true);
                            f.set(entity, fieldValue);
                        } catch (IllegalArgumentException e) {
                            //e.printStackTrace();
                            
                            if (f.getType().equals(Timestamp.class)) {
                                try {
                                    LocalDateTime value = (LocalDateTime) fieldValue;
                                    f.set(entity, Timestamp.valueOf(value));
                                } catch (Exception e1) {
                                }
                            }
                        } catch (Exception e) {
                        }
                        
                 });
            return this;
                  
        }
        
        public T build() {
            T tempEntity = this.entity;
            this.entity = null;
            return tempEntity;
        }
        
    }
    
    private static List<Field> getAllFields(Class<?> objectClass){
        List<Field> resultList = new ArrayList<>();
        List<Field> declaredFields = Arrays.asList(objectClass.getDeclaredFields());
        resultList.addAll(declaredFields);
        
        if (objectClass.getSuperclass() != Object.class) {
            resultList.addAll(getAllFields(objectClass.getSuperclass()));
        }
        return resultList;
    }
    
}
