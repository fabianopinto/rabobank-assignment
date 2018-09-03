package fabianopinto.waes.assignment.rabobank.routes;

import java.io.IOException;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.springframework.stereotype.Component;

import fabianopinto.waes.assignment.rabobank.beans.FileNameGeneratorBean;
import fabianopinto.waes.assignment.rabobank.beans.ValidateStatementsBean;
import fabianopinto.waes.assignment.rabobank.model.ProcessorResult;
import fabianopinto.waes.assignment.rabobank.model.StatementRecord;
import fabianopinto.waes.assignment.rabobank.model.StatementRecords;

/**
 * The main route for customer statements processing.
 * 
 * Data files detected in the data/inbox folder processed and the results stored
 * in the data/outbox folder. Any invalid file transferred to the
 * data/inbox/rejected folder.
 * 
 * @author fabianopinto@gmail.com
 *
 */
@Component
public class FileRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:{{app.file.inbox}}")
		.log("Processing input file \"${file:name}\" (${file:length} bytes)")
		.setProperty("resultsFileName", method(FileNameGeneratorBean.class))
		.choice()
			.when(simple("${file:ext} == 'csv'"))
				.doTry()
					.unmarshal(new BindyCsvDataFormat(StatementRecord.class))
					.bean(ValidateStatementsBean.class)
					.setBody(simple("${body.results}"))
					.marshal(new BindyCsvDataFormat(ProcessorResult.class))
				.doCatch(IllegalArgumentException.class)
					.log(LoggingLevel.ERROR, "Error parsing CSV file \"${file:name}\" -- file rejected")
					.to("file:{{app.file.rejected}}")
				.endChoice()
			.when(simple("${file:ext} == 'xml'"))
				.doTry()
					.unmarshal()
					.jaxb(StatementRecords.class.getPackage().getName().toString())
					.setBody(simple("${body.records}"))
					.bean(ValidateStatementsBean.class)
				.doCatch(IOException.class)
					.log(LoggingLevel.ERROR, "Error parsing XML file \"${file:name}\" -- file rejected")
					.to("file:{{app.file.rejected}}")
				.endChoice()
			.otherwise()
				.log(LoggingLevel.WARN, "Invalid file extension \"${file:ext}\" -- file rejected")
				.to("file:{{app.file.rejected}}")
				.stop()
			.end()
		.to("file:{{app.file.outbox}}?fileName=${property.resultsFileName}");
	}

}
