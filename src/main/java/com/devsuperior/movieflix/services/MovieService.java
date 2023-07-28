package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewMinDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;


@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	
	@Transactional(readOnly = true)
	public MovieDetailsDTO findById(Long id) {		
		Optional<Movie> obj = repository.searchById(id);		
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Resource not Found"));
		return new MovieDetailsDTO(entity);
	}	
	
	@Transactional(readOnly = true)
	public Page<MovieCardDTO> findByGenre(Long id, Pageable pageable) {
		
		Page<Movie> result = null;
		
		if(id == 0) {
			result = repository.searchAll(pageable);			
		}
		else{
			result = repository.findByGenreIdOrderByTitle(id, pageable);
		}	
		
		if(result.isEmpty()) {
			throw new ResourceNotFoundException("Resource not Found");
		}		
		
		return result.map(x -> new MovieCardDTO(x));
	}

	@Transactional(readOnly = true)
	public List<ReviewMinDTO> findReviews(Long id) {
		
		List<Review> result = reviewRepository.findReviews(id);		
		
		if(result.isEmpty()) {
			throw new ResourceNotFoundException("Resource not Found");
		}
		else {
		return result.stream().map(x -> new ReviewMinDTO(x)).toList();
		}
	}	
		
	
}
