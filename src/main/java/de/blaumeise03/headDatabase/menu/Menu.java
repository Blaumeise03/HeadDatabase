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

package de.blaumeise03.headDatabase.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;

/**
 * Basic menu class. This class represents the first menu page (e.g. if you execute a command).
 */
public class Menu {
    List<MenuChild> content;
    int maxIndex;

    /**
     * Constructor for the menu.
     * @param content all content-objects wich are included in the menu.
     */
    Menu(List<MenuChild> content) {
        this.content = content;
    }

    /**
     * Method for setting up the indices of the content.
     */
    public void rearrange(){
        Map<String, MenuChild> temp = new HashMap<>();
        for(MenuChild child : content){
            temp.put(child.getName(),child);
        }
        LinkedHashMap<String, MenuChild> sortedTemp = new LinkedHashMap<>();
        temp.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sortedTemp.put(x.getKey(), x.getValue()));
        List<MenuChild> sortedContent = new ArrayList<>(sortedTemp.values());
        int i = 0;
        for(MenuChild child : sortedContent){
            child.setIndex(i);
            i++;
            if(child instanceof Menu) ((Menu) child).rearrange();
        }

        maxIndex = i;
    }

    /**
     * Method for handling a click inside the menu (inventory).
     * @param p the player who clicked.
     * @param slot the clicked slot.
     * @param session the {@link MenuSession} of the player.
     */
    public void processClick(Player p, int slot, MenuSession session){
        for(MenuChild menuChild : content){
            if(menuChild.getIndex() == slot){
                menuChild.onClick(p, session);
            }
        }
    }

    /**
     * Method for rendering the whole menu. It will override
     * all slots of the inventory.
     * @param inventory the inventory of the menu.
     */
    void renderMenu(Inventory inventory){
        inventory.clear();
        for(MenuChild child : content){
            if(child.getIndex() < inventory.getSize() - 9)
                inventory.setItem(child.getIndex(), child.render());
        }
    }
}
