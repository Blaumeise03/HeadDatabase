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

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a menu-session of a player.
 */
public class MenuSession {
    static Map<Player, MenuSession> openMenus = new HashMap<>();

    private Menu current;
    int startIndex;
    Map<Integer, MenuChild> viewContent;
    private Player viewer;
    private Inventory inventory;

    public MenuSession(Menu current, Player viewer, int size, String title) {
        this.current = current;
        this.viewer = viewer;
        inventory = Bukkit.createInventory(viewer, size, title);
        openMenus.put(viewer, this);
        viewContent = new HashMap<>();
        goTo(current);
    }

    void executeClick(Player p, int slot){
        current.processClick(p, slot, this);
    }

    void goTo(Menu menu){
        current = menu;
        startIndex = 0;
        if(menu instanceof ScrollableMenu)
            ((ScrollableMenu)menu).renderMenu(inventory, this);
        else menu.renderMenu(inventory);
        viewer.openInventory(inventory);
        //viewer.updateInventory();
        openMenus.put(viewer,this);
    }

    void update(){
        if(current instanceof ScrollableMenu)
            ((ScrollableMenu)current).renderMenu(inventory, this);
        else current.renderMenu(inventory);
        viewer.openInventory(inventory);
        openMenus.put(viewer,this);
    }
}
