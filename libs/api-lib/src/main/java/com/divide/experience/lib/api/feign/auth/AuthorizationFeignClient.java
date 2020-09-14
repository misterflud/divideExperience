package com.divide.experience.lib.api.feign.auth;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@FeignClient(name = "auth-service")
public interface AuthorizationFeignClient {

    @ApiOperation(value = "Checks JWT token.",
        notes = "This endpoint was created for checking a token by JwtAuthenticationUserFilter. "
            + "If you take this response then you have a correct JWT token.")
    @RequestMapping(value = "/checkToken", method = RequestMethod.GET)
    String checkToken();

    @ApiOperation(value = "Returns information about user (just for inner services).")
    @RequestMapping(value = "/user_details", method = RequestMethod.GET)
    UserAuthDetailsData getUserByToken(@ApiParam(value = "JWT token", example = "Bearer asdad1213xffvx2311311a",
        required = true)
                                   @RequestHeader String token);
}
