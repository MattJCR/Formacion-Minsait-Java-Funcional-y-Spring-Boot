package com.example.demo;

import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Library.book.BookEntity;
import com.example.demo.Library.book.BookRepository;
import com.example.demo.Library.library.LibraryEntity;
import com.example.demo.Library.library.LibraryRepository;
import com.example.demo.Library.user.UserEntity;
import com.example.demo.Library.user.UserRepository;
import com.example.demo.beer.BeerEntity;
import com.example.demo.beer.BeerRepository;
import com.example.demo.beer.BeerService;
import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerRepository;
import com.example.demo.nasa.Nasa;
import com.example.demo.nasa.NasaRepository;
import com.example.demo.pub.PubEntity;
import com.example.demo.pub.PubRepository;
import com.example.demo.trivial.PreguntaEntity;
import com.example.demo.trivial.PreguntaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner demoCustomer(CustomerRepository repository) {
	// 	return (args) -> {
	// 		repository.save(new Customer("Jack", "Bauer", "jackb@email.com"));
	// 		repository.save(new Customer("Chloe", "O'Brian", "chloeo@email.com"));
	// 		repository.save(new Customer("Kim", "Bauer", "kimb@email.com"));
	// 		repository.save(new Customer("David", "Palmer", "davidp@email.com"));
	// 		repository.save(new Customer("Michelle", "Dessler", "michelled@email.com"));
	// 		for (Customer customer : repository.findAll()) {
	// 			log.info(customer.toString());
	// 		}
	// 	};
	// }

	// @Bean
	// public CommandLineRunner demoLibrary(BookRepository bookRepository, UserRepository userRepository,
	// 		LibraryRepository libraryRepository) {
	// 	return (args) -> {
	// 		bookRepository.save(new BookEntity("0000001", "Harry Potter", "JK", "Description test"));
	// 		bookRepository.save(new BookEntity("0000002", "Java 8", "SU", "Description test"));
	// 		bookRepository.save(new BookEntity("0000003", "Programacion funcional", "Anthony", "Description test"));
	// 		for (BookEntity book : bookRepository.findAll()) {
	// 			log.info(book.toString());
	// 		}
	// 		userRepository.save(new UserEntity("Mateo", "Cabello", "mateo@email.com"));
	// 		userRepository.save(new UserEntity("Rosa", "Martinez", "rosa@email.com"));
	// 		userRepository.save(new UserEntity("Juan", "Sanchez", "juan@email.com"));
	// 		for (UserEntity user : userRepository.findAll()) {
	// 			log.info(user.toString());
	// 		}
	// 		libraryRepository.save(new LibraryEntity("Libreria de Madrid", "Madrid"));
	// 		libraryRepository.save(new LibraryEntity("Libreria de Barcelona", "Barcelona"));
	// 		LibraryEntity libraryToupdate = libraryRepository.findById(1L).get();
	// 		BookEntity bookToUpdate = bookRepository.findById(1L).get();
	// 		// bookToUpdate.setLibrary(libraryToupdate);
	// 		bookRepository.save(bookToUpdate);
	// 		UserEntity userToUpdate = userRepository.findById(1L).get();
	// 		List<LibraryEntity> UserListToLibrary = new ArrayList<LibraryEntity>();
	// 		UserListToLibrary.add(libraryToupdate);
	// 		// userToUpdate.setLibraries(UserListToLibrary);
	// 		userRepository.save(userToUpdate);
	// 		for (LibraryEntity library : libraryRepository.findAll()) {
	// 			log.info(library.toString());
	// 		}
	// 	};
	// }

	// @Bean
	// public CommandLineRunner demoNasa(NasaRepository repository) {
	// 	return (args) -> {
	// 		repository.save(new Nasa("2021-11-09",
	// 				"Why would you want to fake a universe? For one reason -- to better understand our real universe. Many astronomical projects seeking to learn properties of our universe now start with a robotic telescope taking sequential images of the night sky.",
	// 				"All of These Space Images are Fake Except One",
	// 				"https://apod.nasa.gov/apod/image/2111/AIapods01_Geach_960.jpg", "image"));

	// 		repository.save(new Nasa("2021-11-08",
	// 				"Why, sometimes, does part of the Sun's atmosphere leap into space? The reason lies in changing magnetic fields that thread through the Sun's surface.",
	// 				"A Filament Leaps from the Sun",
	// 				"https://www.youtube.com/embed/7NykS2kv_k8?playlist=7NykS2kv_k8&loop=1;rel=0&autoplay=1", "video"));
	// 		for (Nasa nasa : repository.findAll()) {
	// 			log.info(nasa.toString());
	// 		}
	// 	};
	// }

	// @Bean
	// public CommandLineRunner demoBeer(BeerService service, BeerRepository repository) {
	// 	return (args) -> {

	// 		service.getBeerFromApi();
	// 		for (BeerEntity beer : repository.findAll()) {
	// 			log.info(beer.getName());
	// 		}
	// 	};
	// }
	// @Bean
	// public CommandLineRunner demoPub(PubRepository repository) {
	// 	return (args) -> {
	// 		PubEntity pub1 = new PubEntity("Lolca","Sevilla","999000111");
	// 		PubEntity pub2 = new PubEntity("Bohemia","Granada","999000222");
	// 		repository.save(pub1);
	// 		repository.save(pub2);
	// 	};
	// }

	// @Bean
	// public CommandLineRunner demoTrivial(PreguntaRepository repository) {
	// 	return (args) -> {
	// 		PreguntaEntity p1 = new PreguntaEntity();
	// 		p1.setCategory("General Knowledge");
	// 		p1.setType("multiple");
	// 		p1.setDifficulty("medium");
	// 		p1.setQuestion("What does a milliner make and sell?");
	// 		p1.setCorrect_answer("Hats");
	// 		p1.setIncorrect_answers("Shoes;Belts;Shirts");
	// 		PreguntaEntity p2 = new PreguntaEntity();
	// 		p2.setCategory("Science: Computers");
	// 		p2.setType("boolean");
	// 		p2.setDifficulty("medium");
	// 		p2.setQuestion("Android versions are named in alphabetical order.");
	// 		p2.setCorrect_answer("True");
	// 		p2.setIncorrect_answers("False");
	// 		repository.save(p1);
	// 		repository.save(p2);
	// 	};
	// }

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
