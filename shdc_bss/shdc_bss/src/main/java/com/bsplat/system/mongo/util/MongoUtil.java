package com.bsplat.system.mongo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;

import com.bsplat.system.common.util.DateUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * MongoDB辅助工具类
 * @author jdkleo
 * @author Jason
 *
 */

public class MongoUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;   
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		System.out.println("Setting application context here");
		MongoUtil.applicationContext = applicationContext;  	
	}
	
	 
	final static Logger logger = Logger.getLogger(MongoUtil.class);
	
	public final static String ADD="add";
	public final static String UPDATE="update";
	public final static String DELETE="drop";

	public static Mongo getMongo() {
		return (Mongo)applicationContext.getBean("mongo");
    }
	
	public static Mongo getMongo4Write() {
		return (Mongo)applicationContext.getBean("mongo4Write");
    }
	
	public static DB getDB(){
		return ((MongoDbFactory)applicationContext.getBean("mongoDbFactory")).getDb();
	}
	
	public static DB getDB4Write(){
		return ((MongoDbFactory)applicationContext.getBean("mongoDbFactory4Write")).getDb();
	}
	
	
	public static MongoOperations getMongoOperations(){
		return (MongoOperations) applicationContext.getBean("mongoOperations");
	} 
	
	public static MongoOperations getMongoOperations4Write(){
		return (MongoOperations) applicationContext.getBean("mongoOperations4Write");
	} 
	
	
	/**
	 *  copy pojo到BasicDBObject
	 * @param b
	 * @param pojo
	 * @return
	 */
	public static BasicDBObject append(BasicDBObject b,Object pojo) {
		
		 Method[]  ms = pojo.getClass().getMethods();
		for (Method m : ms) {
			String name = m.getName();
			if (name.startsWith("get") && !"getClass".equals(name)){
				Object value = null;
				try {
					value = m.invoke(pojo);
					//id为空的让系统自己创建一个ObjectId
					if ( value==null && "get_id".equals(name)){
						continue;
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				String key = name.length()>3?(name.substring(3,4).toLowerCase()+name.substring(4)):name.substring(3).toLowerCase();
				if (value instanceof Date){
					value = ((Date)value).getTime();
				}
				b.put(key, value);
			}
		}
		return b;
	}
	

	/**
	 * 从BasicDBObject转换为pojo
	 * @param <T>
	 * @param class1 
	 * @param result
	 * @return
	 */
	public static <T> T from(BasicDBObject b, Class<T> pojoClass) {
		if (b==null){
			return null;
		}
		T pojo = null;
		try {
			 pojo = pojoClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		Method[]  ms = pojoClass.getMethods();
		for (Method m : ms) {
			String name = m.getName();
			if (name.startsWith("set")){
				Object paramValue = null;
				String key = name.length()>3?(name.substring(3,4).toLowerCase()+name.substring(4)):name.substring(3).toLowerCase();
				paramValue = getValue(b,key,m.getParameterTypes()[0]);
				try {
					 m.invoke(pojo, paramValue);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return pojo;
	}
	
	 /**
	  * 根据class类型返回正确的结果
	 * @param b 
	 * @param key
	 * @param class1
	 * @return
	 */
	private static Object getValue(BasicDBObject b, String key, Class<?> type) {
		
			if (int.class.isAssignableFrom(type) || Integer.class.isAssignableFrom(type)){
				try{
					
					return b.getInt(key);
				}catch(Exception e){
					//logger.error(e.getMessage(),e);
					if (Integer.class.isAssignableFrom(type)){
						return null;
					}
					return 0;
				}
			}
			else if (long.class.isAssignableFrom(type) || Long.class.isAssignableFrom(type)){
				try{
					return b.getLong(key);
				}catch(Exception e){
					//logger.error(e.getMessage(),e);
					if (Long.class.isAssignableFrom(type)){
						return null;
					}
					return 0L;
				}
			}
			else if (double.class.isAssignableFrom(type) || Double.class.isAssignableFrom(type)){
				try{
					return b.getDouble(key);
				}catch(Exception e){
					//logger.error(e.getMessage(),e);
					if (Double.class.isAssignableFrom(type)){
						return null;
					}
					return 0D;
				}
			}
			else if (boolean.class.isAssignableFrom(type) || Boolean.class.isAssignableFrom(type)){
				try{
					return b.getBoolean(key);
				}catch(Exception e){
					//logger.error(e.getMessage(),e);
					return false;
				}
			}
			else if (String.class.isAssignableFrom(type)){
				try{
					return b.getString(key);
				}catch(Exception e){
					//logger.error(e.getMessage(),e);
					return null;
				}
			}
			else if (Date.class.isAssignableFrom(type)){
				try{
					return new Date(b.getLong(key));
				}catch(Exception e){
					//logger.error(e.getMessage(),e);
					return null;
				}
			}
			try{
				return b.get(key);
			}catch(Exception e){
				//logger.error(e.getMessage(),e);
				return null;
			}
	}

	/**
     * 读取JS方法
     * @param jsFilePath
     * @param functionName
     * @return
     */
    public static String getFunction(String jsFilePath,String functionName){
        if (jsFilePath.contains(".jar!/")){
        	return getFunctionFromJar(jsFilePath,functionName);
        }
        File file = new File(jsFilePath);
        return getFunction(file,functionName);
    }
   
     /**
      * 读取jar包中的js
     * @param jsFilePath
     * @param functionName
     * @return
     */
    private static String getFunctionFromJar(String jsFilePath, String functionName) {
		if (!jsFilePath.startsWith("jar:")){
			jsFilePath = "jar:"+jsFilePath;
		}
		InputStream inr = null;
		InputStreamReader isr = null;
		try {
			URL url = new URL(jsFilePath);
			inr = url.openStream();
			isr =  new InputStreamReader(inr);  
			return getFunctionByReader(isr, functionName);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
					if (inr!=null){
							inr.close();
					}
					if (isr!=null){
						isr.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
		
	}
    
    /**
     * 读取JS方法
    * @param file
    * @param functionName
    * @return
    */
   public static String getFunction(File file, String functionName) {
           if (file.exists()) {
               FileReader fr = null;
               try {
                   fr = new FileReader(file);
                   return getFunctionByReader(fr, functionName);
               } catch (FileNotFoundException e) {
                   e.printStackTrace();
               } finally{
                    try {
                        if (fr!=null)
                            fr.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
           return null;
       }

	/**
	 * 读流
	 * @param r
	 * @param functionName
	 * @return
	 */
	private static String getFunctionByReader(Reader r,String functionName) {
		BufferedReader br = new BufferedReader(r);
         StringBuilder s = new StringBuilder();
         String line = "";
         boolean readding = false;
         try {
	         while ((line = br.readLine()) != null) {
	             String line_trim = line.trim();
	             if (!line_trim.equals(line_trim.replaceFirst("function\\s+"+functionName+"\\(",""))){
	                 readding = true;
	             }else if (readding && line_trim.startsWith("function ")){
	                     break;
	             }else if (line_trim.startsWith("//")){
	                 continue;
	             }
	             if (readding){
	            	int ggIndex = line_trim.lastIndexOf("//");
	   	            int syhIndex = line_trim.lastIndexOf("\""); 
	   	            int dyhIndex = line_trim.lastIndexOf("'"); 
//	   	            System.out.println(line_trim+"==>"+ggIndex+" "+syhIndex+" "+dyhIndex);
	   	             if (ggIndex!=-1 && ggIndex>syhIndex && ggIndex>dyhIndex){
	   	            	 line_trim = line_trim.substring(0,ggIndex);
//	   	            	 System.out.println(line_trim);
	   	             }
	                 s.append(line_trim);
	             }
	         }
	         return s.toString().trim().replaceAll("/\\*.*?\\*/", "");
         }catch(IOException e){
        	return null;
         }finally{
             try {
                 if (r!=null)
                     r.close();
                 if (br!=null)
                     br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	/**
	 * 新增业务对象WITH版本(single)
	 * @param pojo
	 * @param dc
	 * @param opType
	 * @return
	 */
	public static BasicDBObject appendWithVersion(Object pojo,DBCollection dc,String opType,boolean single){
		BasicDBObject b = appendWithVersion(pojo,dc,opType);
		if (single){
			BasicDBObject bb = (BasicDBObject)b.get("mongoTransEntity");
			bb.put("opStatus", 1);
			//bb.put("opType", opType);
			return b.append("mongoTransEntity", bb);
		}
		return b;
	}
	
	/**
	 * 新增业务对象WITH版本(mult)
	 * @param b
	 * @param pojo
	 * @param dc
	 * @param opType
	 * @return
	 */
	public static BasicDBObject appendWithVersion(Object pojo,DBCollection dc,String opType) {
		BasicDBObject b = new BasicDBObject();
		 b = append(b, pojo);
		 BasicDBObject bb = null;
		 if (b.containsField("_id")){
			 String theOldId = b.getString("_id");
			 DBObject dbObject =  dc.findOne(new BasicDBObject("_id",theOldId));
			 if (dbObject!=null){
				  BasicDBObject b2 = (BasicDBObject) dbObject.get("mongoTransEntity");
				  bb = nextVersionMongoTransObject(opType,theOldId,((BasicDBObject)dbObject).getString("hashCode"), b2.getInt("version"));
//				  dbObject.get("_id").getClass();
				  b.put("_id",new ObjectId().toString());
			 }else{
				 bb = firstVersionMongoTransObject(opType);
			 }
		 }else{
			 bb = firstVersionMongoTransObject(opType);
		 }
		return b.append("mongoTransEntity", bb);
	}

	/**
	 * 下一版本的事务对象
	 * @param opType
	 * @param theOldId
	 * @param theOldVersion
	 * @return
	 */
	private static BasicDBObject nextVersionMongoTransObject(String opType,
			String theOldId,String theOldHashCode,  int theOldVersion) {
		  BasicDBObject bb = new BasicDBObject();
		  bb.put("opStatus", 0);
		  bb.put("opType",opType);
		  bb.put("theOldId",theOldId);
		  bb.put("theOldHashCode", theOldHashCode);
		  bb.put("version",theOldVersion+1);
		  bb.put("createDate",DateUtil.currentTimeMillis());
		  return bb;
	}

	/**
	 * 第一版本的事务对象
	 * @param opType
	 * @return
	 */
	private static BasicDBObject firstVersionMongoTransObject(String opType) {
		BasicDBObject bb = new BasicDBObject();
		  bb.put("opStatus", 0);
		  bb.put("opType",opType);
		  bb.put("theOldId",null);
		  bb.put("theOldHashCode",null);
		  bb.put("version",1);
		  bb.put("createDate",DateUtil.currentTimeMillis());
		  return bb;
	}


}
