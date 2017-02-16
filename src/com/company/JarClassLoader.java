package com.company;

import java.io.*;
import java.net.URL;
import java.util.Hashtable;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Tanusha on 15/02/2017.
 */
public class JarClassLoader extends ClassLoader {
        private String jarFile = "Animal.jar"; //Path to the jar file
        private Hashtable classes = new Hashtable(); //used to cache already defined classes

    public JarClassLoader() {
            super(JarClassLoader.class.getClassLoader()); //calls the parent class loader's constructor
        }

        public Class loadClass(String className) throws ClassNotFoundException {
            return findClass(className);
        }

        public Class findClass(String className) {
            byte classByte[];
            Class result = null;

            result = (Class) classes.get(className); //checks in cached classes
            if (result != null) {
                return result;
            }

            try {
                return findSystemClass(className);
            } catch (Exception e) {
            }

            try {
                JarFile jar = new JarFile(jarFile);
                JarEntry entry = jar.getJarEntry(className + ".class");
                InputStream is = jar.getInputStream(entry);
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                int nextValue = is.read();
                while (-1 != nextValue) {
                    byteStream.write(nextValue);
                    nextValue = is.read();
                }

                classByte = byteStream.toByteArray();
                result = defineClass(className, classByte, 0, classByte.length, null);
                classes.put(className, result);
                return result;
            } catch (Exception e) {
                return null;
            }
        }

        public static String downloadFiles() throws IOException {
        String urlStr = "https://github.com/EugeneBobrov/coursera-JS-course/blob/master/Animal.jar?raw=true";

        try {
            URL url = new URL(urlStr);
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream("Animal.jar");
            byte[] buffer = new byte[1024];
            int count=0;
            while((count = bis.read(buffer,0,1024)) != -1)
            {
                fis.write(buffer, 0, count);
            }
            fis.close();
            bis.close();
            //System.out.println("done");

        } catch (IOException e) {
            e.printStackTrace();
        }
            return urlStr;
    }

    }
