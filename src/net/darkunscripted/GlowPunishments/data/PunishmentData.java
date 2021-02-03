package net.darkunscripted.GlowPunishments.data;

import net.darkunscripted.GlowPunishments.domain.Punishment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PunishmentData {

    public static ArrayList<Punishment> punishments = new ArrayList<Punishment>();
    public static HashMap<UUID, ArrayList<Punishment>> playerPunishments = new HashMap<UUID, ArrayList<Punishment>>();

}
