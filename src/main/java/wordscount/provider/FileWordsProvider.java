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

		return convertToText(text).split("[\t\s\r\n,.-]");
	}

	private String convertToText(String path) {

		try {

			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			String text = br.lines().collect(Collectors.joining());

			br.close();
			
			return text;

		} catch (IOException e) {
			return null;
		}
	}
}
