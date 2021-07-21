package com.kgstriversmoviejava.movie_app;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutor {


    private static AppExecutor instance;

    public static AppExecutor getInstance(){
        if(instance == null)
        {
            instance = new AppExecutor();
        }

        return instance;
    }

    //Using Background shredding data collecting from web service to Livedata
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService netWorkIO()
    {
        return mNetworkIO;
    }

}
