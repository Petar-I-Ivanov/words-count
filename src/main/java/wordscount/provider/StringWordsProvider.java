package wordscount.provider;

import org.springframework.stereotype.Component;

@Component
public class StringWordsProvider implements WordsProvider {

	@Override
	  public String[] getWords(String text) {
	    return text.split("[\t\s\r\n,.-]");
	  }

}
