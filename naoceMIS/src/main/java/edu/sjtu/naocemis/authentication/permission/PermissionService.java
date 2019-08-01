package edu.sjtu.naocemis.authentication.permission;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.sjtu.naocemis.authentication.permission.entity.Right;
import edu.sjtu.common.util.HttpConnection;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PermissionService {

    private static Log logger = LogFactory.getLog(PermissionService.class);

    private String rightsUrl;

    public PermissionService(String rightsUrl){
        this.rightsUrl = rightsUrl;
    }


    public List<Right> getMyRights(String token) {

        Map<String,String> parameters = new HashMap<String, String>();
        parameters.put("access_token",token);
        try{
            JSONObject result = HttpConnection.doGet(rightsUrl, parameters);
            if(0!=(int)result.get("errno")){
                logger.error("get user rights failed! token="+token+";result="+result.toString());
                return null;
            }
            Object obj = result.get("entities");
            Gson gson = new Gson();
            List<Right> entities = gson.fromJson(gson.toJson(obj), new TypeToken<List<Right>>(){}.getType());
            return entities;
        }catch (Exception ex){
            logger.error("get user rights failed! token="+token,ex);
            return null;
        }

    }




}
