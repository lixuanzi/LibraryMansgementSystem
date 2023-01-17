package com.lizixuan.util;

public class PathUtils {

    private static final String P_PATH="images/";

    public static String getRealPath(String relativePath){

        return P_PATH+relativePath;
    }

}
