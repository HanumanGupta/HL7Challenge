/*
 * #%L
 * ReservationService Service
 * %%
 * Copyright (C) 2011 - 2012 Talend Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package org.talend.services.reservationservice.service;

import java.util.logging.Logger;
//import javax.jws.WebMethod;
//import javax.jws.WebParam;
//import javax.jws.WebResult;
//import javax.jws.WebService;
//import javax.jws.soap.SOAPBinding;
//import javax.xml.bind.annotation.XmlSeeAlso;

import java.math.BigInteger;

import org.talend.services.crm.types.CRMStatusType;
import org.talend.services.reservation.types.ConfirmationType;
import org.talend.services.reservation.types.RESCarListType;
import org.talend.services.reservation.types.RESCarType;
import org.talend.services.reservation.types.RESProfileType;
import org.talend.services.reservation.types.RESStatucCodeType;
import org.talend.services.reservation.types.RESStatusType;
import org.talend.services.reservation.types.ReservationType;
import org.talend.services.reservationservice.ReservationService;

/**
 * This class was generated by Apache CXF 2.4.0-SNAPSHOT
 * 2011-01-17T11:54:21.894+02:00
 * Generated source version: 2.4.0-SNAPSHOT
 * 
 */

                     
public class ReservationServiceImpl implements ReservationService {

    private static final Logger LOG = Logger.getLogger(ReservationServiceImpl.class.getName());
	private org.talend.services.reservation.types.ObjectFactory resFactory;
	private org.talend.services.crm.types.ObjectFactory crmFactory;

	
	// ####################################################
	// Constructors
	// ####################################################

	public ReservationServiceImpl() {
		resFactory = new org.talend.services.reservation.types.ObjectFactory();
		crmFactory = new org.talend.services.crm.types.ObjectFactory();
	}
	
	// ####################################################
	// Public methods
	// ####################################################

	/**
	 * {@inheritDoc}
	 */ 
	public org.talend.services.reservation.types.RESCarListType getAvailableCars(
			org.talend.services.reservation.types.RESProfileType profile) {
		//
		logData("getAvailableCars", "request", profile);
		// Load customer data
		RESCarListType carList = getListOfAvailableCars(profile);

		logData("getAvailableCars", "response", carList);
		return carList;
	}

	/**
	 * {@inheritDoc}
	 */ 
	public org.talend.services.reservation.types.ConfirmationType getConfirmationOfReservation(
			org.talend.services.reservation.types.ReservationType reservation) {
		//
		logData("getConfirmationOfReservation", "request", reservation);
		// Load customer data
		ConfirmationType status = getCarReservation(reservation);

		logData("getConfirmationOfReservation", "response", status);
		return status;
	}

	/**
	 * {@inheritDoc}
	 */ 
	public org.talend.services.reservation.types.RESStatusType submitCarReservation(
			org.talend.services.reservation.types.ReservationType reservation) {
		//
		logData("submitCarReservation", "request", reservation);
		// Load customer data
		RESStatusType status = startCarReservation(reservation);

		logData("submitCarReservation", "response", status);
		return status;
	}

	// ####################################################
	// Private methods
	// ####################################################

	private RESCarListType getListOfAvailableCars(RESProfileType profile) {
		/*
		 * <xsd:enumeration value="NONE" /> <xsd:enumeration value="NORMAL" />
		 * <xsd:enumeration value="GOLD" /> <xsd:enumeration value="PLATINUM" />
		 */
		RESCarListType carList = resFactory.createRESCarListType();

		CRMStatusType status = profile.getCrmStatus();
		if (status == CRMStatusType.NONE) {
			carList.getCar().add(
					createCar("01-001-00099", "Panda", "Basic", "Fiat",
							"30.00", "45.00", "25.00"));
		} else if (status == CRMStatusType.NORMAL) {
			carList.getCar().add(
					createCar("01-001-00099", "Panda", "Basic", "Fiat",
							"30.00", "45.00", "25.00"));
			carList.getCar().add(
					createCar("01-002-00079", "Fiesta", "Basic", "Ford",
							"40.00", "55.00", "35.00"));
			carList.getCar().add(
					createCar("01-004-00022", "Golf", "Standard", "VW",
							"50.00", "75.00", "40.00"));
		} else if (status == CRMStatusType.GOLD) {
			carList.getCar().add(
					createCar("01-002-00079", "Fiesta", "Basic", "Ford",
							"40.00", "55.00", "35.00"));
			carList.getCar().add(
					createCar("01-004-00022", "Golf", "Standard", "VW",
							"50.00", "75.00", "40.00"));
			carList.getCar().add(
					createCar("01-008-00102", "320i", "Sport", "BMW", "60.00",
							"90.00", "45.00"));
			carList.getCar().add(
					createCar("01-015-00002", "MX5", "Sport", "Mazda", "65.00",
							"95.00", "50.00"));
		} else if (status == CRMStatusType.PLATINUM) {
			carList.getCar().add(
					createCar("01-004-00022", "Golf", "Standard", "VW",
							"50.00", "75.00", "40.00"));
			carList.getCar().add(
					createCar("01-004-00022", "320i", "Sport", "BMW", "60.00",
							"90.00", "45.00"));
			carList.getCar().add(
					createCar("01-004-00022", "MX5", "Sport", "Mazda", "65.00",
							"95.00", "50.00"));
			carList.getCar().add(
					createCar("01-004-00022", "LX400", "SUV", "Lexus", "85.00",
							"120.00", "100.00"));
			carList.getCar().add(
					createCar("01-004-00022", "E320", "Delux", "Mercedes",
							"95.00", "140.00", "100.00"));
		}

		return carList;
	}

	private ConfirmationType getCarReservation(ReservationType reservation) {
		ConfirmationType status = resFactory.createConfirmationType();
		status.setCustomer(reservation.getCustomer());
		status.setFromDate(reservation.getFromDate());
		status.setToDate(reservation.getToDate());
		
		String clazz = reservation.getCar().getClazz();
		status.setCreditPoints(new BigInteger("0"));

		if (clazz.equalsIgnoreCase("BASIC")) {
			status.setCreditPoints(new BigInteger("120"));
		} else if (clazz.equalsIgnoreCase("STANDRAD")) {
			status.setCreditPoints(new BigInteger("180"));
		} else if (clazz.equalsIgnoreCase("SPORT")) {
			status.setCreditPoints(new BigInteger("210"));
		} else if (clazz.equalsIgnoreCase("SUV")) {
			status.setCreditPoints(new BigInteger("280"));
		} else if (clazz.equalsIgnoreCase("DELUX")) {
			status.setCreditPoints(new BigInteger("280"));
		}

		// status.setCode(RESStatucCodeType.OK);
		if (reservation.getReservationId() != null) {
			status.setReservationId(reservation.getReservationId());
		}
		
		status.setDescription("Your reservation is booked!");
		RESCarType car = reservation.getCar();
		status.setCar(car);
		
		if (car != null) {
			// check design model
			String model = car.getDesignModel();

			if ((model != null) && (model.equalsIgnoreCase("GOLF"))) {
				//
				// status.setCode(RESStatucCodeType.FAILED);
				status.setDescription("Your reservation failed! This car is booked by an other customer already.");
			}
		}

		return status;
	}

	private RESStatusType startCarReservation(ReservationType reservation) {
		//
		RESStatusType status = resFactory.createRESStatusType();
		status.setCode(RESStatucCodeType.OK);
		status.setId(generateID());
		status.setDescription("Your reservation is booked!");

		return status;
	}

	private RESCarType createCar(String id, String designModel, String clazz,
			String brand, String rateDay, String rateWeekend, String guarantee) {
		RESCarType car = resFactory.createRESCarType();
		car.setCarId(id);
		car.setBrand(brand);
		car.setClazz(clazz);
		car.setDesignModel(designModel);
		car.setRateDay(rateDay);
		car.setRateWeekend(rateWeekend);
		car.setSecurityGuarantee(guarantee);
		return car;
	}

	private void logData(String operation, String option, Object data) {
		System.out.println("###############################################");
		System.out.println(operation + "() invoked ... " + option + " data:");
		System.out.println(data);
		System.out.println("###############################################");
	}

	private static final String generateID() {
		String string = Long.toString(System.nanoTime());
		return "SGA-" + string.substring(string.length() - 6);
	}
}
