package com.infinira.sms.util;

import java.util.Locale;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import com.infinira.sms.util.ResourceUtil;
import com.infinira.sms.util.LoggerService;
import com.infinira.sms.util.SMSException;

public class SMSUtil{
	
    private static volatile SMSUtil getInstance;
	private static ResourceUtil rBundle;
	private static LoggerService logger;

    public static SMSUtil getInstance() {
        if (getInstance == null) {
            synchronized(SMSUtil.class) {
                if (getInstance == null) {
                    getInstance = new SMSUtil();
                }
            }
        }
        return getInstance;
    }
	
	private SMSUtil(){
		this.rBundle = ResourceUtil.getInstance();
		this.logger = LoggerService.getInstance();
	}
	
	public SMSException getException(String msgId, Throwable th, boolean shouldLog, Object... params){
		String msg = rBundle.getMessage(msgId,params);
		//SMSException ex= prepareException(th);
		SMSException ex = null;

		if (th == null){
			ex = new SMSException(msg);	
		} else {
			ex = new SMSException(msg,th);
		}
		if(shouldLog){
			logger.error(msg,ex);
		}
		return ex;			
	}	
	
	//public SMSException prepareException(String msg,Throwable th){		
	//	return new SMSException(msg,th);
	//}	
	
	public void info(String msgId, Object... params){
		String msg = rBundle.getMessage(msgId,params);
		logger.info(msg);
	}	
}	