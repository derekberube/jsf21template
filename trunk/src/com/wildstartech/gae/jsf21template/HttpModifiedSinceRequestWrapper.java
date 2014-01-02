/**
 * Copyright 2014 Wildstar Technologies, LLC.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.wildstartech.gae.jsf21template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Request wrapper to hide the If-Modified-Since request header.
 * 
 * @author Derek Berube, Wildstar Technologies, LLC.
 * @version 2014-10-01, 1.0
 * 
 * @see https://code.google.com/p/googleappengine/issues/detail?id=8415
 */
public class HttpModifiedSinceRequestWrapper extends HttpServletRequestWrapper {
	private static final String _CLASS = 
			HttpModifiedSinceRequestWrapper.class.getName();
	private static final Logger logger = Logger.getLogger(_CLASS);
	/**
	 * @param request
	 */
	public HttpModifiedSinceRequestWrapper(HttpServletRequest request) {
		super(request);
		logger.entering(_CLASS,"HttpModifiedSinceRequestWrapper");
		logger.exiting(_CLASS,"HttpModifiedSinceRequestWrapper");
	}	

	@Override
	/**
	 * Returns the header provided it is not the "If-Modified-Since" header.
	 */
	 public String getHeader(String name) {
		logger.entering(_CLASS,"getHeader(String)",name);
		String header=null;
		if (!"If-Modified-Since".equals(name)) {
			header=super.getHeader(name);
		} // END if (!"If-Modified-Since".equals(name))
		logger.exiting(_CLASS,"getHeader(String)",header);
		return header;
	 }

	 @SuppressWarnings("rawtypes")
	 @Override
	 /**
	  * Returns headers stripping out the "If-Modified-Since" header if
	  * present.
	  */
	 public Enumeration getHeaderNames() {
		 logger.entering(_CLASS,"getHeaderNames()");
		 Enumeration headerNames=null;
		 Enumeration<?> enu=null;		 
		 List<String> names;
		 String name=null;
		 
		 names=new ArrayList<String>();
		 enu=super.getHeaderNames();
		 
		 while (enu.hasMoreElements()) {
			 name = enu.nextElement().toString();
			 if (!"If-Modified-Since".equals(name)) {
				 names.add(name);
			 } // END if (!"If-Modified-Since".equals(name))
		 } // END while (enu.hasMoreElements())
		 headerNames=Collections.enumeration(names);
		 logger.exiting(_CLASS,"getHeaderNames()",headerNames);
		 return headerNames;
	 }
}
