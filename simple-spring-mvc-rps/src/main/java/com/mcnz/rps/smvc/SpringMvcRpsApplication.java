package com.mcnz.rps.smvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMvcRpsApplication {//implements CommandLineRunner{

	@Autowired
	SubjectRepository subRepository;
	
	@Autowired
	BookRepository bookrepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMvcRpsApplication.class, args);
	}
	
	/*@Transactional
	@Override
	public void run(String... strings) throws Exception {
		// save a couple of categories
		System.out
				.println(".................................execution started-------------->");

		final Subject subA = new Subject("physics", 120);
		Set<Book> bookA = new HashSet();
		bookA.add(new Book("general physics", 150, 1, LocalDate.now(), subA));
		bookA.add(new Book("material physics", 250, 1, LocalDate.now(), subA));
		bookA.add(new Book("solid physics", 350, 1, LocalDate.now(), subA));
		subA.setReferences(bookA);

		final Subject subB = new Subject("chemistry", 120);
		Set<Book> bookB = new HashSet<>();
		bookB.add(new Book("general chemistry", 150, 1, LocalDate.now(), subB));
		bookA.add(new Book("material chemistry", 250, 1, LocalDate.now(), subB));
		bookA.add(new Book("solid chemistry", 350, 1, LocalDate.now(), subB));
		subB.setReferences(bookB);

		subRepository.saveAll(Arrays.asList(subA, subB));

		// fetch all categories
		for (Subject subject : subRepository.findAll()) {
			System.out.println("Subject-->" + subject);
		}
		
		Optional<Subject> subject=subRepository.findById(1L);
		
		System.out.println("subject........................->"+subject.toString());
		
		Set<Book> bookset=subject.get().getReferences();
		Iterator<Book> bookitr=bookset.iterator();
		
		while(bookitr.hasNext()){
			Book b1=bookitr.next();
			System.out.println("Book------------>"+b1.toString());
			bookrepo.delete(b1);
		}
		
	}*/
}
