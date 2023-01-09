package wordscount;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String count(@RequestParam String text,
						@RequestParam MultipartFile file,
						Model model) {
		
		String input = (file.getOriginalFilename().equals(""))
						? text
						: getFilePath(file);
		
		model.addAttribute("count", counter.count(input));
		model.addAttribute("countTopTen", counter.topTenWords(input));
		
		return "view";
	}
	
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/getTopWords")
	@ResponseBody
	public Map<String, Integer> count(@RequestParam String text,
									  @RequestParam(required = false) MultipartFile file) {
		
		return (file == null)
				? counter.topTenWords(text)
				: counter.topTenWords(getFilePath(file));
	}
	
	private String getFilePath(MultipartFile file) {
		
		try {
			File path = new File(FILES_LOCATION + file.getOriginalFilename());
			path.createNewFile();

			FileOutputStream output = new FileOutputStream(path);
			output.write(file.getBytes());
			output.close();

			return path.getAbsolutePath();
			
		} catch (IOException e) {
			return null;
		}
	}
}
