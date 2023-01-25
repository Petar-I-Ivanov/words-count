package wordscount.provider;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

@Component
public class UrlWordsProvider implements WordsProvider {

	@Override
	public String[] getWords(String text) {
	        
			try {	
				
				return Jsoup.connect(text).get()
						.body().text()
						.split("[\t\s\r\n,.-]");
		        
			} catch (IOException e) {
				return null;
			}
	}

}
