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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

/**
 * This class contains all required <code>EventHandler</code>. It
 * must be registered inside the <code>onEnable()</code>-method.
 */
public class MenuListener implements Listener {

    @SuppressWarnings("SuspiciousMethodCalls")
    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(MenuSession.openMenus.containsKey(e.getWhoClicked())){
            if (e.getSlotType() == InventoryType.SlotType.CONTAINER) {
                if (e.getClickedInventory() == MenuSession.openMenus.get(e.getWhoClicked()).getInventory()) {
                    MenuSession.openMenus.get(e.getWhoClicked()).executeClick((Player) e.getWhoClicked(), e.getSlot());
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e){
        //noinspection SuspiciousMethodCalls
        MenuSession.openMenus.remove(e.getPlayer());
    }
}
