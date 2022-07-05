package com.nelcionedias.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nelcionedias.workshopmongo.domain.Post;
import com.nelcionedias.workshopmongo.domain.User;
import com.nelcionedias.workshopmongo.dto.AuthorDTO;
import com.nelcionedias.workshopmongo.dto.CommentDTO;
import com.nelcionedias.workshopmongo.repository.PostRepository;
import com.nelcionedias.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 =  new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 =  new Post(null, sdf.parse("23/03/2022"),"Bom dia", "Acordei Fliz Joje!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa Viagem Mano", sdf.parse("21/01/2022"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("24/01/2022"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("26/01/2022"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
				
		maria.getPost().addAll(Arrays.asList(post1,post2));
		
		userRepository.save(maria);
		
	}

}
