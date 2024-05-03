package com.backend.backendnew.AuthTokenVerifyPackage;

import com.backend.backendnew.clientPackage.UserClient;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
public class AuthTokenService {

    private static final String jwtSecret = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";

    UserClient userClient=new UserClient();


    public Boolean verify(String jwtToken, Integer Role){

        //verify logic
        Claims claims;
        try{
            claims = decodeJwt(jwtToken);
        }catch (Exception e){
            return false;
        }
        //System.out.println(claims);
        String UserName=(String) claims.get("UserName");

        if(userClient.doesUserExist(UserName,Role))return true;
        else return false;
    }

    public String getUsername(String jwtToken){

        Claims claims = decodeJwt(jwtToken);
        String UserName=(String) claims.get("UserName");

        return UserName;
    }
    


    public Claims decodeJwt(String jwtToken) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKeyResolver(new SigningKeyResolverAdapter() {
                    @Override
                    public byte[] resolveSigningKeyBytes(JwsHeader header, Claims claims) {
                        return jwtSecret.getBytes();
                    }
                })
                .build()
                .parseClaimsJws(jwtToken);

//        System.out.println("claimsJws.getSignature());
        return claimsJws.getBody();
    }
}
