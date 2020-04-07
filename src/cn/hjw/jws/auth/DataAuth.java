package cn.hjw.jws.auth;

import java.util.Iterator;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;

import cn.hjw.common.Constant;
import cn.hjw.common.MD5Util;

public class DataAuth {
	
	 /** 
     *  
    *  
     * @throws AxisFault 
     */  
  public static void checkUserPwd() throws AxisFault  
    {  
        MessageContext msgContext = MessageContext.getCurrentMessageContext();  
        // 获取Head  
        Iterator<?> list = (Iterator<?>) msgContext.getEnvelope().getHeader()  
                .getFirstElement().getChildren();  
       String Username = "";  
       String Password = "";  
       while (list.hasNext())  
       {  
           OMElement element = (OMElement) list.next();  
            if (element.getLocalName().equals("Username"))  
           {  
               Username = element.getText();  
            }  
            if (element.getLocalName().equals("Password"))  
           {  
                Password = element.getText();  
           }  
       }  
        if (!Username.equals(MD5Util.MD5(Constant.getText("username"))) || !Password.equals(MD5Util.MD5(Constant.getText("password"))))  
       {  
           throw new AxisFault(  
                    " Authentication Fail! Check username/password ");  
        }  
   }  
}
