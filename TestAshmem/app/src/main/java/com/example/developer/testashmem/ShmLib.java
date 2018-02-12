package com.example.developer.testashmem;

import android.os.ParcelFileDescriptor;

import java.io.IOException;
import java.util.HashMap;


public class ShmLib {
    static {
        System.loadLibrary("native-lib");
    }
    private static HashMap<String,Integer> memAreas = new HashMap<>();

    public static int OpenSharedMem(String name, int size, boolean create)  {
        Integer i = memAreas.get(name);
        if (create && i != null)
            return -1;
        if (i == null){
            i = new Integer(getFD(name, size));
            memAreas.put(name, i);
        }
        return i.intValue();

    }
    public static int setValue(String name, int pos, int val){
        Integer fd = memAreas.get(name);
        if(fd != null)
            return setVal(fd.intValue(),pos,val);
        return -1;
    }
    public static int getValue(String name, int pos ){
        Integer fd = memAreas.get(name);
        if(fd != null)
            return getVal(fd.intValue(),pos);
        return -1;
    }
    private static native int setVal(int fd,int pos, int val);
    private static native int getVal(int fd,int pos);
    private static native int getFD(String name , int size);

}
