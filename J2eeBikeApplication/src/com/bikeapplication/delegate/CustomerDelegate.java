package com.bikeapplication.delegate;

import java.util.List;
import java.util.logging.Logger;

import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.RentCalculatorBeanClass;
import com.bikeapplication.constants.Constants;
import com.bikeapplication.dao.BikeApplicationDao;

public class CustomerDelegate {
	Logger logger = Logger.getLogger(CustomerDelegate.class.getName());
	BikeApplicationDao dao = new BikeApplicationDao();

	public List<BikeBeanClass> viewAllBikes() {
		List<BikeBeanClass> bikeBeanList = dao.viewAllBikes();
		return bikeBeanList;
	}
	
	public List<BikeBeanClass> viewAvailableBikes() {
		List<BikeBeanClass> bikeBeanList = dao.viewAvailableBikes();
		return bikeBeanList;
	}
	
	public boolean rentBike(RentBeanClass rentBean) {
		return dao.rentBike(rentBean);
	}
	
	public List<BikeBeanClass> viewUserRentedBikes(int userId) {
			List<BikeBeanClass> bikeBeanList = dao.userRentedBike(userId);
			return bikeBeanList;
	}
	
	public RentCalculatorBeanClass returnBike(int userId,int bikeId, String registrationNumber) {
		return dao.returnBike(userId, bikeId, registrationNumber);
	}
	
	public RentCalculatorBeanClass viewRemainingDuration(int userId, int bikeId, String registrationNumber) {
		RentBeanClass rentBean = dao.getRentDetails(userId, bikeId, registrationNumber);
		return dao.rentCalculator(rentBean, bikeId);
	}
}
