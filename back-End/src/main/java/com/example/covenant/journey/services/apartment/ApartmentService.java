package com.example.covenant.journey.services.apartment;

import com.example.covenant.journey.model.apartment.Apartment;
import com.example.covenant.journey.repositories.BaseRepository;
import com.example.covenant.journey.repositories.apartment.ApartmentRepository;
import com.example.covenant.journey.services.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApartmentService extends AbstractService<Apartment> {

	@Autowired
	private ApartmentRepository apartmentRepository;

	@Override
	protected BaseRepository<Apartment> getRepository() {
		return apartmentRepository;
	}
}
