package wordscount.provider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class FileWordsProvider implements WordsProvider {

	@Override
	public String[] getWords(String text) {
		
		String actualText = "";
		
		try {
			File file = new File(text);
			BufferedReader br = new BufferedReader(new FileReader(file));
			actualText = br.lines().collect(Collectors.joining());
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return actualText.split("[\t\s\r\n,.-]");
	}}
