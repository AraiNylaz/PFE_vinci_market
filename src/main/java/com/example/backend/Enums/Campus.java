package com.example.backend.Enums;

import javax.naming.Name;


public enum Campus {
    IXELLES("Ixelles"),
    WOLUWE("Woluwe"),
    LOUVAIN("Louvain-La-Neuve");

    private String name;

    Campus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
