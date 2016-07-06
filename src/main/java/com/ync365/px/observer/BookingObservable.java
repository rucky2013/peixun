package com.ync365.px.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.ync365.px.model.ProjectStudentBo;
public class BookingObservable extends Observable implements
    InitializingBean {
	private static Logger logger = LoggerFactory
			.getLogger(BookingObservable.class);

	private List<Observer> observers = new ArrayList<Observer>();
	
	public void notify(ProjectStudentBo projectStudentBo) {
		logger.info("抢票人员Id;{}", projectStudentBo.getUserId());
		setChanged();
		notifyObservers(projectStudentBo);
	}
	
	@Override
    public void afterPropertiesSet() throws Exception {
        logger.info("初始化监听器");
        for(Observer obs:observers){
            addObserver(obs);
            logger.info("初始化："+obs.getClass().getName());
        }
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
