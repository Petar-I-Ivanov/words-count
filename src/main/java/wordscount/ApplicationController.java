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

	WordsCounter counter;

	public ApplicationController(WordsCounter counter) {
		this.counter = counter;
	}

	@GetMapping("/")
	public String getView() {
		return "view";
	}

	@PostMapping("/countWords")
	public String count(@RequestParam String input, @RequestParam MultipartFile file, Model model) {

		if (!file.getOriginalFilename().equals("")) {

			File path;

			try {

				path = new File("C:/Users/pe6o0/eclipse-workspace/words-count/src/main/resources/files/" + file.getOriginalFilename());
				path.createNewFile();
				
				FileOutputStream output = new FileOutputStream(path);
				output.write(file.getBytes());
				output.close();
				
				model.addAttribute("countTopTen", counter.topTenWords(path.getAbsolutePath()));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else {
			model.addAttribute("countTopTen", counter.topTenWords(input));
		}
		return "view";
	}
}
