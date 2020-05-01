package com.girlesc.enguard.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DiskIOThreadExecutor implements Executor {

    private final Executor mDiskIO;

    public DiskIOThreadExecutor() {
        this.mDiskIO = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(Runnable runnable) {
        mDiskIO.execute(runnable);
    }
}
