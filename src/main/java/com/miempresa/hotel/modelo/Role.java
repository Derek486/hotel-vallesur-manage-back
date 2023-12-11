package com.miempresa.hotel.modelo;


import static com.miempresa.hotel.modelo.Permission.ADMIN_CREATE;
import static com.miempresa.hotel.modelo.Permission.ADMIN_DELETE;
import static com.miempresa.hotel.modelo.Permission.ADMIN_READ;
import static com.miempresa.hotel.modelo.Permission.ADMIN_UPDATE;
import static com.miempresa.hotel.modelo.Permission.MANAGER_CREATE;
import static com.miempresa.hotel.modelo.Permission.MANAGER_DELETE;
import static com.miempresa.hotel.modelo.Permission.MANAGER_READ;
import static com.miempresa.hotel.modelo.Permission.MANAGER_UPDATE;

import java.util.Collections;
import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptySet()),
    ADMIN(
        Set.of(
            ADMIN_READ,
            ADMIN_CREATE,
            ADMIN_UPDATE,
            ADMIN_DELETE,
            MANAGER_CREATE,
            MANAGER_READ,
            MANAGER_UPDATE,
            MANAGER_DELETE
        )
    ),
    MANAGER(
        Set.of(
            MANAGER_CREATE,
            MANAGER_READ,
            MANAGER_UPDATE,
            MANAGER_DELETE
        )
    );

    @Getter
    private final Set<Permission> permissions;
}
