package com.bikeapplication.delegate;

import java.util.List;
import java.util.logging.Logger;

import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.UserBeanClass;
import com.bikeapplication.dao.BikeApplicationDao;

public class AdminDelegate {
	Logger logger = Logger.getLogger(AdminDelegate.class.getName());
	BikeApplicationDao dao = new BikeApplicationDao();
	
	public List<BikeBeanClass> viewAllBikes() {
		return dao.viewAllBikes();
	}
	
	public List<RentBeanClass> viewAllRentedBikes() {
		return dao.viewAllRentedBikes();
	}
	
	public List<UserBeanClass> viewAllUsers() {
		return dao.viewAllUsers();
	}
	
	
}
