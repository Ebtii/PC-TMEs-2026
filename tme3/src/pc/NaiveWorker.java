package pc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class NaiveWorker implements Runnable {
	private long[] totalWords ;
	private File f ; 
	private long deb, fin ; 
	private Map<String, Integer> map ; 

	public NaiveWorker(File f, long deb, long fin, Map<String, Integer> map, long[] totalWords ) {
		this.f = f ; 
		this.deb = deb ; 
		this.fin = fin ; 
		this.map = map; 
		this.totalWords = totalWords; 
	}

	public void run() {
		try (Scanner scanner = new Scanner(FileUtils.getRange(f,deb ,fin))) {
			while (scanner.hasNext()) {
				String word = WordFrequency.cleanWord(scanner.next());
				if (!word.isEmpty()) {
					totalWords[0]++;
					map.compute(word, (w, c) -> c == null ? 1 : c + 1);
				}
			}
		} catch (IOException e){
            System.out.println(e.getMessage()) ; 
        }
	}
}
