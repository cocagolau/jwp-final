package next.support.gson;

import com.google.gson.Gson;

public class GSON {

	public static String toJsonString(Object obj) {
		
		Gson gson = new Gson();
		
		return gson.toJson(obj);
	}
	
}
