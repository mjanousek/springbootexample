package cz.janousek.springbootlive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {
	private ReadingListRepository readingListRepository;

	@Autowired // automaticky injectovano containerem
	public HelloController(ReadingListRepository readingListRepository) {
		this.readingListRepository = readingListRepository;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "hello";
	}

	/**
	 * Navraci seznam vsech knizek ctenare
	 * @param reader
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{reader}", method = RequestMethod.GET)
	public String readersBooks(@PathVariable("reader") String reader, Model model) {
		List<Book> readingList = readingListRepository.findByReader(reader);
		if (readingList != null) {
			model.addAttribute("books", readingList); // prida list knizek do modelu
		}
		return "readingList"; // vraci view s nazvem: "readingList"
	}

	/**
	 * Z tela requestu vytvori Book object
	 * Nastavi ctenare
	 * Ulozi modifikovanou knihu
	 * Redirectuje se na profil ctenare
	 * @param reader
	 * @param book
	 * @return
	 */
	@RequestMapping(value = "/{reader}", method = RequestMethod.POST)
	public String addToReadingList(
			@PathVariable("reader") String reader,
			Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/{reader}";
	}
}
