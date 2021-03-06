/**
 * This is the controller layer of the application.
 * @author ankurbag
 * 
 */
package edu.neu.ga.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.neu.ga.service.TimetableService;
import io.swagger.annotations.Api;
import edu.neu.ga.beans.Class;
import edu.neu.ga.beans.Schedule;

/**
 * Handles requests for TimetableController
 */
@Controller
@Api(value = "Timetable", description = "Timetable Controller")
@RequestMapping("/schedule")
public class TimetableController {

	private static final Logger logger = LoggerFactory.getLogger(TimetableController.class);

	/**
	 * Get the Schedule. Thus is the parallel execution.
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@RequestMapping(value = "/parallelexecution", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Schedule> getSchedule() throws InterruptedException, ExecutionException {
		logger.info("Invoke : TimetableController.");
		return new TimetableService().execute();
	}
	
	/**
	 * Get the Schedule. Thus is the single-threaded execution.
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@RequestMapping(value = "/singlethreadedexecution", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Schedule> getSchedulesSingleThreaded() throws InterruptedException, ExecutionException {
		logger.info("Invoke : TimetableController.");
		return new TimetableService().execute_without_parallisation();
	}


}
