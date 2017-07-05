package com.avinash.examples.resourcebundle;

import java.io.File;
import java.net.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Application {

    public static void main(String[] args) {

        // prep for class loader
        try {
            URL locationURL = Application.class.getProtectionDomain().getCodeSource().getLocation();
            String path = locationURL.getPath();
            int index = path.lastIndexOf('/');
            if (index > 0) {
                path = path.substring(0,index);
            }
            File file = new File(path);
            URL[] urls = new URL[]{file.toURI().toURL()};
            ClassLoader loader = new URLClassLoader(urls);
            // en_US
            System.out.println("Current Locale: " + Locale.getDefault());
            ResourceBundle mybundle = ResourceBundle.getBundle("MyLabels", Locale.getDefault(), loader);

            // read MyLabels_en_US.properties
            System.out.println("Say how are you in US English: " + mybundle.getString("how_are_you"));

            Locale.setDefault(new Locale("ms", "MY"));

            // read MyLabels_ms_MY.properties
            System.out.println("Current Locale: " + Locale.getDefault());
            mybundle = ResourceBundle.getBundle("MyLabels", Locale.getDefault(), loader);
            System.out.println("Say how are you in Malaysian Malaya language: " + mybundle.getString("how_are_you"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}