package wordscount;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ApplicationController {

	private final String FILES_LOCATION = "C:/Users/pe6o0/eclipse-workspace/words-count/src/main/resources/static/files/";

	private WordsCounter counter;

	public ApplicationController(WordsCounter counter) {
		this.counter = counter;
	}

	@GetMapping("/")
	public String getView() {
		return "view";
	}

	@PostMapping("/countWords")
	public String count(@RequestParam String input, @RequestParam MultipartFile file, Model model) {

		try {

			File path = new File(FILES_LOCATION + file.getOriginalFilename());
			path.createNewFile();

			FileOutputStream output = new FileOutputStream(path);
			output.write(file.getBytes());
			output.close();

			model.addAttribute("count", counter.count(path.getAbsolutePath()));
			model.addAttribute("countTopTen", counter.topTenWords(path.getAbsolutePath()));

		} catch (IOException e) {
			model.addAttribute("count", counter.count(input));
			model.addAttribute("countTopTen", counter.topTenWords(input));
		}

		return "view";
	}
}
