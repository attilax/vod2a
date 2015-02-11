package com.attilax.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

    public class JsonUtil4jackjson {  
    	
    	public static void main(String[] args) {
			Map  m=new HashMap();
			m.put("a", "va");
			m.put("b",new HashMap(){{
				this.put("m2k1", "m2v1");
			}});
			String s=  JsonUtil4jackjson.buildNormalBinder().toJson(m);
			System.out.println(s);
		}
      
     //   private static Logger logger = LoggerFactory.getLogger(JsonBinder.class);  
      
        private ObjectMapper mapper;  
      
        public JsonUtil4jackjson(Inclusion inclusion) {  
            mapper = new ObjectMapper();  
            //设置输出包含的属性  
            mapper.getSerializationConfig().setSerializationInclusion(inclusion);  
            //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性  
            mapper.getDeserializationConfig().set(  
                    org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);  
        }  
      
        /** 
         * 创建输出全部属性到Json字符串的Binder. 
         */  
        public static JsonUtil4jackjson buildNormalBinder() {  
            return new JsonUtil4jackjson(Inclusion.ALWAYS);  
        }  
      
        /** 
         * 创建只输出非空属性到Json字符串的Binder. 
         */  
        public static JsonUtil4jackjson buildNonNullBinder() {  
            return new JsonUtil4jackjson(Inclusion.NON_NULL);  
        }  
      
        /** 
         * 创建只输出初始值被改变的属性到Json字符串的Binder. 
         */  
        public static JsonUtil4jackjson buildNonDefaultBinder() {  
            return new JsonUtil4jackjson(Inclusion.NON_DEFAULT);  
        }  
      
        /** 
         * 如果JSON字符串为Null或"null"字符串,返回Null. 
         * 如果JSON字符串为"[]",返回空集合. 
         *  
         * 如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句: 
         * List<MyBean> beanList = binder.getMapper().readValue(listString, new TypeReference<List<MyBean>>() {}); 
         */  
//        public <T> T fromJson(String jsonString, Class<T> clazz) {  
//            if (StringUtils.isEmpty(jsonString)) {  
//                return null;  
//            }  
//      
//            try {  
//                return mapper.readValue(jsonString, clazz);  
//            } catch (IOException e) {  
//                logger.warn("parse json string error:" + jsonString, e);  
//                return null;  
//            }  
//        }  
      
        /** 
         * 如果对象为Null,返回"null". 
         * 如果集合为空集合,返回"[]". 
         */  
        public String toJson(Object object) {  
      
            try {  
            //	SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS
            //	SerializationConfig.Feature.
             	mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
            	//这是辅助设置，控制格式化输出。
            //	mapper.writeValueAsString(value)
            	//format output
            	return	mapper.defaultPrettyPrintingWriter().writeValueAsString(object);
             //   return mapper.writeValueAsString(object);  
            } catch (IOException e) {  
             //   logger.warn("write to json string error:" + object, e);  
            	e.printStackTrace();
                return "";  
            }  
        }  
        
        public JsonNode toJsonFromStr(Object object) throws Exception {  
            
           
            //	SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS
            //	SerializationConfig.Feature.
             	mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
            	//这是辅助设置，控制格式化输出。
            //	mapper.writeValueAsString(value)
            	//format output
            	return	mapper.readTree(object.toString());
            
        }  
      
        /** 
         * 设置转换日期类型的format pattern,如果不设置默认打印Timestamp毫秒数. 
         */  
        public void setDateFormat(String pattern) {  
//            if (StringUtils.isNotBlank(pattern)) {  
//                DateFormat df = new SimpleDateFormat(pattern);  
//                mapper.getSerializationConfig().setDateFormat(df);  
//                mapper.getDeserializationConfig().setDateFormat(df);  
//            }  
        }  
      
        /** 
         * 取出Mapper做进一步的设置或使用其他序列化API. 
         */  
        public ObjectMapper getMapper() {  
            return mapper;  
        }  
    }  