package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.EntidadeNaoEncontradaException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private AuthService AuthService;

	@Transactional(propagation = Propagation.SUPPORTS)
	public ReviewDTO salva(ReviewDTO dto) {

		User user = AuthService.authenticated();

		Review review = new Review();
		Movie movie = movieRepository.findById(dto.getMovieId())
				.orElseThrow(() -> new EntidadeNaoEncontradaException("id de MovieId não foi encontrado"));

		review.setUser(user);
		review.setMovie(movie);
		review.setText(dto.getText());

		return new ReviewDTO(repository.save(review));

	}
}
