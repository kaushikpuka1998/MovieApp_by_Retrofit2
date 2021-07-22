package com.kgstriversmoviejava.movie_app;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {


    private static AppExecutors instance;

    public static AppExecutors getInstance(){
        if(instance == null)
        {
            instance = new AppExecutors();
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
