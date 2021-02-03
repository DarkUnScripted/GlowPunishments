package net.darkunscripted.GlowPunishments.domain;

import net.darkunscripted.GlowPunishments.data.PunishmentData;

import java.util.ArrayList;

public class Punishment {

    private final String name;
    private String command;
    private ArrayList<String> duration = new ArrayList<String>();
    private String permission;

    public Punishment(String name, String command, String permission){
        this.name = name;
        this.command = command;
        this.permission = permission;
        PunishmentData.punishments.add(this);
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }

    public ArrayList<String> getDuration() {
        return duration;
    }

    public String getPermission() {
        return permission;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setDuration(ArrayList<String> duration) {
        this.duration = duration;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void addDuration(String duration){
        this.duration.add(duration);
    }
}

