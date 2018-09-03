package fabianopinto.waes.assignment.rabobank.beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.camel.Header;
import org.springframework.stereotype.Component;

/**
 * Generates the name for result files.
 * 
 * Generates the filename composing the original file with short timestamp
 * representation, keeping the same file extension and format.
 * 
 * @author fabianopinto@gmail.com
 *
 */
@Component
public class FileNameGeneratorBean {

	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	/**
	 * Returns similar filename with timestamp representation before the extension.
	 * 
	 * @param original
	 * @return
	 */
	public String resultsFileName(@Header("CamelFileName") String original) {
		String dateTime = dtf.format(LocalDateTime.now());
		return original.replaceAll("\\.(csv|xml)$", "-" + dateTime + ".$1");
	}

}
