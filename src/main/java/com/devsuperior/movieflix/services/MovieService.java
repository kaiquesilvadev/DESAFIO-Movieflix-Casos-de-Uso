package com.devsuperior.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.EntidadeNaoEncontradaException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;

	@Transactional(readOnly = true)
	public Page<MovieCardDTO> listaTodos(Pageable pageable, String genreId) {
		
		if (pageable.getSort().isUnsorted()) {
			pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("title"));
		}
		
		Long idDeGenre = Long.parseLong(genreId);

		Page<Movie> lista = repository.findAllByGenreIdOrAllOrdered(pageable, idDeGenre);
		return lista.map(m -> new MovieCardDTO(m));
	}

	@Transactional(readOnly = true)
	public MovieDetailsDTO buscaPorId(Long id) {
		Movie movie = repository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException("entidade com o id :" + id + " não foi encontrada"));
		return new MovieDetailsDTO(movie);
	}

	@Transactional(readOnly = true)
	public List<ReviewDTO> buscaReviewsPorId(Long id) {
		Movie movie = repository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException("entidade com o id :" + id + " não foi encontrada"));
		
		return movie.getReviews().stream().map(r -> new ReviewDTO(r)).toList();
	}
	
}
