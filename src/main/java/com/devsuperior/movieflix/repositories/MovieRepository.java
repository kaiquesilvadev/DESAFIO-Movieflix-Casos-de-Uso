package com.devsuperior.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("SELECT m FROM Movie m LEFT JOIN m.genre g WHERE (:genreId = 0 OR g.id = :genreId)")
	Page<Movie> findAllByGenreIdOrAllOrdered(Pageable pageable, Long genreId);
}