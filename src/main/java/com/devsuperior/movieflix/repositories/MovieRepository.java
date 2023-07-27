package com.devsuperior.movieflix.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	@Query(value = "SELECT obj FROM Movie obj JOIN FETCH obj.genre WHERE obj.id = :id")
	Optional<Movie> searchById(Long id);

}
