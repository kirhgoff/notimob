package com.gpmedia.notimob;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Utils {

	public static String printStackTrace(Exception e) {
		String mainContent;
		StringWriter writer = new StringWriter ();
		PrintWriter stream = new PrintWriter(writer, true);
		e.printStackTrace(stream);
		stream.flush();
		writer.flush();
		
		mainContent = writer.toString();
		return  "<nowrap>" + mainContent  + "</nowrap>";
	}

}
