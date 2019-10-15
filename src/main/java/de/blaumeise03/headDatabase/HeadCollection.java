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

import de.blaumeise03.headDatabase.menu.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class HeadCollection {
    private static int headCount = 0;
    private static List<HeadCollection> headDatabase = new ArrayList<>();
    public static Menu headDatabaseMenu;

    private List<Head> heads = new ArrayList<>();
    private String name;
    private String displayName;
    private ItemStack icon;

    public static List<HeadCollection> loadAll(FileConfiguration configuration){
        headCount = 0;
        List<HeadCollection> headColls = new ArrayList<>();
        ConfigurationSection section = configuration.getConfigurationSection("heads");
        if(section == null){
            HeadDatabase.getPlugin().warn("Config is empty. If you want to restore a prefilled " +
                    "config execute /head preventSave, delete the config and restart/reload the server.");
            return new ArrayList<>();
        }
        Set<String> rawCollections = section.getKeys(false);
        for(String rawCollection : rawCollections){
            HeadCollection coll = new HeadCollection(rawCollection);
            coll.loadCollection(configuration);
            headColls.add(coll);
        }
        headDatabase = headColls;
        return headColls;
    }

    public HeadCollection(String name) {
        this.name = name;
    }

    public void loadCollection(FileConfiguration configuration){
        heads = new ArrayList<>();
        Set<String> rawHeads = Objects.requireNonNull(
                configuration.getConfigurationSection("heads." + name)).getKeys(false);
        displayName = configuration.getString("heads." + name + ".name");
        if(displayName == null)
            HeadDatabase.getPlugin().getLogger().warning("Corrupted database! Collection '" + name + "' has no name defined!");
        String iconRaw = configuration.getString("heads." + name + ".icon");
        if(iconRaw == null)
            HeadDatabase.getPlugin().getLogger().warning("Corrupted database! Collection '" + name + "' has no icon defined!");
        else icon = Head.getSkull(iconRaw);
        for(String rawHead : rawHeads){
            if(rawHead.equalsIgnoreCase("icon") || rawHead.equalsIgnoreCase("name")) continue;
            String headName = configuration.getString("heads." + name + "." + rawHead + ".name");
            String headLink = configuration.getString("heads." + name + "." + rawHead + ".link");
            if(headName == null || headLink == null){
                HeadDatabase.getPlugin().getLogger().warning("Corrupted database! Head '"
                        + rawHead + "' from collection '" + name + "' has missing information: name: " + headName + " link: " + headLink);
            }else {
                addHead(new Head(rawHead, headName, headLink));
                headCount++;
            }
        }
    }

    public static void generateMenu(){
        //System.out.println("Test2");;
        List<MenuChild> groupSelection = new ArrayList<>();
        for(HeadCollection coll : headDatabase){
            groupSelection.add(coll.getMenu());
        }
        //System.out.println("C " + headDatabase.size() + "|" + groupSelection.size());
        Menu menu = new ScrollableMenu(groupSelection, 45 - 9);
        menu.rearrange();
        headDatabaseMenu = menu;
    }

    public MenuChild getMenu(){
        List<MenuChild> content = new ArrayList<>();
        for(Head head : heads){
            content.add(head.getButton());
        }
        return new ScrollableSubMenu(content, displayName, icon, 45 - 9);
    }

    public void addHead(Head head){
        heads.add(head);
    }

    public static int getHeadCount(){
        return headCount;
    }
}
