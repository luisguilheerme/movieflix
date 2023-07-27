package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class ReviewService {
	
	@Autowired
	ReviewRepository repository;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {	
		
		Review entity = new Review();	
		Movie movie = movieRepository.getReferenceById(dto.getMovieId());		
		UserDTO userDto = userService.getMe();
		User user = userRepository.getReferenceById(userDto.getId());
		entity.setText(dto.getText());
		entity.setMovie(movie);
		entity.setUser(user);			
		entity = repository.save(entity);		
		return new ReviewDTO(entity);		
		
	}	

}
