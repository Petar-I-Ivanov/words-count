package wordscount;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wordscount.provider.WordsProvider;

@Component
public class WordsCounter {
	
	@Autowired private WordsProvider urlWordsProvider;
	@Autowired private WordsProvider stringWordsProvider;
	@Autowired private WordsProvider fileWordsProvider;
	
	public int count(String text) {
		
	    return  pickWordsProvider(text)
	    			.getWords(text).length;
	}
	
	public Map<String, Integer> topTenWords(String text) {
		
		WordsProvider provider = pickWordsProvider(text);
		
		return setWordsCountMap(provider.getWords(text))
				.entrySet().stream()
				.sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
				.limit(10)
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
 	}
	
	private Map<String, Integer> setWordsCountMap(String[] words) {
		
		Map<String, Integer> counter = new HashMap<>();
		
		Arrays.stream(words).forEach(word -> {
			var count = counter.get(word);
			count = (count == null) ? 1 : count + 1;
			counter.put(word, count);
		});
		
		return counter;
	}
	
	private boolean isValidURL(String url) {
		
	    try {
	        new URL(url).toURI();
	        return true;
	    }
	    
	    catch (MalformedURLException | URISyntaxException e) {
	        return false;
	    }
	}
	
	private boolean isValidFilePath(String path) {
		return new File(path).exists();
	}
	
	private WordsProvider pickWordsProvider(String text) {
		
		return (isValidURL(text)) ? urlWordsProvider	
			 : (isValidFilePath(text)) ? fileWordsProvider	
			 : stringWordsProvider;
	}
}
