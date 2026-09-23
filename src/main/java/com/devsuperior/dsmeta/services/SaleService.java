package com.devsuperior.dsmeta.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import com.devsuperior.dsmeta.dto.SaleReportDTO;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	public Page<SaleReportDTO> getReport(
			String minDate,
			String maxDate,
			String name,
			int page,
			int size
	) {

		LocalDate today = LocalDate.ofInstant(
				Instant.now(),
				ZoneId.systemDefault()
		);

		LocalDate min;
		LocalDate max;

		if (maxDate.equals("")) {
			max = today;
		}
		else {
			max = LocalDate.parse(maxDate);
		}

		if (minDate.equals("")) {
			min = max.minusYears(1);
		}
		else {
			min = LocalDate.parse(minDate);
		}

		if (name == null) {
			name = "";
		}

		Pageable pageable = PageRequest.of(page, size);

		return repository.searchReport(min, max, name, pageable);
	}
}
