package cosmos.frontend.middle.api.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cosmos.frontend.middle.api.exception.MiddlewareServiceException;
import cosmos.frontend.middle.api.model.Action;
import cosmos.frontend.middle.api.model.DataPoint;
import cosmos.frontend.middle.api.model.DataStream;
import cosmos.frontend.middle.api.service.MiddlewareService;

@RestController
public class Controller {
	
	@Autowired
	private MiddlewareService middlewareService;
	
	private static final Logger logger = Logger.getLogger(Controller.class);
	
	@RequestMapping(value="/testAvailable", produces = { MediaType.APPLICATION_JSON_VALUE })
	public String testAvailable() {
		return "Service is available";
	}


	@CrossOrigin
	@RequestMapping(value="/dataStream/availables", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ArrayList<String> getDataStreamAvailables() throws MiddlewareServiceException{
		logger.debug(" Entering getDataStreamAvailables.");
		ResponseEntity<DataStream[]> responseEntity = middlewareService.getAvailableDataStreams();
		DataStream[] dataStreams = responseEntity.getBody();
		
		ArrayList<String> dataStreamNames = adaptDataStreamsToList(dataStreams);
		
		return dataStreamNames;
	}

	
	@CrossOrigin
	@RequestMapping(value = "/pruebaPOST", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public DataPoint getAvailability(@RequestBody Action req){
		logger.debug(" Entering getAvailability.");
		return new DataPoint(String.valueOf(Calendar.getInstance().getTimeInMillis()), "24");
	}
	
	@CrossOrigin
	@RequestMapping(value = "/dataStream/{name}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<DataPoint> getDataPointsForDataStream(@PathVariable(value = "name") String dataStreamName) throws MiddlewareServiceException{
		logger.debug(" Entering getDataPointsForDataStream.");
		System.out.println(" Entering getDataPointsForDataStream.");
		if("undefined".equalsIgnoreCase(dataStreamName)){
			return null;
		}else{
			
			ResponseEntity<List<DataPoint>> dataPointsForDataStream = middlewareService.getDataPointsForDataStream(dataStreamName);
			System.out.println(dataPointsForDataStream);
			return dataPointsForDataStream.getBody();
		}
	}
	
	

	//////////////////////
	// Private Methods  //
	/////////////////////
	
	private ArrayList<String> adaptDataStreamsToList(DataStream[] dataStreams) {
		ArrayList<String> dataStreamNames =  new ArrayList<>();
		
		for ( DataStream  dataStream : dataStreams ){
			dataStreamNames.add(dataStream.getName());
		}
		return dataStreamNames;
	}
}
