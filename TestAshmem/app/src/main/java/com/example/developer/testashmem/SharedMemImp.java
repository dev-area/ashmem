package com.example.developer.testashmem;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

import com.example.sharedmemlib.ISharedMem;

import java.io.IOException;
import java.util.HashMap;


public class SharedMemImp extends ISharedMem.Stub {
    @Override
    public ParcelFileDescriptor OpenSharedMem(String name, int size, boolean create) throws RemoteException {
        int fd =  ShmLib.OpenSharedMem(name,size,create);
        try {
            return ParcelFileDescriptor.fromFd(fd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
