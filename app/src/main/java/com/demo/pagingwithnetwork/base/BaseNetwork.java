package com.demo.pagingwithnetwork.base;

import io.reactivex.Observable;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class BaseNetwork {

    public Object getData(Observable observable) throws Exception {
        FutureTask task = new FutureTask<>(() -> {
            AtomicReference<Object> object = new AtomicReference<>();
            observable.subscribe(o -> object.set(o), throwable -> object.set(throwable));
            return object.get();
        });
        new Thread(task).start();
        return task.get(8000, TimeUnit.MILLISECONDS);
    }
}
