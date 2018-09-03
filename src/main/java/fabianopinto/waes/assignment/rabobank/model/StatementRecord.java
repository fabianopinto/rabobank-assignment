package fabianopinto.waes.assignment.rabobank.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import lombok.Data;

/**
 * Customer statement record.
 * 
 * Used to parse each customer statement from XML and CSV files.
 * 
 * @author fabianopinto@gmail.com
 *
 */
@Data
@CsvRecord(separator = ",", skipFirstLine = true)
@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatementRecord {

	@DataField(pos = 1)
	@XmlAttribute
	private Integer reference;

	@DataField(pos = 2)
	@XmlElement
	private String accountNumber;

	@DataField(pos = 3)
	@XmlElement
	private String description;

	@DataField(pos = 4, precision = 2)
	@XmlElement
	private BigDecimal startBalance;

	@DataField(pos = 5, precision = 2)
	@XmlElement
	private BigDecimal mutation;

	@DataField(pos = 6, precision = 2)
	@XmlElement
	private BigDecimal endBalance;

}
