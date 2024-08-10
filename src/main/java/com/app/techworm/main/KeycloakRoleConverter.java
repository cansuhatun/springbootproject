package com.app.techworm.main;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        // Önce varsayılan rol dönüştürücüsünü kullanarak yetkileri al
        Collection<GrantedAuthority> authorities = defaultGrantedAuthoritiesConverter.convert(jwt);

        // JWT token'ındaki realm_access claim'ini al
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");

        // realm_access claim'inde roller varsa
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            // rollerin "ROLE_" ile başladığından emin olun
            Collection<GrantedAuthority> realmAuthorities = ((Collection<String>) realmAccess.get("roles")).stream()
                    .map(roleName -> "ROLE_" + roleName)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            authorities.addAll(realmAuthorities);
        }

        return authorities;
    }
}