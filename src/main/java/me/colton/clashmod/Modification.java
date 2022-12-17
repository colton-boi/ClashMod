package me.colton.clashmod;

public enum Modification {
    HIDE_PLAYERS(false, "Hide Players"),
    OUTLINE_CLASHERS(true, "Outline Clashers"),
    ALIVE_COUNT(true, "Alive Counter")
    ;

    private boolean enabled;
    private final String displayName;

    Modification(boolean defaultValue, String displayName) {
        this.enabled = defaultValue;
        this.displayName = displayName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDisplayName() {
        return displayName;
    }
}
