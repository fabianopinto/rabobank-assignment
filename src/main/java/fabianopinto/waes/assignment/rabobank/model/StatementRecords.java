package fabianopinto.waes.assignment.rabobank.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * Customer statement records collection.
 * 
 * Used to parse XML files for customer statements processing.
 * 
 * @author fabianopinto@gmail.com
 *
 */
@Data
@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatementRecords {

	@XmlElement(name = "record")
	private List<StatementRecord> records;

}
