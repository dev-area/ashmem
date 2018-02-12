// ISharedMem.aidl
package com.example.sharedmemlib;

import android.os.ParcelFileDescriptor;

interface ISharedMem {
    ParcelFileDescriptor OpenSharedMem(String name, int size, boolean create);
}
