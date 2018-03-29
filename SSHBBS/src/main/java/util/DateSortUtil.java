package util;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class DateSortUtil {
	
	public static void sortDate(Object[] objs){
		Date idate = null;
		Date jdate = null;
		for (int i = 0; i < objs.length-1; i++) {
			for (int j = i+1; j < objs.length; j++) {
				try {
					idate = (Date) objs[i].getClass().getMethod("getPublish_time", null).invoke(objs[i], null);
					jdate = (Date) objs[j].getClass().getMethod("getPublish_time", null).invoke(objs[j], null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(idate.after(jdate)){
					Object t = objs[i];
					objs[i] = objs[j];
					objs[j] = t;
				}
			}
		}
	}
	
	public static void lastestFirst(Object[] objs){
		Date idate = null;
		Date jdate = null;
		for (int i = 0; i < objs.length-1; i++) {
			for (int j = i+1; j < objs.length; j++) {
				try {
					idate = (Date) objs[i].getClass().getMethod("getPublish_time", null).invoke(objs[i], null);
					jdate = (Date) objs[j].getClass().getMethod("getPublish_time", null).invoke(objs[j], null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(idate.before(jdate)){
					Object t = objs[i];
					objs[i] = objs[j];
					objs[j] = t;
				}
			}
		}
	}
}
