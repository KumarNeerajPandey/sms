package com.infinira.sms.util;

import java.util.ResourceBundle;
import java.util.Locale;
import java.text.MessageFormat;
import java.util.MissingResourceException;

public class ResourceUtil{
	
	private static final String LOCALE_FILE = "sms_messages";
    private static volatile ResourceUtil getInstance;
	private static ResourceBundle rBundle;

    public static ResourceUtil getInstance() {
        if (getInstance == null) {
            synchronized(ResourceUtil.class) {
                if (getInstance == null) {
                    getInstance = new ResourceUtil();
                }
            }
        }
        return getInstance;
    }
	
	private ResourceUtil(){
		try {
			rBundle = ResourceBundle.getBundle(LOCALE_FILE,Locale.getDefault());
			//rBundle = ResourceBundle.getBundle(LOCALE_FILE,Locale.FRANCE);
		} catch (MissingResourceException e){
			throw new RuntimeException (MSG_001, e);
		}
	}
	
	public static String getMessage(String msgId, Object... params){
		
		if (msgId == null || msgId.isBlank()){
			return MSG_002;
		}
		if(!rBundle.containsKey(msgId)){
			return MessageFormat.format(MSG_003, msgId);
		}
		String msg = rBundle.getString(msgId);
		if(msg == null || msg.isBlank()){
			return MessageFormat.format(MSG_004, msgId);
		}
		return (params == null) ? msg : MessageFormat.format(msg, params);
		
/*		if(msgId != null && rBundle.containsKey(msgId)){
			String msg = rBundle.getString(msgId);
			if(msg != null && !msg.isBlank()){
				return MessageFormat.format(msg,params);
			} else {
				return "Message is Empty for the " +msgId + "";
				//throw new RuntimeException ("Message is Empty for the given msgId in ResurceBundle file : " +msgId);			
			}
		} else {
			return "Given "+msgId+" is not present in ResurceBundle file.".
			//throw new RuntimeException ("Given msgId is not present in ResurceBundle file : "+msgId);
		}
*/	
	}	
	
	private static final String MSG_001 = "ResurceBundle 'messages' file not found.";
	private static final String MSG_002 = "msgId cannot be Null or Blank";
	private static final String MSG_003 = "Given msgId: {0} is not present in ResurceBundle file.";
	private static final String MSG_004 = "Message is Empty for the given msgId: {0} in ResurceBundle file.";
}