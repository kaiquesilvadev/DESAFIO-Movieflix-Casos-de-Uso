package com.devsuperior.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService service;

	@PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
	@GetMapping()
	public ResponseEntity<Page<MovieCardDTO>> listaTodos(Pageable pageable , @RequestParam(defaultValue = "0")String genreId) {
		Page<MovieCardDTO> list = service.listaTodos( pageable , genreId);
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
	@GetMapping("/{id}")
	public ResponseEntity<MovieDetailsDTO> buscaPorId( @PathVariable Long id) {
		MovieDetailsDTO detailsDTO = service.buscaPorId(id);
		return ResponseEntity.ok().body(detailsDTO);
	}
	
	@PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
	@GetMapping("/{id}/reviews")
	public ResponseEntity<List<ReviewDTO>> buscaReviewsPorId(@PathVariable Long id) {
	 List<ReviewDTO> reviewDTO = service.buscaReviewsPorId(id);
		return ResponseEntity.ok().body(reviewDTO);
	}
}
