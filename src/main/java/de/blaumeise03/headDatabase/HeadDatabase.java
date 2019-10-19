/*
 *     Copyright (C) 2019  Blaumeise03 - bluegame61@gmail.com
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published
 *     by the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.blaumeise03.headDatabase;

import de.blaumeise03.headDatabase.menu.MenuListener;
import de.blaumeise03.headDatabase.menu.MenuSession;
import de.blaumeise03.spigotUtils.AdvancedPlugin;
import de.blaumeise03.spigotUtils.Command;
import de.blaumeise03.spigotUtils.Configuration;
import de.blaumeise03.spigotUtils.exceptions.CommandNotFoundException;
import de.blaumeise03.spigotUtils.exceptions.ConfigurationNotFoundException;
import de.blaumeise03.spigotUtils.exceptions.PluginNotDefinedException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.permissions.Permission;

import java.util.List;

public class HeadDatabase extends AdvancedPlugin {
    private static Configuration config;
    private static boolean preventSave = false;
    private static List<HeadCollection> heads;
    private static AdvancedPlugin plugin;

    public static AdvancedPlugin getPlugin() {
        return plugin;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (preventSave)
            getLogger().warning("Config wird NICHT gespeichert!");
        else config.save();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        getLogger().info("Loading configurations...");
        config = new Configuration("heads.yml",this);
        try {
            config.setup(true);
        } catch (ConfigurationNotFoundException e) {
            e.printStackTrace();
        }
        getLogger().info("Loading heads...");
        heads = HeadCollection.loadAll(config);
        getLogger().warning("Loaded " + HeadCollection.getHeadCount() + " heads!");
        getLogger().info("Generating menu...");
        HeadCollection.generateMenu();
        getLogger().info("Registering events...");
        registerEvent(new MenuListener());
        getLogger().info("Registering commands...");
        try {
            new Command("head","Sehe /head help", new Permission("head.head"), true) {
                @Override
                public void onCommand(String[] args, CommandSender sender) {
                    if(args.length == 2){
                        if(args[0].equalsIgnoreCase("LINK")) {
                            Player p = (Player) sender;
                            p.getInventory().addItem(Head.getSkull(args[1]));
                            p.sendMessage("§aHier ist dein Kopf.");
                            return;
                        }if(args[0].equalsIgnoreCase("PLAYER")){
                            OfflinePlayer o = Bukkit.getOfflinePlayer(args[1]);
                            Player p = (Player) sender;
                            ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                            SkullMeta iMeta = (SkullMeta) skull.getItemMeta();
                            assert iMeta != null;
                            iMeta.setOwningPlayer(o);
                            skull.setItemMeta(iMeta);
                            p.getInventory().addItem(skull);
                            sender.sendMessage("§aHier ist dein Kopf.");
                            return;
                        }
                    }else if(args.length == 1){
                        if(args[0].equalsIgnoreCase("help")){
                            sender.sendMessage("§aHeadDatabase - Hilfe\n" +
                                    "§2/head §a - Öffnet das Menu (WIP).\n" +
                                    "§2/head LINK <Link> §a-Gibt dir den Kopf von der Minecraft-URL (eine längere Zeichenkette oder die ganze Adresse).\n" +
                                    "§2/head PLAYER <Spielername> §a-Gibt dir den Kopf des Spielers.\n" +
                                    "§2/head reload §a-Lädt die Config neu. §cDies §4LÖSCHT§c den Cache!!\n" +
                                    "§2/head preventSave §a-Verhindert das Speichern während des neuladens.");
                            return;
                        }
                        if(args[0].equalsIgnoreCase("preventSave")){
                            preventSave = !preventSave;
                            sender.sendMessage("§aDie Config wird beim Neustart/Stop" + (preventSave ? "§c nicht§a " : " §2") + "gespeichert.");
                            return;
                        }
                        if(args[0].equalsIgnoreCase("reload")){
                            getLogger().info("Reloading configuration...");
                            config.reload();
                            getLogger().info("Reloading heads...");
                            heads = HeadCollection.loadAll(config);
                            getLogger().warning("Reloaded " + HeadCollection.getHeadCount() + " heads!");
                            getLogger().info("Regenerating menu...");
                            HeadCollection.generateMenu();
                            sender.sendMessage("§aKöpfe wurden neugeladen!");
                            return;
                        }
                    }
                    if(args.length == 0){
                        sender.sendMessage("§aÖffne Menü...");
                        new MenuSession(HeadCollection.headDatabaseMenu, (Player) sender, 45, "Datenbank");
                        return;
                    }
                    sender.sendMessage("§cZu wenig argumente! Gebe §2/head help§c ein für weitere Hilfe.");
                }
            };
        } catch (PluginNotDefinedException | CommandNotFoundException e) {
            e.printStackTrace();
        }
    }
}
