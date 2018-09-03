package fabianopinto.waes.assignment.rabobank.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * Customer statement processor results.
 * 
 * Used to generate XML files with the collection of customers statements
 * validation results.
 * 
 * @author fabianopinto@gmail.com
 *
 */
@Data
@XmlRootElement(name = "results")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcessorResults {

	@XmlElement(name = "result")
	private List<ProcessorResult> results;

}
