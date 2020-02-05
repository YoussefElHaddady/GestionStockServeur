package ma.tc.projects.enums;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Code {
	
	public static String generate() {
		return generate("");
	}
	
	public static String generate(String str) {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("-yyD-Hm");
		return str + ldt.format(formatter);
	}

}
