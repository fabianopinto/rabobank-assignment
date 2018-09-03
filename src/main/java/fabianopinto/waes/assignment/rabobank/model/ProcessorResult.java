package fabianopinto.waes.assignment.rabobank.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import lombok.Data;

/**
 * Individual statement processor result.
 * 
 * Used to generate XML or CSV files with each statement processor result.
 * 
 * @author fabianopinto@gmail.com
 *
 */
@Data
@CsvRecord(separator = ",", generateHeaderColumns = true)
@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcessorResult {

	@DataField(pos = 1, position = 1, columnName = "Index")
	@XmlAttribute
	private Integer index;

	@DataField(pos = 2, position = 2, columnName = "Reference")
	@XmlAttribute
	private Integer reference;

	@DataField(pos = 3, position = 3, columnName = "Description")
	@XmlElement
	private String description;

}
