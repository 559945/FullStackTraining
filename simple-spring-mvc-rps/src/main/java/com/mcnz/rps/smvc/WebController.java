package com.mcnz.rps.smvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;






@Controller
public class WebController {
	@Autowired
	SubjectRepository subRepository;
	
	@Autowired
	BookRepository bookrepo;
	
	@GetMapping ("/playagame")
	public String playGame(
			@RequestParam(name="choice", required=false) 
			    String theChoice, 
			       Model model) {
		
		if (theChoice == null) {
			return "index";
		}
		
		String theOutcome = "error";
		if (theChoice.equals("rock")) {
			theOutcome = "tie";
		}
		if (theChoice.equals("paper")) {
			theOutcome = "win";
		}
		if (theChoice.equals("scissors")) {
			theOutcome = "loss";
		}
		
		model.addAttribute("outcome", theOutcome);
		return "results";
		
	}
	
	@GetMapping(value = "/savebook")
  public ModelAndView saveBook(HttpServletRequest request, HttpServletResponse response) {
	System.out.println("................. Get ..........");
    ModelAndView mav = new ModelAndView("savebook");
    mav.addObject("subject", new Subject());
    return mav;
  }
	
	@PostMapping("/savebookprocess")
    public String addUser(@Valid Subject subject, BindingResult result, Model model) {
		
        if (result.hasErrors()) {
            return "savebook";
        }
        
        Set<Book> bookA = new HashSet();
		bookA.add(new Book("physics", 150, 1, LocalDate.now(),subject));
		bookA.add(new Book("material physics", 250, 1, LocalDate.now(),subject));
		bookA.add(new Book("solid physics", 350, 1, LocalDate.now(),subject));
		
        subject.setReferences(bookA); 
        subRepository.save(subject);
        model.addAttribute("subjects", subRepository.findAll());
        model.addAttribute("success", "Successfully added");
        return "listsubjects";
    }
	
	@GetMapping ("/getall")
	public String getAllSubjects(Model model) {
		
		
		for (Subject subject : subRepository.findAll()) {
			System.out.println("Subject-->" + subject);
		}
		
		model.addAttribute("subjects", subRepository.findAll());
		//Optional<Subject> subject=subRepository.findById(2L);
		//System.out.println("subject........................->"+subject.toString());
		//model.addAttribute("subject", subject);
		return "listsubjects";
		
		
	}
	
	@GetMapping ("/getsubjectbyid/{subjectId}")
	public String getSubjectById(@PathVariable("subjectId") Long subjectId,Model model) {
		
		Optional<Subject> subject=subRepository.findById(subjectId);
		
		System.out.println("subject........................->"+subject.toString());
		
		model.addAttribute("subject", subject);
		return "subject";
	}
	
	@GetMapping ("/getbookbyid/{bookId}")
	public String getBookById(@PathVariable("bookId") Integer bookId,Model model) {
		
		Optional<Book> book=bookrepo.findById(bookId);
		
		System.out.println("book........................->"+book.toString());
		
		model.addAttribute("book", book);
		return "subject";
	}
	
	@GetMapping ("/getbooksbysubjectid/{subjectId}")
	public String getBooksBySubjectId(@PathVariable("subjectId") Long subjectId,Model model) {
		
		Optional<Subject> subject=subRepository.findById(subjectId);
		
		System.out.println("subject->"+subject.toString());
		
		Set<Book> bookset=subject.get().getReferences();
		
		Iterator<Book> bookitr=bookset.iterator();
		while(bookitr.hasNext()){
			Book b1=bookitr.next();
			System.out.println("Book------------>"+b1.toString());
		}
		
		model.addAttribute("books", bookset);
		return "listbooks";
	}
	
	@GetMapping ("/deletesubject/{subjectId}")
	public String deleteSubjectById(@PathVariable("subjectId") Long subjectId,Model model) {
		
		Optional<Subject> subject=subRepository.findById(subjectId);
		
		System.out.println("subject->"+subject.toString());
		
		Set<Book> bookset=subject.get().getReferences();
		Iterator<Book> bookitr=bookset.iterator();
		
		while(bookitr.hasNext()){
			Book b1=bookitr.next();
			System.out.println("Book------------>"+b1.toString());
			bookrepo.delete(b1);
		}
		return "redirect:/getall";
	}
}
