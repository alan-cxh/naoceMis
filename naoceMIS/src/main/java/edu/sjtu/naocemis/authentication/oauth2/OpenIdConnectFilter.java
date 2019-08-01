package edu.sjtu.naocemis.authentication.oauth2;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.sjtu.api.applicationToolkit.model.Profile;
import edu.sjtu.json.Response;
import edu.sjtu.naocemis.authentication.permission.PermissionService;
import edu.sjtu.naocemis.authentication.permission.entity.Right;
import edu.sjtu.common.util.Constants;
import edu.sjtu.common.util.HttpConnection;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


public class OpenIdConnectFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    public OAuth2RestOperations restTemplate;

    @Value("${auth.client_id}")
    private String clientId;

    @Value("${auth.client_secret}")
    private String clientSecret;

    @Value("${auth.rights.url}")
    private String rightsUrl;

    @Bean
    public PermissionService permissionService(){
        return new PermissionService(rightsUrl);
    }


    public OpenIdConnectFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        setAuthenticationManager(new JAccountAuthenticationManager());
    }



    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        OAuth2AccessToken accessToken;
        try {
            accessToken = restTemplate.getAccessToken();
        } catch (OAuth2Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("Could not obtain access token", e);
        }
        try {
            //============get user info from id_token=========================
            String idToken = accessToken.getAdditionalInformation().get("id_token").toString();
            Jwt tokenDecoded = JwtHelper.decodeAndVerify(idToken, verifier(clientSecret));
            Map<String, String> authInfo = new ObjectMapper()
                    .readValue(tokenDecoded.getClaims(), Map.class);
            verifyClaims(authInfo);
            // 获取用户详细信息
            Map params = new HashMap();
            params.put("access_token",accessToken.getValue());
            JSONObject jsonObject = HttpConnection.doGet("https://api.sjtu.edu.cn/v1/me/profile", params);
            Gson gson = new Gson();
            Response<Profile> profileResponse = gson.fromJson(gson.toJson(jsonObject), new TypeToken<Response<Profile>>() {}.getType());
            Profile profile = profileResponse.getEntities().get(0);
            request.getSession().setAttribute(Constants.USER_PROFILE, profile);
            request.getSession().setAttribute(Constants.CURRENT_USER, profile.getAccount());
            //================generate userdetails and authtoken===============
            List<GrantedAuthority>  userPermission = getUserPermision(accessToken);
            OpenIdConnectUserDetails user = new OpenIdConnectUserDetails(authInfo, accessToken,userPermission);
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            return authToken;

        } catch (InvalidTokenException e) {
            throw new BadCredentialsException("Could not obtain user details from token", e);
        } catch ( Exception ex){
            ex.printStackTrace();
            throw new BadCredentialsException("Verify user details from token failed!", ex);
        }
    }



    public OAuth2RestOperations getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(OAuth2RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

        //========================JWT VERIFY=======================================

    private SignatureVerifier verifier(String verifierKey) throws Exception {
        SignatureVerifier verifier = new MacSigner(verifierKey);
        return verifier;
    }

    public void verifyClaims(Map claims) {
        int exp = (int) claims.get("exp");
        Date expireDate = new Date(exp * 1000L);
        Date now = new Date();
        if (expireDate.before(now) || !claims.get("aud").equals(clientId)) {
            throw new RuntimeException("Invalid claims");
        }
    }

    private List<GrantedAuthority>  getUserPermision(OAuth2AccessToken token){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<Right> allRights = permissionService().getMyRights(token.getValue());
        if(allRights != null){
            for(Right r : allRights){
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+r.getCode());
                grantedAuthorities.add(grantedAuthority);
            }
        }
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return grantedAuthorities;
    }
}
