package wordscount;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ApplicationController {

  private final String BASEDIR = System.getProperty("user.dir");
  private final String FILES_LOCATION = "\\src\\main\\resources\\static\\files\\";

  private final String PREFIX = BASEDIR + FILES_LOCATION;

  private WordsCounter counter;

  public ApplicationController(WordsCounter counter) {
    this.counter = counter;
  }

  @GetMapping("/")
  public String index() {
    return "forward:/index.html";
  }

  @PostMapping("/getTopWords")
  @ResponseBody
  public Map<String, Integer> count(@RequestParam String text,
      @RequestParam(required = false) MultipartFile file) {

    return (file == null) ? counter.topTenWords(text) : counter.topTenWords(getFilePath(file));
  }

  @PostMapping("/getWordsCount")
  @ResponseBody
  public Integer countWords(@RequestParam String text,
      @RequestParam(required = false) MultipartFile file) {

    return (file == null) ? counter.count(text) : counter.count(getFilePath(file));
  }

  private String getFilePath(MultipartFile file) {

    try {
      File path = new File(PREFIX + file.getOriginalFilename());
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
