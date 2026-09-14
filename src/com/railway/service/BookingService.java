package com.railway.service;

import java.util.List;

import com.railway.beans.HistoryBean;
import com.railway.beans.TrainException;

public interface BookingService {

	public List<HistoryBean> getAllBookingsByCustomerId(String customerEmailId) throws TrainException;

	public HistoryBean createHistory(HistoryBean bookingDetails) throws TrainException;

}
