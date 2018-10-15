package com.bsplat.system.mongo.util;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class ResourceUtils
{
  private static final Logger logger = Logger.getLogger(ResourceUtils.class);

  private static Map lastModifiedMap = new HashMap();

  public static List getAndMarkFiles(String[] fileNames)
  {
    if (logger.isDebugEnabled()) {
      logger.debug("getAndMarkFiles(String[]) - start");
      logger.debug("getAndMarkFiles(String[]) - fileNames=");
    }

    List files = getFiles(fileNames);

    for (int i = 0; i < files.size(); ++i) {
      File file = (File)files.get(i);
      if ((file != null) && (file.exists())) {
        String filePath = file.getAbsolutePath();
        String lastModified = String.valueOf(file.lastModified());
        lastModifiedMap.put(filePath, lastModified);
        if (logger.isDebugEnabled()) {
          logger.debug("getAndMarkFiles(String[]) - i=" + i + ", filePath=" + filePath + ", lastModified=" + lastModified);
        }
      }

    }

    if (logger.isDebugEnabled()) {
      logger.debug("getAndMarkFiles(String[]) - end");
    }
    return files;
  }

  public static List getFiles(String[] fileNames)
  {
    if (logger.isDebugEnabled()) {
      logger.debug("getFiles(String[]) - start");
    //  logger.debug("getFiles(String[]) - fileNames");
    }
    if (fileNames == null) {
      return null;
    }
    List files = new ArrayList();
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    try {
      for (int i = 0; i < fileNames.length; ++i) {
        Resource[] resources = resolver.getResources(fileNames[i]);
        for (int j = 0; j < resources.length; ++j)
          try {
            File file = resources[j].getFile();
            files.add(file);
          } catch (Exception e) {
            if (logger.isDebugEnabled())
              logger.debug("getFiles(String[]) - e=" + e);
          }
      }
    }
    catch (Exception e)
    {
      logger.warn("getFiles(String[]) - exception ignored", e);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("getFiles(String[]) - end");
    }
    return files;
  }

  public static List getAndMarkChangedFiles(String[] fileNames)
  {
    if (logger.isDebugEnabled()) {
      logger.debug("getAndMarkChangedFiles(String[]) - start");
      //logger.debug("getAndMarkChangedFiles(String[]) - fileNames=" + LangUtils.Array2String(fileNames));
    }

    List changedFiles = new ArrayList();
    List allFiles = getFiles(fileNames);
    for (int i = 0; i < allFiles.size(); ++i) {
      File file = (File)allFiles.get(i);
      if ((file != null) && (file.exists())) {
        String filePath = file.getAbsolutePath();
        String s_lastModified = (String)lastModifiedMap.get(filePath);
        if (s_lastModified == null) {
          s_lastModified = "0";
        }
        long lastModified = Long.parseLong(s_lastModified);
        long file_lastModified = file.lastModified();

        if (logger.isDebugEnabled()) {
          logger.debug("getAndMarkChangedFiles(String[]) - i=" + i + ", filePath=" + filePath + ", lastModified=" + lastModified + ", file_lastModified=" + file_lastModified);
        }

        if (file_lastModified > lastModified) {
          lastModifiedMap.put(filePath, String.valueOf(file_lastModified));
          changedFiles.add(file);
        }
      }

    }

    if (logger.isDebugEnabled()) {
      logger.debug("getAndMarkChangedFiles(String[]) - end");
    }
    return changedFiles;
  }

  public static String resourceKey(String key, String suffix)
  {
    if ((key == null) || (key.equals(""))) {
      return null;
    }
    if ((suffix == null) || (suffix.trim().equals(""))) {
      return key;
    }
    String returnString = key + "_" + suffix;
    return returnString;
  }
}